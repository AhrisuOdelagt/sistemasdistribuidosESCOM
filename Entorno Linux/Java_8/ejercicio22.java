//javac -Xlint:deprecation ejercicio22.java

import java.util.*;
import java.io.*;

public class ejercicio22 {
	// Inicializamos el método main
	public static void main(String[] args) throws Exception{
  
		//String archivoNombre = "El_viejo_y_el_mar.txt";
		
        // Creating an empty HashMap 
        Map<Character, Integer> map = new LinkedHashMap<>();
		Map<Character, Integer> map_sorted = new LinkedHashMap<>(); 
		// Creamos un ArrayList
		ArrayList<Integer> lista = new ArrayList<Integer>();
		
		File file = new File("El_viejo_y_el_mar.txt");
        Scanner sc = new Scanner(file);
		int i=1;
        while (sc.hasNextLine()){
			String linea = sc.nextLine();
			for(char c: linea.toCharArray()){
				map.put(c,map.getOrDefault(c,0)+1);
			}
		}
		
		// Agregamos los valores a la lista
		for (Map.Entry<Character, Integer> e : map.entrySet()) 
            lista.add(e.getValue()); 
		// Ordenamos los valores de la lista
		Collections.sort(lista, new Comparator<Integer>(){
            public int compare(Integer int1, Integer int2) {
                return int2.compareTo(int1);
            }
		});
		// Agregamos los valores ordenados a un nuevo Hashmap
		for(int entero: lista) {
            for (Map.Entry<Character, Integer> e : map.entrySet()) {
				if(entero == e.getValue()) {
					map_sorted.put(e.getKey(), e.getValue());
				}
			}
        }
        
		// Iterating over Map using for each loop 
        for (Map.Entry<Character, Integer> e : map_sorted.entrySet()) 
            System.out.println(e.getKey() + " " + e.getValue()); 
    } 
	
}
