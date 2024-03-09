// Clase Coordenada
public class Coordenada {
	// Atributos
	private double x, y;
	
	// Constructores
	public Coordenada(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	// Métodos
	// Método getter de x
    public double abcisa() { 
		return x;
	}
    // Método getter de y
    public double ordenada() {
		return y;
	}
	//Sobreescritura del método de la superclase objeto para imprimir con System.out.println( )
	@Override
	public String toString( ) {
		return "[" + x + ", " + y + "]";
	}
}
