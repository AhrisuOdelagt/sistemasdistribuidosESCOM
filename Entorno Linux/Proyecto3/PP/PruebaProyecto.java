// Creamos una clase de pruebas
import java.util.*;
class PruebaProyecto {
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
		// Generamos una String de números para el estado final
		StringBuilder sb = new StringBuilder();
		for (Integer num : end) {
			sb.append(num).append(" ");
		}

		String numerosComoString = sb.toString().trim();
		// Creamos un Puzzle y lo colocamos como la raíz del árbol
		Puzzle rompecabezas = new Puzzle(init, 0, end);
		
		// Creamos el nodo que será gestionado en la creación del árbol
		Nodo nodoActual = new Nodo(rompecabezas);
		ArbolNArio arbol = new ArbolNArio(nodoActual.valor);
		// Creamos el array que contendrá a los movimientos del Puzzle actual
		ArrayList<Puzzle> mov = new ArrayList<Puzzle>();
		// Creamos una lista para almacenar los movimientos ya agregados al árbol
		ArrayList<Puzzle> movimientosAgregados = new ArrayList<>();
		movimientosAgregados.add(rompecabezas);
		// Creamos una bandera
		boolean check = false;
		
		// Resolvemos el rompecabezas
		while(true) {
			System.out.println("\nNodo actual:");
			System.out.println("" + nodoActual.valor);
			if(nodoActual.valor.idPuzzle().equals(numerosComoString))
				break;
			mov = nodoActual.valor.posiblesMov();
			// Ordenamos los movimientos por costo heurístico ascendente
			Collections.sort(mov, new Comparator<Puzzle>() {
				@Override
				public int compare(Puzzle p1, Puzzle p2) {
					return Integer.compare(p1.costoH(), p2.costoH());
				}
			});
			
			// Añadimos los movimientos al árbol
			for(Puzzle jigsaw : mov) {
				// Verificamos que el movimiento actual no exista en la lista de movimientos ya agregados
				for (Puzzle movis : movimientosAgregados) {
					if(jigsaw.idPuzzle().equals(movis.idPuzzle())) {
						check = true;
					}
				}
				if(!check) {
					nodoActual.agregarHijo(new Nodo(jigsaw));
					movimientosAgregados.add(jigsaw);
				}
				check = false;
			}
			
			// Imprimimos los hijos del nodo
			System.out.println("Hijos del nodo actual:");
			for (Nodo hijo : nodoActual.hijos) {
				arbol.raiz.agregarHijo(hijo);
				System.out.println(hijo.valor);
			}
			
			// Tomamos el movimiento de menor costo y lo colocamos como el nodo actual
			ArrayList<Nodo> nodos = new ArrayList<Nodo>(nodoActual.hijos);
			Nodo nodoMenorCosto = Collections.min(nodoActual.hijos, Comparator.comparingInt(n -> n.valor.costoH()));
			nodoActual = nodoMenorCosto;
			
			try {
				// Espera un segundo (1000 milisegundos)
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// Manejo de excepciones si ocurre un problema al dormir el hilo
				e.printStackTrace();
			}
		}
		
		// Informamos si el Puzzle se ha completado
		System.out.println("El puzzle esta completo y la solucion es: \n");
		System.out.println(nodoActual.valor);
		
		// Realizamos un recorrido en profundidad del árbol
		System.out.println("\nRecorrido en profundidad del arbol n-ario:");
        arbol.recorridoDFS(arbol.raiz);
	}
}
