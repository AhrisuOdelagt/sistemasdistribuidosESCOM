/*    --- Información del proyecto ----
	No. de proyecto: 2
	Nombre completo: Franco Olvera Demian Oder
	Grupo: 7CM1
*/

// Bibliotecas para dibujar los polígonos en la interfaz
import javax.swing.*;
import javax.swing.Timer;	// Importamos el Timer aparte porque el programa no compila por alguna razón si no está por separado
import java.awt.*;
import java.awt.geom.AffineTransform;

// Importamos los ArrayList y el Collections para ordenar por área
import java.util.*;

// Creamos la clase de prueba para el proyecto 2, heredando la clase abstracta JPanel para dibujar
public class ProyectoDos extends JPanel {
    // Atributos
    private ArrayList<Coordenada> vertices;
    private double anguloRotacion = 0;  // Se requiere para poder trasladar al polígono en incrementos
    /* Velocidades de rotación */
    private double rotacionLenta = Math.toRadians(0.000007); // Para dar la ilusión de que los polígonos no se mueven
    private double rotacionNormal = Math.toRadians(1); // Para rotar a los polígonos sobre su centro
    /* Coordenadas del centro de cada polígono (Para manterner la rotación en torno a él) */
    private int centroAbcisa;
    private int centroOrdenada;
    /* Offsets, para añadir aleatoriedad a las posiciones de los polígonos */
    private int offsetAbcisa;
    private int offsetOrdenada;
    /* Swing maneja sólo enteros, por lo que se harán múltiples cast a lo largo del código */
    
    // Constructor para la clase de prueba
    public ProyectoDos(ArrayList<Coordenada> vertices) {
        this.vertices = vertices;
        // Calculamos el centro del polígono en cuestión
        int centro[] = calcularCentro();
        centroAbcisa = centro[0];
        centroOrdenada = centro[1];
        // Establecemos un offset en su posición
        offsetAbcisa = (int)(Math.random() * 100 + 75);
        offsetOrdenada = (int)(Math.random() * 100 + 75);
    }

    // Función para calcular el centro del polígono
    private int[] calcularCentro() {
        double promedioAbcisas = 0;
        double promedioOrdenadas = 0;
        // La coordenada céntrica del polígono es el promedio de todos sus puntos
        for (Coordenada coord : vertices) {
            promedioAbcisas += coord.abcisa();
            promedioOrdenadas += coord.ordenada();
        }
        // Generamos la coordenada para el centro
        int centro[] = new int[2];
        centro[0] = (int)(promedioAbcisas / vertices.size());
        centro[1] = (int)(promedioOrdenadas / vertices.size());
        return centro;
    }

    // Sobrescritura del método para dibujar un componente
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        // AffineTransform permite generar movimientos de rotación (documentación de swing)
        AffineTransform rotacion = AffineTransform.getRotateInstance(anguloRotacion, centroAbcisa + offsetAbcisa, centroOrdenada + offsetOrdenada);
        g2d.setTransform(rotacion);
        // Calculamos los nuevos vértices por cada rotación realizada
        int[] x = new int[vertices.size()];
        int[] y = new int[vertices.size()];
        for (int i = 0; i < vertices.size(); i++) {
            x[i] = (int) vertices.get(i).abcisa() + offsetAbcisa;
            y[i] = (int) vertices.get(i).ordenada() + offsetOrdenada;
        }
        // Coloreamos al polígono
        g2d.setColor(Color.PINK);
        g2d.fillPolygon(x, y, vertices.size());
        g2d.dispose();
    }

    /* Rotaciones */
    // Función para tener una figura «sin movimiento»
    public void poligonoEstatico() {
        Timer timer = new Timer(4, e -> {
            anguloRotacion += rotacionLenta;
            repaint();
        });
        timer.start();
    }

    // Función para rotar al polígono sobre su centro
    public void iniciarRotacionNormal(int retraso) {
        Timer timer = new Timer(4, e -> {
            anguloRotacion += rotacionNormal;
            repaint();
        });
        timer.setInitialDelay(retraso);
        timer.start();
    }

    // Función main
    public static void main(String[] args) {
        // Capturamos el total de polígonos desde la consola para almacenarlos en un ArrayList
        int totalPoligonos = Integer.parseInt(args[0]);
        ArrayList<PoligonoReg> poligonos = new ArrayList<>();

        // Generamos los polígonos
        for (int i = 0; i < totalPoligonos; i++) {
            // Mínimo tres lados, máximo 10
            int numLados = (int) (Math.random() * 10 + 3);
            // Radio mínimo de 15 unidades, máximo de 1/8 de la altura total de la ventana
            double radioPol = (double) (Math.random() * (720.0 / 8) + 15);
            poligonos.add(new PoligonoReg(numLados, radioPol));
        }

        // Ordenamos a los polígonos conforme a su área
        Collections.sort(poligonos, new Comparator<PoligonoReg>() {
            @Override
            public int compare(PoligonoReg poligono1, PoligonoReg poligono2) {
                return Double.compare(poligono1.areaPoligono(), poligono2.areaPoligono());
            }
        });

        // Creamos al JFrame que contendrá al canvas de figuras
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Rotación de Polígonos Generados");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1400, 720);
            // FlowLayout para tener una alineación central con todas las figuras
            frame.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

            // Agregamos todos los polígonos al JFrame para tenerlos preparados para iniciar sus movimientos de rotación
            for (PoligonoReg poligono : poligonos) {
                ProyectoDos panel = new ProyectoDos(poligono.verticesPoligono());
                panel.setPreferredSize(new Dimension(200, 200));
                frame.add(panel);
            }

            // Mostramos el JFrame en pantalla
            frame.setVisible(true);

            // Generamos un retraso de 1 segundo (1000 milisegundos)
            int retraso = 1000; 
            // Iteramos los polígonos del marco para dibujarlos y hacerlos rotar
            for (Component component : frame.getContentPane().getComponents()) {
                ProyectoDos panel = (ProyectoDos) component;
                // Primero los mantenemos estáticos
                panel.poligonoEstatico();
                // Lo hacemos rotar con un delay
                panel.iniciarRotacionNormal(retraso);
                // Colocamos un segundo más por cada iteración de tal forma que se cumpla la regla de una rotación por segundo
                retraso += 1000;
            }
        });
    }
}
