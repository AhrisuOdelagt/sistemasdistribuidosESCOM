// Importamos los ArrayList
import java.util.*;

// Creamos la clase para el polígono regular
public class PoligonoReg {
	// Atributos
	private int numVert;
	private double radio;
	private ArrayList<Coordenada> vertices = new ArrayList<Coordenada>();
	
	// Constructor
	public PoligonoReg(int numVert, double radio) {
		this.numVert = numVert;
		this.radio = Math.round(radio * 1000.0) / 1000.0;;
		/* Calculamos cuáles serán los vértices */
		// Hallamos la amplitud del ángulo interno del polígono
		double anguloInterno = 360.0 / numVert;
		// Encontramos la coordenada exacta de cada vértice
		for(int i = 0; i < numVert; i++) {
			double anguloRad = anguloInterno * i * (Math.PI / 180);
			double coor_x = radio * Math.cos(anguloRad);
			double coor_y = radio * Math.sin(anguloRad);
			// Redondeamos los valores de la coordenada
			coor_x = Math.round(coor_x * 1000.0) / 1000.0;
			coor_y = Math.round(coor_y * 1000.0) / 1000.0;
			vertices.add(i, new Coordenada(coor_x, coor_y));
		}
	}
	
	// Métodos
	// Método getter del número de vértices
    public int numVertices() { 
		return numVert;
	}
	// Método getter del radio
    public double radioPoligono() { 
		return radio;
	}
	// Sobreescritura del método de la superclase objeto para imprimir los vértices del polígono
	@Override
	public String toString() {
		String ver = "";
		for(Coordenada vertice : this.vertices) {
			ver = ver + vertice + "\n";
		}
		return "\nPoligono Regular de radio " + radio + ": \n" + ver;
	}
}
 