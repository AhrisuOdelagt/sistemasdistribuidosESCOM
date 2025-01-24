// Clase PoligonoIrregular
public class PoligonoIrregular {
	// Atributos
	private Coordenada[] vertices;
	private int num_vert;
	
	// Constructor
	public PoligonoIrregular(int num_vert) {
		vertices = new Coordenada[num_vert];
		this.num_vert = num_vert;
		// Generamos los vértices aleatoriamente
		int i = 0;
		for(i = 0; i < this.num_vert; i++) {
			Coordenada vertice = new Coordenada((int)(Math.random() * 20 - 10), (int)(Math.random() * 20 - 10));
			this.vertices[i] = vertice;
		}
	}
	
	// Métodos
	// Método getter de vertices
    public Coordenada[] vertices() { 
		return vertices;
	}
	// Método para modificar vértices
	public void modificaVertice(Coordenada nueva_Cor, int enesimo) {
		if(enesimo > this.num_vert || enesimo <= 0) {
			throw new IllegalArgumentException("Numero de vertices invalido.");
		}
		this.vertices[enesimo - 1] = nueva_Cor;
		System.out.println("Vertice modificado.\n");
	}
	// Sobreescritura del método de la superclase objeto para imprimir los vértices del polígono
	@Override
	public String toString() {
		String ver = "";
		int i = 0;
		for(i = 0; i < this.num_vert; i++) {
			ver = ver + this.vertices[i] + "\n";
		}
		return "Poligono: \n" + ver;
	}
}
