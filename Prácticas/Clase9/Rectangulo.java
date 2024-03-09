public class Rectangulo extends Figura implements Perimetro {
	private Coordenada superiorIzq, inferiorDer;
  
    public Rectangulo(){
       superiorIzq = new Coordenada(0,0);
       inferiorDer = new Coordenada(0,0);
    }
  
    public Rectangulo(double xSupIzq, double ySupIzq, double xInfDer, double yInfDer){
       superiorIzq = new Coordenada(xSupIzq, ySupIzq);
       inferiorDer = new Coordenada(xInfDer, yInfDer); 
	   numLados = 4;
    }
	
	// Metodo getter de la coordenada inferior derecha (Override desde figura)
	@Override
    public int numeroLados( ) { return numLados; }
  
	//Sobreescritura del método de la superclase objeto para imprimir con System.out.println( )
	@Override
	public String toString( ) {
       return "Esquina superior izquierda: " + superiorIzq + "\tEsquina superior derecha:" + inferiorDer + "\n";
	}
	
	// Implementamos el método Area
	@Override
    public double area(){
        double alto = superiorIzq.ordenada() - inferiorDer.ordenada();
        double ancho = inferiorDer.abcisa() - superiorIzq.abcisa();
        return alto * ancho;
    }
	
	// Implementamos el método perímetro desde la interfaz
	@Override
	public double imprimePerimetro(){
		double alto = superiorIzq.ordenada() - inferiorDer.ordenada();
        double ancho = inferiorDer.abcisa() - superiorIzq.abcisa();
		return (2 * alto) + (2 * ancho);
	}
}
