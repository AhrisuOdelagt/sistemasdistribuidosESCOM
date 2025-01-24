// Clase PruebaRectangulo
public class PruebaPoligono {
	public static void main (String[] args) {
		// Se crea un polígono con la clase PoligonoIrregular
		PoligonoIrregular pol_1 = new PoligonoIrregular();
		
		// Imprimimos el polígono irregular
		System.out.println(pol_1);
		
		// Añadimos tres vértices al polígono
		pol_1.anadeVertice();
		pol_1.anadeVertice();
		pol_1.anadeVertice();
		pol_1.anadeVertice();
		pol_1.anadeVertice();
		pol_1.anadeVertice();
		pol_1.anadeVertice();
		pol_1.anadeVertice();
		pol_1.anadeVertice();
		pol_1.anadeVertice();
			
		// Volvemos a imprimir el poligono
		System.out.println(pol_1);
	}
}
