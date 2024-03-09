// Clase 7 --- Problema 6

// Generamos la Clase del archivo
public class Problema6 {
	// Iniciamos el método main
	public static void main(String args[]) {
		// Tomamos el valor del argumento
		int n = Integer.parseInt(args[0]);
		// Definimos el resto de variables a utilizar
		int palabras = n * 4;
		String cadena_ipn = "IPN ";
		int contador_ipn = 0, contador_index[] = new int[20];;
		StringBuilder cadenota = new StringBuilder("");
		StringBuilder leer_palabra = new StringBuilder("");
		
		// Generamos las palabras
		for(int i = 0; i < palabras; i++) {
			// Creamos las mayúsculas aleatorias
			if(((i + 1) % 4) != 0)
				cadenota.append((char)(Math.random() * 25 + 65));
			else
				cadenota.append((char)(32));
		}
		
		// Leemos las palabras
		int i = 0, j = 0;
		while((cadenota.indexOf(cadena_ipn, i + 1)) != -1) {
			contador_ipn++;
			i = cadenota.indexOf(cadena_ipn, i + 1);
			contador_index[j] = i;
			j++;
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
