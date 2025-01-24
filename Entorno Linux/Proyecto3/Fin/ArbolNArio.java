/*    --- Información del proyecto ----
	No. de proyecto: 3
	Nombre completo: Franco Olvera Demian Oder
	Grupo: 7CM1
*/

// CÓDIGO LEVEMENTE MODIFICADO
// SE MODIFICA EL CÓDIGO PARA FUNCIONAR CON OBJETOS DE TIPO PUZZLE EN LUGAR DE ENTEROS
import java.util.*;    // Una importación para implementar un ArrayList en el árbol
class ArbolNArio {
    /*MI CÓDIGO*/
    ArrayList<Nodo> nodosArbol = new ArrayList<Nodo>();    // ArrayList de nodos del árbol (para poder automatizar su manipulación)

    // Constructor
    public ArbolNArio(Puzzle valor) {
        nodosArbol.add(new Nodo(valor));
    }

    // Método para realizar un recorrido en profundidad (DFS) del árbol
    public void recorridoDFS(Nodo nodo) {
        if (nodo != null) {
            System.out.print(nodo.valor + "\n");
            for (Nodo hijo : nodo.hijos) {
                recorridoDFS(hijo);
            }
        }
    }
}
