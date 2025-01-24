import javax.swing.*;
import java.awt.*;
public class MovimientoTriangulo extends JPanel {
    private int x1 = 0;
    private int y1 = 0;
    private int x2 = 100;
    private int y2 = 0;
    private int x3 = 50;
    private int y3 = 100;    
    private Polygon poligono=new Polygon();
    
    public MovimientoTriangulo() {
        Timer timer = new Timer(50, e -> {
            poligono.reset();
            poligono.addPoint(x1, y1);
            poligono.addPoint(x2, y2);
            poligono.addPoint(x3, y3);
            // Incrementa las coordenadas
            x1 += 1;
            y1 += 1;
            x2 += 1;
            y2 += 1;
            x3 += 1;
            y3 += 1;
            // Vuelve a dibujar
            repaint();
        });
        timer.start();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawPolygon(poligono);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Movimiento de una Triángulo");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);
            frame.add(new MovimientoTriangulo());
            frame.setVisible(true);
        }
        );
    }
}
