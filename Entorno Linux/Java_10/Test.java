import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.Collections;

public class Test {
    static final int MAX_T = 10;

    public static void main(String[] args) {
        String archivoNombre = "biblia_completa.txt";
        List<String> contenido = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(archivoNombre))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                contenido.add(linea);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int tamanioPartes = 7037;
        ExecutorService pool = Executors.newFixedThreadPool(MAX_T);

        Runnable r1 = new Task("task 1", contenido.subList(0, tamanioPartes));
        Runnable r2 = new Task("task 2", contenido.subList(tamanioPartes, tamanioPartes * 2));
        Runnable r3 = new Task("task 3", contenido.subList(tamanioPartes * 2, tamanioPartes * 3));
        Runnable r4 = new Task("task 4", contenido.subList(tamanioPartes * 3, tamanioPartes * 4));
        Runnable r5 = new Task("task 5", contenido.subList(tamanioPartes * 4, contenido.size()));

        pool.execute(r1);
        pool.execute(r2);
        pool.execute(r3);
        pool.execute(r4);
        pool.execute(r5);

        pool.shutdown();
    }

    static class Task implements Runnable {
        private String name;
        private List<String> parteTexto;

        public Task(String nombre, List<String> parteTexto) {
            name = nombre;
            this.parteTexto = parteTexto;
        }

        
        public void run() {
            System.out.println("Inicia: " + name);
            Map<Character, Integer> mapaConteo = new LinkedHashMap<>();
            for (String linea : parteTexto) {
                for (char c : linea.toCharArray()) {
                    mapaConteo.put(c, mapaConteo.getOrDefault(c, 0) + 1);
                }
            }

            List<Map.Entry<Character, Integer>> lista = new ArrayList<>(mapaConteo.entrySet());

            // Ordenamos la lista
            Collections.sort(lista, new Comparator<Map.Entry<Character, Integer>>() {
            public int compare(Map.Entry<Character, Integer> e1, Map.Entry<Character, Integer> e2) {
                return e2.getValue().compareTo(e1.getValue());
                }
            });

            // imprimimos la lista ya ordenada
            for (Map.Entry<Character, Integer> e : lista) {
                System.out.println(e.getKey() + ": " + e.getValue());
            }

            System.out.println("Fin de: " + name);
        }
    }
}
