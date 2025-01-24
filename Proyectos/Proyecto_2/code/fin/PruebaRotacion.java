import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.*;

public class PruebaRotacion extends JPanel {
    private ArrayList<Coordenada> vertices;
    private double angle = 0;
    private double slowRotationSpeed = Math.toRadians(0.000007); // Velocidad lenta de rotación
    private double normalRotationSpeed = Math.toRadians(1); // Velocidad normal de rotación
    private int centerX;
    private int centerY;
    private int offsetX;
    private int offsetY;

    public PruebaRotacion(ArrayList<Coordenada> vertices) {
        this.vertices = vertices;
        centerX = calculateCenterX();
        centerY = calculateCenterY();
        offsetX = (int)(Math.random() * 100 + 75);
        offsetY = (int)(Math.random() * 100 + 75);
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
        AffineTransform transform = AffineTransform.getRotateInstance(angle, centerX + offsetX, centerY + offsetY);
        g2d.setTransform(transform);
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

    public void iniciarRotacionLenta() {
        Timer timer = new Timer(4, e -> {
            angle += slowRotationSpeed;
            repaint();
        });
        timer.start();
    }

    public void iniciarRotacionNormal(int delay) {
        Timer timer = new Timer(4, e -> {
            angle += normalRotationSpeed;
            repaint();
        });
        timer.setInitialDelay(delay);
        timer.start();
    }

    public static void main(String[] args) {
        int totalPoligonos = Integer.parseInt(args[0]);
        ArrayList<PoligonoReg> poligonos = new ArrayList<>();

        for (int i = 0; i < totalPoligonos; i++) {
            int numLados = (int) (Math.random() * 10 + 3);
            double radioPol = (double) (Math.random() * (720.0 / 8) + 15);
            poligonos.add(new PoligonoReg(numLados, radioPol));
        }

        Collections.sort(poligonos, new Comparator<PoligonoReg>() {
            @Override
            public int compare(PoligonoReg poligono1, PoligonoReg poligono2) {
                return Double.compare(poligono1.areaPoligono(), poligono2.areaPoligono());
            }
        });

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Rotación de Polígonos Generados");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1400, 720);
            frame.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

            // Agregar todos los polígonos al JFrame sin rotación
            for (PoligonoReg poligono : poligonos) {
                PruebaRotacion panel = new PruebaRotacion(poligono.verticesPoligono());
                panel.setPreferredSize(new Dimension(200, 200));
                frame.add(panel);
            }

            frame.setVisible(true);

            // Iniciar la rotación lenta de todos los polígonos
            int delay = 1000; // Retraso inicial de 1 segundo
            for (Component component : frame.getContentPane().getComponents()) {
                PruebaRotacion panel = (PruebaRotacion) component;
                panel.iniciarRotacionLenta();
                panel.iniciarRotacionNormal(delay); // Iniciar rotación normal con un retraso acumulativo
                delay += 1000; // Incrementar el retraso para el siguiente polígono
            }
        });
    }
}
