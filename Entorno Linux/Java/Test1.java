/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Demian
 */

public class Test1 {
    // Iniciamos la función Main
    public static void main(String args[]) {
        // Obtenemos el argumento desde los parámetros
        int n = Integer.parseInt(args[0]);
		
        // Generamos la cadena
        int palabras = n * 4;
        byte[] cadenota = new byte[palabras];
		
        // Generamos las n palabras
        for(int i = 0; i < palabras; i++) {
            if(((i + 1) % 4) == 0) {
				cadenota[i] = (byte)(32);
			}
			else {
				cadenota[i] = (byte)(Math.random() * 26 + 65);
			}
        }
		
		// Realizamos la búsqueda de las palabras
		String ipn = "IPN ", palabra = "";
		int contador_ipn = 0;
		for(int i = 0; i < palabras; i++) {
			if(((i + 1) % 4) != 0) {
				palabra = palabra + (char)cadenota[i];
			}
			else {
				palabra = palabra + (char)cadenota[i];
				System.out.println(palabra);
				if(palabra.equals(ipn)) 
					contador_ipn++;
				palabra = "";
			}
			
		}
		
		// Imprimimos las veces que apareció la cadena IPN 
		System.out.println("\nEl total de veces que aparecio la cadena " + ipn + " fue: " + contador_ipn + ".");
    }
}
