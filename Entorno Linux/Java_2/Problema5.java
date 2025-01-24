// Clase 7 --- Problema 5

// Generamos la Clase del archivo
public class Problema5 {
	// Iniciamos el método main
	public static void main(String args[]) {
		// Tomamos el valor del argumento
		int n = Integer.parseInt(args[0]);
		// Definimos el resto de variables a utilizar
		int palabras = n * 4;
		String cadena_ipn = "IPN ", leer_cadena = "";
		int contador_ipn = 0, contador_index[] = new int[20];
		char cadenota[] = new char[palabras];
		
		// Generamos las palabras
		for(int i = 0; i < palabras; i++) {
			// Creamos las mayúsculas aleatorias
			if(((i + 1) % 4) != 0)
				cadenota[i] += (char)(Math.random() * 25 + 65);
			else
				cadenota[i] += (char)32;
		}
		
		// Leemos las palabras
		int j = 0;
		for(int i = 0; i < palabras; i++) {
			// Leemos de cuatro en cuatro índices
			if(((i + 1) % 4) != 0)
				leer_cadena += (char)cadenota[i];
			else {
				leer_cadena += (char)cadenota[i];
				if(leer_cadena.equals(cadena_ipn)) {
					contador_ipn++;
					contador_index[j] = i - 3;
					j++;
				}
				leer_cadena = "";
			}
		}
		
		// Imprimimos las coincidencias
		System.out.println("\nLa cadena " + cadena_ipn + " fue leida: " + contador_ipn + " veces.");
		// Imprimimos los índices de las coincidencias
		System.out.println("\nLas coincidencias fueron: ");
		for(j = 0; j < 20; j++) {
			if(contador_index[j] == 0)
				break;
			else
				System.out.println("Coincidencia " + (int)(j + 1) + ": " + contador_index[j]);
		}
	}
}
