// Importamos bibliotecas
import java.lang.*;

// Iniciamos la clase principal
public class MainHilos2 {
    // Declaramos las variables compartidas
    public static char[] arreglo = new char[10];
    public static int tope = 0;

    // Inicializamos el método main
    public static void main(String args[]) throws Exception {

        // Implementamos los hilos
        ActividadProductor ta1 = new MainHilos2().new ActividadProductor();
		ActividadConsumidor ta2 = new MainHilos2().new ActividadConsumidor();
        Thread t1 = new Thread(ta1, "productor");
        Thread t2 = new Thread(ta2, "consumidor");
        t1.start();
        t2.start();

        // Esperamos a que los hilos terminen su ejecución
        t1.join();
        t2.join();
    }

    // Implementamos clases para cada hilo
	private class ActividadProductor implements Runnable {
		private int current_tope = 0;
		
		public void run() {
            while(true) {
				try {
                    push();
                } catch (Exception e) {
                    e.printStackTrace();
                }
			}
        }
		
		public synchronized void push() throws Exception {
			int current_tope = tope;
			// Esperamos un tiempo aleatorio
            int tp = (int)(Math.random() * 1000);
            Thread.sleep(tp);
            if(tope != 9) {
                // Agregamos un elemento a la pila
                arreglo[tope] = 'X';
                tope++;
            }
			// Reiniciamos e imprimimos la pantalla con el tercer hilo
			ActividadEraser ta3 = new MainHilos2().new ActividadEraser(current_tope);
            Thread t3 = new Thread(ta3, "eraser");
            t3.start();
		}
	}
	
	private class ActividadConsumidor implements Runnable {
		private int current_tope = 0;
		
		public void run() {
            while(true) {
				try {
                    pop();
                } catch (Exception e) {
                    e.printStackTrace();
                }
			}
        }
		
		public synchronized void pop() throws Exception {
			int current_tope = tope;
			// Esperamos un tiempo aleatorio
            int tc = (int)(Math.random() * 1000);
            Thread.sleep(tc);
            if(tope != 0) {
                // Removemos un elemento de la pila
                arreglo[tope] = ' ';
                tope--;
            }
			// Reiniciamos e imprimimos la pantalla con el tercer hilo
			ActividadEraser ta3 = new MainHilos2().new ActividadEraser(current_tope);
            Thread t3 = new Thread(ta3, "eraser");
            t3.start();
		}
	}
	
	private class ActividadEraser implements Runnable {
		private int current_tope = 0;
		
		// Constructor
		private ActividadEraser(int number) {
			this.current_tope = number;
		}
		
		public void run() {
			try {
                print_n_erase();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
		
		public synchronized void print_n_erase() {
			// Realizamos la limpieza de la pantalla
            System.out.print("\033[H\033[2J");
            System.out.flush();
            // Imprimimos el contenido del arreglo
            for (int i = 0; i < 10; i++) {
                System.out.print(arreglo[i]);
            }
            // Imprimimos el tope actual
            System.out.println("\nSoy el graficador tope = " + tope);
			System.out.println("Tope actual = " + current_tope);
		}
	}
}
