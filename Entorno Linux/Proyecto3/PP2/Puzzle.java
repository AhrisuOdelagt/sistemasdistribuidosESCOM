// Creamos a la clase que va a retratar a los Puzzles
import java.util.ArrayList;
class Puzzle {
	/* Atributos */
	private ArrayList<Integer> contenido = new ArrayList<Integer>();
	private ArrayList<Integer> estadoFin = new ArrayList<Integer>();
	private int estado = 0;
	private int costoHeuristico = 0;
	
	/* Constructor */
	public Puzzle(ArrayList<Integer> content, int state, ArrayList<Integer> end) {
		// Creamos el Puzzle según las especificaciones
		this.contenido = content;
		this.estado = state;
		this.estadoFin = end;
	}
	
	/* Métodos */
	// GETTERS
	// Costo heurístico
	public int costoH() {
		return this.costoHeuristico;
	}
	
	// Contenido del Puzzle
	public ArrayList<Integer> contentP() {
		return this.contenido;
	}
	
	// String para identificar igualdades
	public String idPuzzle() {
		StringBuilder sb = new StringBuilder();
		for (Integer num : contenido) {
			sb.append(num).append(" ");
		}

		String numerosComoString = sb.toString().trim();
		return numerosComoString;
	}
	
	// String para identificar finales
	public String finPuzzle() {
		StringBuilder sb = new StringBuilder();
		for (Integer num : estadoFin) {
			sb.append(num).append(" ");
		}

		String numerosComoString = sb.toString().trim();
		return numerosComoString;
	}
	
