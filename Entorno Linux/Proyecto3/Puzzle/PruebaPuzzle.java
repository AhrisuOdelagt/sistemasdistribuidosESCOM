// Creamos una clase de pruebas
import java.util.ArrayList;
class PruebaPuzzle {
	// Creamos la función Main
	public static void main(String args[]){
		// Creamos el estado inicial del Puzzle
		ArrayList<Integer> init = new ArrayList<Integer>();
		init.add(1);
		init.add(2);
		init.add(3);
		init.add(0);
		init.add(8);
		init.add(4);
		init.add(7);
		init.add(6);
		init.add(5);
		// Creamos el estado final del Puzzle
		ArrayList<Integer> end = new ArrayList<Integer>();
		end.add(1);
		end.add(2);
		end.add(3);
		end.add(8);
		end.add(0);
		end.add(4);
		end.add(7);
		end.add(6);
		end.add(5);
		// Creamos un Puzzle y lo imprimimos
		Puzzle rompecabezas = new Puzzle(init, 0, end);
		System.out.println(rompecabezas);
		
		// Generamos sus movimientos posibles
		ArrayList<Puzzle> mov = new ArrayList<Puzzle>();
		mov = rompecabezas.posiblesMov();
		
		// Imprimimos dichos movimientos
		System.out.println("Movimientos: \n");
		for(Puzzle jigsaw : mov) {
			jigsaw.calcularCosto();
			System.out.println("" + jigsaw + jigsaw.costoH() + "\n");
		}
	}
}
