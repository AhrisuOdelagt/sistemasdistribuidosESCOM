// Creamos una clase de pruebas
import java.util.ArrayList;
class PruebaArbol {
	// Creamos la función Main
	public static void main(String args[]){
		// Creamos el estado inicial del Puzzle
		ArrayList<Integer> init = new ArrayList<Integer>();
		init.add(2);
		init.add(8);
		init.add(3);
		init.add(1);
		init.add(6);
		init.add(4);
		init.add(7);
		init.add(0);
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
		// Creamos un Puzzle y lo colocamos como la raíz del árbol
		Puzzle rompecabezas = new Puzzle(init, 0, end);
		ArbolNArio arbol = new ArbolNArio(rompecabezas);
		
		// Generamos nodos hijos en el árbol
		ArrayList<Puzzle> mov = new ArrayList<Puzzle>();
		
		mov = rompecabezas.posiblesMov();
		for(Puzzle jigsaw : mov) {
			arbol.raiz.agregarHijo(new Nodo(jigsaw));
		}
		
		// Realizamos un recorrido en profundidad del árbol
		System.out.println("Recorrido en profundidad del árbol n-ario:");
        arbol.recorridoDFS(arbol.raiz);
	}
}
