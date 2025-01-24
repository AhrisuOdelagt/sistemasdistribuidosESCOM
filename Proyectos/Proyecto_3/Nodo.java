/*    --- Información del proyecto ----
	No. de proyecto: 3
	Nombre completo: Franco Olvera Demian Oder
	Grupo: 7CM1
*/

// SE MODIFICA EL CÓDIGO PARA FUNCIONAR CON OBJETOS DE TIPO PUZZLE EN LUGAR DE ENTEROS
import java.util.ArrayList;
import java.util.List;

class Nodo {

    Puzzle valor;
    List<Nodo> hijos;

    public Nodo(Puzzle valor) {
        this.valor = valor;
        this.hijos = new ArrayList<>();
    }

    // Método para agregar un hijo al nodo
    public void agregarHijo(Nodo hijo) {
        hijos.add(hijo);
    }
}