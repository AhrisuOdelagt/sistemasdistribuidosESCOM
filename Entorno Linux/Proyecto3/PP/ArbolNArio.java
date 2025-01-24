class ArbolNArio {
    Nodo raiz;
    
    // Constructor
    public ArbolNArio(Puzzle valor) {
        raiz = new Nodo(valor);
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
