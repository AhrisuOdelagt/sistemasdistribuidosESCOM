import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

public class PruebaRotacion extends JPanel {
    private ArrayList<Coordenada> vertices; // Coordenadas de los vértices del polígono
    private double angle = 0; // Ángulo de rotación inicial
    private double rotationSpeed = Math.toRadians(1); // Velocidad de rotación en radianes por frame
    private int centerX; // Centro del polígono en el eje x
    private int centerY; // Centro del polígono en el eje y
	private static double altVentana = 720.0;
	private int offsetX;
	private int offsetY;

    public PruebaRotacion(ArrayList<Coordenada> vertices) {
        this.vertices = vertices;

        // Calcula el centro del polígono como el promedio de todas las coordenadas
        centerX = calculateCenterX();
        centerY = calculateCenterY();

        // Calculan offset para el polígono
		offsetX = (int)(Math.random() * 250 + 50);
        offsetY = (int)(Math.random() * 150 + 50);

        // Inicia el temporizador para la rotación
        Timer timer = new Timer(50, e -> {
            angle += rotationSpeed; // Incrementa el ángulo de rotación
            repaint(); // Vuelve a dibujar
        });
        timer.start();
    }

    private int calculateCenterX() {
        double sumX = 0;
        for (Coordenada coord : vertices) {
            sumX += coord.abcisa();
        }
        return (int) (sumX / vertices.size());
    }

    private int calculateCenterY() {
        double sumY = 0;
        for (Coordenada coord : vertices) {
            sumY += coord.ordenada();
        }
        return (int) (sumY / vertices.size());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        // Calcula la transformación de rotación alrededor del centro del polígono
        AffineTransform transform = AffineTransform.getRotateInstance(angle, centerX + offsetX, centerY + offsetY);
        g2d.setTransform(transform);

        // Dibuja el polígono
        int[] xPoints = new int[vertices.size()];
        int[] yPoints = new int[vertices.size()];

        for (int i = 0; i < vertices.size(); i++) {
            xPoints[i] = (int) vertices.get(i).abcisa() + offsetX;
            yPoints[i] = (int) vertices.get(i).ordenada() + offsetY;
        }
        g2d.setColor(Color.PINK);
        g2d.fillPolygon(xPoints, yPoints, vertices.size());

        g2d.dispose();
    }

    public static void main(String[] args) {
		// Leemos la cantidad de polígonos que se van a generar
		int totalPoligonos = Integer.parseInt(args[0]);
		// Creamos el ArrayList para incluir a los polígonos
		ArrayList<PoligonoReg> poligonos = new ArrayList<>();

		// Generamos los polígonos con las especificaciones solicitadas
		for (int i = 0; i < totalPoligonos; i++) {
			int numLados = (int) (Math.random() * 10 + 3);
			double radioPol = (double) (Math.random() * (altVentana / 8) + 10);
			poligonos.add(new PoligonoReg(numLados, radioPol));
		}

		// Crear y configurar el JFrame
		SwingUtilities.invokeLater(() -> {
			JFrame frame = new JFrame("Rotación de Polígonos Generados");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(1080, (int) altVentana);

			// Configurar el GridLayout para organizar los paneles en filas y columnas
			int rows = (int) Math.ceil(totalPoligonos / 3.0); // 3 paneles por fila
			frame.setLayout(new GridLayout(rows, 3, 10, 10)); // Espacio entre paneles

			// Agregar un panel PruebaRotacion para cada polígono generado
			for (PoligonoReg poligono : poligonos) {
				PruebaRotacion panel = new PruebaRotacion(poligono.verticesPoligono());
				frame.add(panel);
			}

			frame.setVisible(true);
		});
	}
}
