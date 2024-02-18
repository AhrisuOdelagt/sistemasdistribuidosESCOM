/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Demian
 */
import java.util.Scanner;

public class Problema4 {

    /*Escriba un programa que calcule las dos respuestas de x en formato decimal (en caso de que no
existan soluciones reales debe indicarlo al usuario con un mensaje) que son soluciones de una ecuación
de segundo grado. */
    public static void main(String args[]) {
        // Generamos una nueva instancia del Scanner
        Scanner console = new Scanner(System.in);
        // Definimos a las variables que vamos a utilizar
        double a, b, c, d, x1, x2;

        // Solicitamos los valores de a, b & c
        System.out.println("Digite el valor de a: \t");
        a = console.nextDouble();
        System.out.println("Digite el valor de b: \t");
        b = console.nextDouble();
        System.out.println("Digite el valor de c: \t");
        c = console.nextDouble();

        // Verificamos si la ecuación tiene soluciones reales
        d = Math.pow(b, 2) - 4 * a * c;
        System.out.println("d = " + d);
        // Terminamos el programa de no ser así
        if (d < 0) {
            System.out.println("La ecuacion no tiene soluciones reales.");
            System.out.println("\n\nFin del programa");
            System.exit(1);
        }

        // Calculamos los valores de x1 & x2
        x1 = ((-1 * b) + Math.sqrt(d)) / (2 * a);
        x2 = ((-1 * b) - Math.sqrt(d)) / (2 * a);

        // Imprimimos los resultados en pantalla
        System.out.println("\nLos resultados son:");
        System.out.printf("El valor de x1 es: %.2f\n", x1);
        System.out.printf("El valor de x2 es: %.2f\n", x2);
        
        // Finalizamos el programa
        System.out.println("\n\nFin del programa.");
        System.exit(0);
    }
}
