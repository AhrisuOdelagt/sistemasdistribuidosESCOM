// Clase PruebaRectangulo
public class PruebaPoligono {
	public static void main (String[] args) {
		// Se crea un polígono con la clase PoligonoIrregular
		PoligonoIrregular pol_1 = new PoligonoIrregular(7);
		
		// Imprimimos el polígono irregular
		System.out.println(pol_1);
		
		try {
			// Modificamos el vértice 4
			Coordenada nueva = new Coordenada(-4.444, -4.444);
			pol_1.modificaVertice(nueva, 4);
			
			// Volvemos a imprimir el poligono
			System.out.println(pol_1);
		}
		catch (IllegalArgumentException e){
			System.out.println("Error al modificar el vertice: " + e.getMessage());
		}
	}
}
