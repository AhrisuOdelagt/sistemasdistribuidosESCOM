class ArbolNArio {
    Nodo raiz;
    
    // Constructor
    public ArbolNArio(int valor) {
        raiz = new Nodo(valor);
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
        ArbolNArio arbol = new ArbolNArio(1);
        // Agregamos hijos al nodo raíz
        Nodo nodo2 = new Nodo(2);
        Nodo nodo3 = new Nodo(3);
        Nodo nodo4 = new Nodo(4);
        Nodo nodo5 = new Nodo(5);
        Nodo nodo6 = new Nodo(6);
        arbol.raiz.agregarHijo(nodo2);
        arbol.raiz.agregarHijo(nodo3);
        arbol.raiz.agregarHijo(nodo4);
        nodo2.agregarHijo(nodo5);
        nodo2.agregarHijo(nodo6);
        // Realizamos un recorrido en profundidad del árbol
        System.out.println("Recorrido en profundidad del arbol n-ario:");
        arbol.recorridoDFS(arbol.raiz);
    }
}
