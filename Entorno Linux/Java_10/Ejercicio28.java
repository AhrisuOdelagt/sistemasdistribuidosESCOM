//javac -Xlint:deprecation Ejercicio28.java

import java.util.*;
import java.io.*;
// ThreadPool 
import java.text.*; 

public class Ejercicio28 {
	// Inicializamos el método main
	public static void main(String[] args) throws Exception{
        // Creating an empty HashMap 
        Map<Character, Integer> map = new LinkedHashMap<>();
		Map<Character, Integer> map_sorted = new LinkedHashMap<>(); 
		// Creamos un ArrayList
		ArrayList<Integer> lista = new ArrayList<Integer>();
		
		// Leemos la biblia 
		File file = new File("biblia_completa.txt");
        Scanner sc = new Scanner(file);
		
		// Generamos un ArrayList de StringBuilders
		ArrayList<StringBuilder> bibliaCompleta = new ArrayList<StringBuilder>();
		
		int i = 1;
		int j = 0;
        while (sc.hasNextLine()){
			if(i == 7038) {
				j++;
				i = 1;
			}
			String linea = sc.nextLine();
			/*for(char c: linea.toCharArray()){
				map.put(c,map.getOrDefault(c,0)+1);
			}*/
			if(i == 1)
				bibliaCompleta.add(linea);
			else
				bibliaCompleta.set(j, bibliaCompleta.get(j) + linea);
			i++;
		}
		
		System.out.println(bibliaCompleta.get(0));
		
		/* // Agregamos los valores a la lista
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
            System.out.println(e.getKey() + " " + e.getValue()); */
    } 
	
}
