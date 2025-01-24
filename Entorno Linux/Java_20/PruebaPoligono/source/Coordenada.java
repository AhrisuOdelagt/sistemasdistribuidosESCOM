// Clase Coordenada

public class Coordenada implements java.io.Serializable {
	// Atributos
	private double x, y;
	private double magnitud;
	
	// Constructores
	public Coordenada(double x, double y) {
		this.x = x;
		this.y = y;
		this.magnitud = Math.sqrt(x * x + y * y);
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
	// Método getter de la magnitud
	public double magnitudVer() {
		return magnitud;
	}
	//Sobreescritura del método de la superclase objeto para imprimir con System.out.println( )
	@Override
	public String toString( ) {
		return "[" + x + ", " + y + "]";
	}
}
