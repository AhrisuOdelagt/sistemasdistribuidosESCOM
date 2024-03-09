// Clase Rectángulo
public class Rectangulo {
	// Atributos
	private Coordenada superiorIzq, inferiorDer;
  
	// Constructores
	// Vacío
	public Rectangulo() {
		superiorIzq = new Coordenada(0,0);
		inferiorDer = new Coordenada(0,0);
	}
	// Por Coordenadas
	public Rectangulo(Coordenada superiorIzq, Coordenada inferiorDer) {
		if(superiorIzq.ordenada() - inferiorDer.ordenada() <= 0 || inferiorDer.abcisa() - superiorIzq.abcisa() <= 0) {
			throw new IllegalArgumentException("La primer coordenada no se encuentra arriba y a la izquierda de la segunda.");
		}
		this.superiorIzq = superiorIzq;
		this.inferiorDer = inferiorDer;
	}
	// Completo
	public Rectangulo(double xSupIzq, double ySupIzq, double xInfDer, double yInfDer) {
		superiorIzq = new Coordenada(xSupIzq, ySupIzq);
		inferiorDer = new Coordenada(xInfDer, yInfDer);       
	}
	
	// Métodos
	//Metodo getter de la coordenada superior izquierda
	public Coordenada superiorIzquierda() {
		return superiorIzq;
	}
	//Metodo getter de la coordenada inferior derecha
	public Coordenada inferiorDerecha() {
		return inferiorDer;
	}
	//Sobreescritura del método de la superclase objeto para imprimir con System.out.println( )
	@Override
	public String toString() {
		return "Esquina superior izquierda: " + superiorIzq + "\tEsquina superior derecha:" + inferiorDer + "\n";
	}
}
