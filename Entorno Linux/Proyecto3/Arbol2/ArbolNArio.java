import java.util.ArrayList;

class ArbolNArio {
    /*MI CÓDIGO*/
    ArrayList<Nodo> nodosArbol = new ArrayList<Nodo>();

    // Constructor
    public ArbolNArio(int valor) {
        nodosArbol.add(new Nodo(valor));
    }

    // Método para realizar un recorrido en profundidad (DFS) del árbol
    public void recorridoDFS(Nodo nodo) {
        if (nodo != null) {
            System.out.print(nodo.valor + " ");
            for (Nodo hijo : nodo.hijos) {
                recorridoDFS(hijo);
            }
        }
    }

    public static void main(String[] args) {
        // Creamos el nodo que será gestionado en la creación del árbol
        Nodo nodoActual = new Nodo(1);
        ArbolNArio arbol = new ArbolNArio(nodoActual.valor);

        // Generamos un ArrayList con los mismos tres valores
        ArrayList<Integer> mov = new ArrayList<Integer>();
        mov.add(2);
        mov.add(3);
        mov.add(4);

        // Imprimimos el nodo raíz
        System.out.println("El nodo raiz es es:");
        System.out.println(arbol.nodosArbol.get(0).valor + "\n");

        // Generamos un valor para determinar el total de recorridos actuales
        int contador = 0;

        // Agregamos nodos de forma automática
        while (contador != 3) {
            // Sustituímos valores de mov para las siguientes iteraciones
            if (contador == 1) {
                mov.set(0, 5);
                mov.set(1, 6);
                mov.set(2, 7);
            }
			if (contador == 2) {
                mov.set(0, 8);
                mov.set(1, 9);
                mov.set(2, 0);
            }

            // Generamos los nodos para agregar en el árbol
            for (Integer numero : mov) {
                Nodo nuevoNodo = new Nodo(numero);
                for (Nodo nodo : arbol.nodosArbol) {
                    if (nodo.valor == nodoActual.valor) {
                        nodo.agregarHijo(nuevoNodo);
                        arbol.nodosArbol.add(nuevoNodo);
                        break;
                    }
                }
            }

            // Actualizamos el nodo actual
            nodoActual = new Nodo(mov.get(0));

            // Incrementamos el contador
            contador++;
        }

        // Realizamos un recorrido en profundidad del árbol
        System.out.println("Recorrido en profundidad del arbol n-ario:");
        arbol.recorridoDFS(arbol.nodosArbol.get(0));

        // Imprimimos la profundidad del árbol
        System.out.println("\nLa profundidad del arbol n-ario es: " + contador);
    }
}
