// Importamos bibliotecas
import java.lang.*;

// Iniciamos la clase principal
public class MainHilos {
	// Declaramos la variable compartida
	public static int var_compartida = 0;
	
	// Inicializamos el método main
	public static void main(String args[]) throws Exception {
		// Obtenemos el parámetro desde consola
		int n = Integer.parseInt(args[0]);
		
		// Implementamos los hilos
		ActividadHilo t = new MainHilos().new ActividadHilo(n);
		Thread t1 = new Thread(t, "Hilo1");
		Thread t2 = new Thread(t, "Hilo2");
		t1.start();
		t2.start();
		
		// Cerramos el hilo
		t1.join(0);
		t2.join(0);
		
		System.out.print(var_compartida + "\n");
	}
	
	// Implementamos una clase que implemente runnable
	private class ActividadHilo implements Runnable {
		private int number = 0;
		
		// Declaramos un constructor para pasarle el parámetro de la consola
		private ActividadHilo(int number) {
			this.number = number;
		}
		
		public void run() {
			// Iniciamos el ciclo for
			int i = 0;
			for (i = 0; i < number; i++)
				modifica(Thread.currentThread().getName());
		}
		
		public synchronized void modifica(String hilo_id) {
			if(hilo_id.equals("Hilo1")) {
				var_compartida++;
				// System.out.print("Soy " + hilo_id + " | Valor de var_compartida: " + var_compartida + "\n");
			}
			else if(hilo_id.equals("Hilo2")) {
				var_compartida--;
				// System.out.print("Soy " + hilo_id + " | Valor de var_compartida: " + var_compartida + "\n");
			}
			
		}
	}
}
