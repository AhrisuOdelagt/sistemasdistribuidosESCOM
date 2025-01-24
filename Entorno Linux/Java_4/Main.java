// Clase principal (de prueba)
public class Main {
	public static void main(String args[]) {
		System.out.println("Clase de prueba\n");
		// Generamos un objeto Rectángulo
		Rectangulo rect1 = new Rectangulo(2.0, 3.0, 5.0, 1.0);
		// Calculamos e imprimimos el área
		System.out.println("El area del rectangulo es: " + rect1.area() + ".");
		System.out.println("El numero de lados del rectangulo es: " + rect1.numeroLados() + ".");
		System.out.println("El perimetro del rectangulo es: " + rect1.imprimePerimetro() + ".");
	}
}
