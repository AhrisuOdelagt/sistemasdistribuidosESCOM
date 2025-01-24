// Importamos los ArrayList
import java.util.*;

// Creamos la clase de prueba
public class PruebaPoligono {
	// Generamos el método main
	public static void main(String args[]) {
		// Leemos la cantidad de polígonos que se van a generar
		int totalPoligonos = Integer.parseInt(args[0]);
		// Creamos el ArrayList para incluir a los polígonos
		ArrayList<PoligonoReg> poligonos = new ArrayList<PoligonoReg>();
		
		// Generamos los polígonos con las especificaciones solicitadas
		for(int i = 0; i < totalPoligonos; i++) {
			int numLados = (int)(Math.random() * 10 + 3);
			double radioPol = (double)(Math.random() * 10 + 1);
			poligonos.add(new PoligonoReg(numLados, radioPol));
		}
		
		// Imprimimos los polígonos generados
		for(PoligonoReg poligono: poligonos) {
			System.out.println(poligono);
		}
		
		// System.out.println(heptagono);
	}
}
