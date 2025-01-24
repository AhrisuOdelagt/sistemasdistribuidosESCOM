/*    --- Información del proyecto ----
	No. de proyecto: 3
	Nombre completo: Franco Olvera Demian Oder
	Grupo: 7CM1
*/

// Clase principal del proyecto
import java.util.*;    // Para permitir el uso de ArrayList
class ProyectoTres {
	// Creamos la función Main
	public static void main(String args[]){
		// Estado inicial del rompecabezas
		/* Original */
		// ArrayList<Integer> init = new ArrayList<>(Arrays.asList(2, 8, 3, 1, 6, 4, 7, 0, 5));
		/* 10 Movimientos (1) */
		// ArrayList<Integer> init = new ArrayList<>(Arrays.asList(2, 8, 3, 1, 5, 6, 0, 7, 4));
		/* 10 Movimientos (2) */
		ArrayList<Integer> init = new ArrayList<>(Arrays.asList(2, 8, 0, 1, 6, 3, 7, 5, 4));
		// Estado final del rompecabezas
		ArrayList<Integer> end = new ArrayList<>(Arrays.asList(1, 2, 3, 8, 0, 4, 7, 6, 5));
		
		// Generamos una String de números para el estado final
		StringBuilder sb = new StringBuilder();
		for (Integer num : end) {
			sb.append(num).append(" ");
		}

		String numerosComoString = sb.toString().trim();
		// Creamos un Puzzle y lo colocamos como la raíz del árbol
		Puzzle rompecabezas = new Puzzle(init, 0, end);
		System.out.println("El puzzle esta inicial es:");
		System.out.println(rompecabezas);
		
		// Creamos el nodo que será gestionado en la creación del árbol
		Nodo nodoActual = new Nodo(rompecabezas);
		ArbolNArio arbol = new ArbolNArio(nodoActual.valor);
		// Creamos el array que contendrá a los movimientos del Puzzle actual
		ArrayList<Puzzle> mov = new ArrayList<Puzzle>();
		// Creamos una lista para almacenar los movimientos ya agregados al árbol
		ArrayList<Puzzle> movimientosAgregados = new ArrayList<>();
		movimientosAgregados.add(rompecabezas);
		// Creamos una bandera para eliminar movimientos repetidos
		boolean check = false;
		// Creamos un indicador de cuántos movimientos se han realizado
		int contador = 0;
		
		// Resolvemos el rompecabezas
		while(true) {
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
			
			// Añadimos los movimientos al nodo actual
			for(Puzzle jigsaw : mov) {
				// Calculamos inicialmente el costo
				jigsaw.calcularCosto();
				// Verificamos que el movimiento actual no exista en la lista de movimientos ya agregados
				for (Puzzle movis : movimientosAgregados) {
					if(jigsaw.idPuzzle().equals(movis.idPuzzle())) {
						check = true;
					}
				}
				// Si no existe, agregamos el movimiento al árbol y a la lista
				if(!check) {
					nodoActual.agregarHijo(new Nodo(jigsaw));
					movimientosAgregados.add(jigsaw);
				}
				check = false;
				// Construímos el árbol
				Nodo nuevoNodo = new Nodo(jigsaw);
                for (Nodo nodo : arbol.nodosArbol) {
                    if (nodo.valor == nodoActual.valor) {
                        nodo.agregarHijo(nuevoNodo);
                        arbol.nodosArbol.add(nuevoNodo);
                        break;
                    }
                }
			}
			
			// Tomamos el movimiento de menor costo y lo colocamos como el nodo actual
			ArrayList<Nodo> nodos = new ArrayList<Nodo>(nodoActual.hijos);
			// Verificamos si la lista está vacía (ya no hay movimientos posibles dado que sólo habría repeticiones)
			// Esto impide que el programa entre en bucles
			if (nodos.isEmpty()) {
				System.out.println("\nNo hay mas movimientos posibles. El rompecabezas no tiene solucion.");
				System.exit(1);
			}
			// Tomamos el siguiente nodo como aquel que tuvo el menor costo
			Nodo nodoMenorCosto = Collections.min(nodoActual.hijos, Comparator.comparingInt(n -> n.valor.costoH()));
			nodoActual = nodoMenorCosto;
			
			// Incrementamos el contador de movimientos
			contador++;
		}
		
		// Informamos si el Puzzle se ha completado
		System.out.println("El puzzle esta completo y la solucion es:");
		System.out.println(nodoActual.valor);
		System.out.println("La solucion tomo " + contador + " movimientos.\n");
		
		// Realizamos un recorrido en anchura del árbol (imprimiendo el orden de las soluciones)
		System.out.println("\nRecorrido en anchura del arbol n-ario: \n");
        // arbol.recorridoDFS(arbol.nodosArbol.get(0));    // El nodo 0 de la lista del árbol es la raíz
		// AL ESTAR BASADO EN HEURÍSTICA, EL ÁRBOL DISMINUYE MUCHO EN COMPLEJIDAD DADO QUE ÚNICAMENTE
		// LOS NODOS CON MENOR COSTO TIENEN HIJOS
		for(Puzzle jigsaw : movimientosAgregados)
			System.out.println(jigsaw);
	}
}
