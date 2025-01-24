import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

public class Rotacion extends JPanel {
    private int[] xPoints = {0, 100, 50}; // Coordenadas x de los vértices del triángulo
    private int[] yPoints = {0, 0, 100}; // Coordenadas y de los vértices del triángulo
    private double angle = 0; // Ángulo de rotación inicial
    private final double rotationSpeed = Math.toRadians(1); // Velocidad de rotación en radianes por frame
    private final int centerX; // Centro del polígono en el eje x
    private final int centerY; // Centro del polígono en el eje y

    public Rotacion() {
        // Calcula el centro del polígono como el promedio de las coordenadas de los vértices
        centerX = (xPoints[0] + xPoints[1] + xPoints[2]) / 3;
        centerY = (yPoints[0] + yPoints[1] + yPoints[2]) / 3;

        Timer timer = new Timer(50, e -> {
            angle += rotationSpeed; // Incrementa el ángulo de rotación
            repaint(); // Vuelve a dibujar
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        // Calcula la transformación de rotación alrededor del centro del polígono
        AffineTransform transform = AffineTransform.getRotateInstance(angle, centerX, centerY);
        g2d.setTransform(transform);

        // Dibuja el polígono
        g2d.setColor(Color.PINK);
        g2d.fillPolygon(xPoints, yPoints, 3);

        g2d.dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Rotación de un Polígono sobre su Centro");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);
            frame.add(new Rotacion());
            frame.setVisible(true);
        });
    }
}
