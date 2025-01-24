// Clase PoligonoIrregular
// Importamos las listas en la clase
import java.util.*;
// Importamos Decimal Format
import java.text.DecimalFormat;
import java.io.*;
import java.lang.*;

class CompararMagnitud implements Comparator<Coordenada> {
    // @Override
    public int compare(Coordenada a, Coordenada b) {
        return (int) a.magnitudVer() - (int) b.magnitudVer();
    }
}

public class PoligonoIrregular implements java.io.Serializable {
    // Atributos
    private List<Coordenada> vertices = new ArrayList<Coordenada>();
    private int num_vert;

    // Constructor
    public PoligonoIrregular() {
        this.num_vert = 0;
    }

    // Métodos

    // Método para añadir vértice
    public void anadeVertice() {
        Coordenada vertice = new Coordenada((double) (Math.random() * 200 - 100), (double) (Math.random() * 200 - 100));
        this.vertices.add(this.num_vert, vertice);
        // Acomodamos la lista
        Collections.sort(vertices, new CompararMagnitud());
        this.num_vert++;
    }

    // Método getter para obtener el número de vértices
    public int getNumVert() {
        return this.num_vert;
    }

    // Sobreescritura del método de la superclase objeto para imprimir los vértices del polígono
    @Override
    public String toString() {
        DecimalFormat formato = new DecimalFormat("#.###");
        String ver = "";
        for (Coordenada vertice : this.vertices) {
            double x_ver = vertice.abcisa();
            double y_ver = vertice.ordenada();
            double mag = vertice.magnitudVer();
            String x_format = formato.format(x_ver);
            String y_format = formato.format(y_ver);
            String mag_format = formato.format(mag);
            ver = ver + "[" + x_format + ", " + y_format + "]" + " Magnitud: " + mag_format + "\n";
        }
        return "\nPoligono: \n" + ver;
    }
}
