// Clase PruebaRectangulo
public class PruebaRectangulo {
	public static void main (String[] args) {
		// Se crea un rectángulo con la clase Rectángulo
		// Rectangulo rect1 = new Rectangulo(2,3,5,1);
		Coordenada superiorIzq = new Coordenada(2.0,3.0);
		Coordenada inferiorDer = new Coordenada(5.0,1.0);
		try {
			Rectangulo rect1 = new Rectangulo(inferiorDer, superiorIzq);
			double ancho, alto;
			// Se calcula el área de un rectángulo
			System.out.println("Calculando el area de un rectangulo dadas sus coordenadas en un plano cartesiano:");
			System.out.println(rect1);
			alto = rect1.superiorIzquierda().ordenada() - rect1.inferiorDerecha().ordenada();
			ancho = rect1.inferiorDerecha().abcisa() - rect1.superiorIzquierda().abcisa();
			System.out.println("El area del rectangulo es = " + ancho*alto);
		}
		catch(IllegalArgumentException e) {
			System.out.println("Error al crear el rectangulo: " + e.getMessage());
		}
	}
}