	// Realizar movimientos
	public ArrayList<Puzzle> posiblesMov() {
		// Generamos una copia del contenido del Puzzle
		ArrayList<Integer> configuracionActual = this.contenido;
		ArrayList<Integer> configuracionFinal = this.estadoFin;
		// Movimientos para la casilla 0
		if(this.contenido.get(0) == 0) {
			// Movimiento 1
			ArrayList<Integer> contenidoMov1 = new ArrayList<>(configuracionActual);
			Puzzle mov1 = new Puzzle(contenidoMov1, 1, configuracionFinal);
			mov1.contenido.set(0, mov1.contenido.get(1));
			mov1.contenido.set(1, 0);
			// Movimiento 2
			ArrayList<Integer> contenidoMov2 = new ArrayList<>(configuracionActual);
			Puzzle mov2 = new Puzzle(contenidoMov2, 1, configuracionFinal);
			mov2.contenido.set(0, mov2.contenido.get(3));
			mov2.contenido.set(3, 0);
			// Retornamos los movimientos posibles
			ArrayList<Puzzle> movimientos = new ArrayList<Puzzle>();
			movimientos.add(mov1);
			movimientos.add(mov2);
			return movimientos;
		}
		// Movimientos para la casilla 1
		else if(this.contenido.get(1) == 0) {
			// Movimiento 1
			ArrayList<Integer> contenidoMov1 = new ArrayList<>(configuracionActual);
			Puzzle mov1 = new Puzzle(contenidoMov1, 1, configuracionFinal);
			mov1.contenido.set(1, mov1.contenido.get(0));
			mov1.contenido.set(0, 0);
			// Movimiento 2
			ArrayList<Integer> contenidoMov2 = new ArrayList<>(configuracionActual);
			Puzzle mov2 = new Puzzle(contenidoMov2, 1, configuracionFinal);
			mov2.contenido.set(1, mov2.contenido.get(4));
			mov2.contenido.set(4, 0);
			// Movimiento 3
			ArrayList<Integer> contenidoMov3 = new ArrayList<>(configuracionActual);
			Puzzle mov3 = new Puzzle(contenidoMov3, 1, configuracionFinal);
			mov3.contenido.set(1, mov3.contenido.get(2));
			mov3.contenido.set(2, 0);
			// Retornamos los movimientos posibles
			ArrayList<Puzzle> movimientos = new ArrayList<Puzzle>();
			movimientos.add(mov1);
			movimientos.add(mov2);
			movimientos.add(mov3);
			return movimientos;
		}
		// Movimientos para la casilla 2
		else if(this.contenido.get(2) == 0) {
			// Movimiento 1
			ArrayList<Integer> contenidoMov1 = new ArrayList<>(configuracionActual);
			Puzzle mov1 = new Puzzle(contenidoMov1, 1, configuracionFinal);
			mov1.contenido.set(2, mov1.contenido.get(1));
			mov1.contenido.set(1, 0);
			// Movimiento 2
			ArrayList<Integer> contenidoMov2 = new ArrayList<>(configuracionActual);
			Puzzle mov2 = new Puzzle(contenidoMov2, 1, configuracionFinal);
			mov2.contenido.set(2, mov2.contenido.get(5));
			mov2.contenido.set(5, 0);
			// Retornamos los movimientos posibles
			ArrayList<Puzzle> movimientos = new ArrayList<Puzzle>();
			movimientos.add(mov1);
			movimientos.add(mov2);
			return movimientos;
		}
		// Movimientos para la casilla 3
		else if(this.contenido.get(3) == 0) {
			// Movimiento 1
			ArrayList<Integer> contenidoMov1 = new ArrayList<>(configuracionActual);
			Puzzle mov1 = new Puzzle(contenidoMov1, 1, configuracionFinal);
			mov1.contenido.set(3, mov1.contenido.get(0));
			mov1.contenido.set(0, 0);
			// Movimiento 2
			ArrayList<Integer> contenidoMov2 = new ArrayList<>(configuracionActual);
			Puzzle mov2 = new Puzzle(contenidoMov2, 1, configuracionFinal);
			mov2.contenido.set(3, mov2.contenido.get(4));
			mov2.contenido.set(4, 0);
			// Movimiento 3
			ArrayList<Integer> contenidoMov3 = new ArrayList<>(configuracionActual);
			Puzzle mov3 = new Puzzle(contenidoMov3, 1, configuracionFinal);
			mov3.contenido.set(3, mov3.contenido.get(6));
			mov3.contenido.set(6, 0);
			// Retornamos los movimientos posibles
			ArrayList<Puzzle> movimientos = new ArrayList<Puzzle>();
			movimientos.add(mov1);
			movimientos.add(mov2);
			movimientos.add(mov3);
			return movimientos;
		}
		// Movimientos para la casilla 4
		else if(this.contenido.get(4) == 0) {
			// Movimiento 1
			ArrayList<Integer> contenidoMov1 = new ArrayList<>(configuracionActual);
			Puzzle mov1 = new Puzzle(contenidoMov1, 1, configuracionFinal);
			mov1.contenido.set(4, mov1.contenido.get(1));
			mov1.contenido.set(1, 0);
			// Movimiento 2
			ArrayList<Integer> contenidoMov2 = new ArrayList<>(configuracionActual);
			Puzzle mov2 = new Puzzle(contenidoMov2, 1, configuracionFinal);
			mov2.contenido.set(4, mov2.contenido.get(3));
			mov2.contenido.set(3, 0);
			// Movimiento 3
			ArrayList<Integer> contenidoMov3 = new ArrayList<>(configuracionActual);
			Puzzle mov3 = new Puzzle(contenidoMov3, 1, configuracionFinal);
			mov3.contenido.set(4, mov3.contenido.get(5));
			mov3.contenido.set(5, 0);
			// Movimiento 4
			ArrayList<Integer> contenidoMov4 = new ArrayList<>(configuracionActual);
			Puzzle mov4 = new Puzzle(contenidoMov4, 1, configuracionFinal);
			mov4.contenido.set(4, mov4.contenido.get(7));
			mov4.contenido.set(7, 0);
			// Retornamos los movimientos posibles
			ArrayList<Puzzle> movimientos = new ArrayList<Puzzle>();
			movimientos.add(mov1);
			movimientos.add(mov2);
			movimientos.add(mov3);
			movimientos.add(mov4);
			return movimientos;
		}
		// Movimientos para la casilla 5
		else if(this.contenido.get(5) == 0) {
			// Movimiento 1
			ArrayList<Integer> contenidoMov1 = new ArrayList<>(configuracionActual);
			Puzzle mov1 = new Puzzle(contenidoMov1, 1, configuracionFinal);
			mov1.contenido.set(5, mov1.contenido.get(2));
			mov1.contenido.set(2, 0);
			// Movimiento 2
			ArrayList<Integer> contenidoMov2 = new ArrayList<>(configuracionActual);
			Puzzle mov2 = new Puzzle(contenidoMov2, 1, configuracionFinal);
			mov2.contenido.set(5, mov2.contenido.get(4));
			mov2.contenido.set(4, 0);
			// Movimiento 3
			ArrayList<Integer> contenidoMov3 = new ArrayList<>(configuracionActual);
			Puzzle mov3 = new Puzzle(contenidoMov3, 1, configuracionFinal);
			mov3.contenido.set(5, mov3.contenido.get(8));
			mov3.contenido.set(8, 0);
			// Retornamos los movimientos posibles
			ArrayList<Puzzle> movimientos = new ArrayList<Puzzle>();
			movimientos.add(mov1);
			movimientos.add(mov2);
			movimientos.add(mov3);
			return movimientos;
		}
		// Movimientos para la casilla 6
		else if(this.contenido.get(6) == 0) {
			// Movimiento 1
			ArrayList<Integer> contenidoMov1 = new ArrayList<>(configuracionActual);
			Puzzle mov1 = new Puzzle(contenidoMov1, 1, configuracionFinal);
			mov1.contenido.set(6, mov1.contenido.get(3));
			mov1.contenido.set(3, 0);
			// Movimiento 2
			ArrayList<Integer> contenidoMov2 = new ArrayList<>(configuracionActual);
			Puzzle mov2 = new Puzzle(contenidoMov2, 1, configuracionFinal);
			mov2.contenido.set(6, mov2.contenido.get(7));
			mov2.contenido.set(7, 0);
			// Retornamos los movimientos posibles
			ArrayList<Puzzle> movimientos = new ArrayList<Puzzle>();
			movimientos.add(mov1);
			movimientos.add(mov2);
			return movimientos;
		}
		// Movimientos para la casilla 7
		else if(this.contenido.get(7) == 0) {
			// Movimiento 1
			ArrayList<Integer> contenidoMov1 = new ArrayList<>(configuracionActual);
			Puzzle mov1 = new Puzzle(contenidoMov1, 1, configuracionFinal);
			mov1.contenido.set(7, mov1.contenido.get(4));
			mov1.contenido.set(4, 0);
			// Movimiento 2
			ArrayList<Integer> contenidoMov2 = new ArrayList<>(configuracionActual);
			Puzzle mov2 = new Puzzle(contenidoMov2, 1, configuracionFinal);
			mov2.contenido.set(7, mov2.contenido.get(6));
			mov2.contenido.set(6, 0);
			// Movimiento 3
			ArrayList<Integer> contenidoMov3 = new ArrayList<>(configuracionActual);
			Puzzle mov3 = new Puzzle(contenidoMov3, 1, configuracionFinal);
			mov3.contenido.set(7, mov3.contenido.get(8));
			mov3.contenido.set(8, 0);
			// Retornamos los movimientos posibles
			ArrayList<Puzzle> movimientos = new ArrayList<Puzzle>();
			movimientos.add(mov1);
			movimientos.add(mov2);
			movimientos.add(mov3);
			return movimientos;
		}
		// Movimientos para la casilla 8
		else if(this.contenido.get(8) == 0) {
			// Movimiento 1
			ArrayList<Integer> contenidoMov1 = new ArrayList<>(configuracionActual);
			Puzzle mov1 = new Puzzle(contenidoMov1, 1, configuracionFinal);
			mov1.contenido.set(8, mov1.contenido.get(5));
			mov1.contenido.set(5, 0);
			// Movimiento 2
			ArrayList<Integer> contenidoMov2 = new ArrayList<>(configuracionActual);
			Puzzle mov2 = new Puzzle(contenidoMov2, 1, configuracionFinal);
			mov2.contenido.set(8, mov2.contenido.get(7));
			mov2.contenido.set(7, 0);
			// Retornamos los movimientos posibles
			ArrayList<Puzzle> movimientos = new ArrayList<Puzzle>();
			movimientos.add(mov1);
			movimientos.add(mov2);
			return movimientos;
		}
		else
			return new ArrayList<Puzzle>();
	}
	
	// Cálculo del costo heurístico del puzzle
	public void calcularCosto() {
		this.costoHeuristico = 0;
		for (int i = 0; i < this.contenido.size(); i++) {
			if (this.contenido.get(i) != this.estadoFin.get(i)) {
				this.costoHeuristico++;
			}
		}
	}
	
	// Sobreescribimos el método toString para imprimir el contenido del Puzzle
	@Override
	public String toString() {
		String retorno = "";
		retorno = retorno + "| " + this.contenido.get(0) + " " + this.contenido.get(1) + " " + this.contenido.get(2) + " |\n";
		retorno = retorno + "| " + this.contenido.get(3) + " " + this.contenido.get(4) + " " + this.contenido.get(5) + " |\n";
		retorno = retorno + "| " + this.contenido.get(6) + " " + this.contenido.get(7) + " " + this.contenido.get(8) + " |\n";
		return retorno;
	}
}
