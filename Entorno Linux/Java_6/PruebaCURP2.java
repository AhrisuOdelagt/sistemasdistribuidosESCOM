// Importamos las bibliotecas necesarias
import java.util.ArrayList;
import java.util.Iterator;

// Inicializamos la clase de prueba
class PruebaCURP2 {
	// Iniciamos la función main
	public static void main(String args[]) {
		// Leemos los valores de los argumentos
		int n = Integer.parseInt(args[0]);
		// Generamos el arrayList para los Curp
		ArrayList<String> curps = new ArrayList<String>();
		// Instanciamos la clase main para generar los CURP
		Main generador = new Main();
		// Creamos el iterador para la lista
		Iterator<String> curps_itr = curps.iterator();
		// Generamos la ArrayList desordenada de los CURP (y ordenamos internamente)
		System.out.println("Lista desordenada: ");
		for(int i = 0; i < n; i++) {
			if (i == 0)
				curps.add(i, Main.getCURP());
			else {
				// Añadimos un CURP nuevo
				curps.add(i, Main.getCURP());
				// Revisamos la lista creada hasta ahora
				int j = 0;
				
				while(curps_itr.hasNext()) {
					if(j == 0) {
						String curp_init_1 = curps_itr.next().substring(0, 4);
				}
			}
		}
	}
}
