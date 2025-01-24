import java.util.ArrayList;
import java.util.List;

class Nodo {

    int valor;
    List<Nodo> hijos;

    public Nodo(int valor) {
        this.valor = valor;
        this.hijos = new ArrayList<>();
    }

    // Método para agregar un hijo al nodo
    public void agregarHijo(Nodo hijo) {
        hijos.add(hijo);
    }
}
