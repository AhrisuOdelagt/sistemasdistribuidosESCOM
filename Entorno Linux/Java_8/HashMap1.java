// Creamos la clase de prueba
import java.io.*;
import java.util.*;

public class HashMap1 {
	public static void main(String args[]) throws Exception {
		// Declaramos el HashMap
		HashMap<Character, Integer> hm = new HashMap<Character, Integer>();
		
		// Abrimos el archivo
		File file = new File("test.txt");
		BufferedReader br = new BufferedReader(new FileReader(file));
		String st;
		while ((st = br.readLine()) != null)
			// Leemos la String resultante, carácter por carácter
			System.out.println(st);
		
		hm.put('a', 4444);
		hm.put('b', 4445);
		hm.put('c', 4446);
		hm.put('d', 4447);
		
		for(Map.Entry<Character, Integer> me : hm.entrySet()) {
			// Imprimimos llaves
			System.out.println(me.getKey() + ": " + me.getValue());
		}
	}
}
