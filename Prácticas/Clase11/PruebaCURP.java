// Importamos las bibliotecas necesarias
import java.util.ArrayList;
import java.util.Iterator;

// Inicializamos la clase de prueba
class PruebaCURP {
	// Iniciamos la función main
	public static void main(String args[]) {
		// Leemos los valores de los argumentos
		int n = Integer.parseInt(args[0]);
		char sexo = args[1].charAt(0); 
		// Generamos el arrayList para los Curp
		ArrayList<String> curps = new ArrayList<String>();
		// Instanciamos la clase main para generar los CURP
		Main generador = new Main();
		// Comenzamos a generar los n CURP
		for(int i = 0; i < n; i++) {
			curps.add(i, Main.getCURP());
		}
		// Imprimimos los n CURP generados
		for(String curp: curps) {
			System.out.println("CURP: " + curp);
		}
		
		// Creamos el iterador para la lista
		Iterator<String> curps_itr = curps.iterator();
		// Iniciamos la iteración
		while(curps_itr.hasNext()) {
			String curp = curps_itr.next();
			// Eliminamos el elemento si coincide el con el sexo declarado por el usuario
			if(curp.charAt(10) == sexo)
				curps_itr.remove();
			
			// Imprimimos el elemento si no se eliminó
			else
				System.out.println("" + curp);
		}
	}
}
