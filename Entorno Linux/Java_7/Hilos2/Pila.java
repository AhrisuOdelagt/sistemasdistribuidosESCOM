public class Pila {
    private static final int TAMANO_PILA = 10;
    private static char[] pila = new char[TAMANO_PILA];
    private static int tope = -1;
    public static void main(String[] args) {
        Thread productor = new Thread(() -> {
            while (true) {
                try {
                    char elemento = (char) ('A' + Math.random() * ('Z' - 'A' + 1));
                    agregarElemento(elemento);
                    Thread.sleep((long) (Math.random() * 1000)); // tp aleatorio
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread consumidor = new Thread(() -> {
            while (true) {
                try {
                    quitarElemento();
                    Thread.sleep((long) (Math.random() * 1000)); // tc aleatorio
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread impresor = new Thread(() -> {
            while (true) {
                try {
                    imprimirPila();
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        productor.start();
        consumidor.start();
        impresor.start();
    }
    private static synchronized void agregarElemento(char elemento) {
        if (tope < TAMANO_PILA - 1) {
            pila[++tope] = elemento;
            System.out.println("Productor agregó: " + elemento);
            imprimirPila();
        }
    }
    private static synchronized void quitarElemento() {
        if (tope >= 0) {
            System.out.println("Consumidor quitó: " + pila[tope]);
            tope--;
            imprimirPila();
        }
    }
    private static void imprimirPila() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
        //    for (int i = 0; i < 50; ++i) System.out.println();
        }
        System.out.println("tope = " + tope + "):");
        for (int i = tope; i >= 0; i--) {
            System.out.print(pila[i]);
        }
        System.out.println("");
    }
}
