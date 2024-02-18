/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Demian
 */
import java.util.Scanner;

public class Problema2 {

    /* Escribe un programa que calcule el área de un trapecio de lados a y b y altura h mediante la
formula: A = ((a + b) / 2) * h */
    public static void main(String args[]) {
        // Generamos una nueva instancia del Scanner
        Scanner console = new Scanner(System.in);
        // Definimos a las variables que vamos a utilizar
        double a, b, h, area;

        // Solicitamos los datos al usuario
        System.out.println("Digite el valor del lado a: \t");
        a = console.nextDouble();
        System.out.println("Digite el valor el lado b: \t");
        b = console.nextDouble();
        System.out.println("Digite el valor de la altura: \t");
        h = console.nextDouble();

        // Calculamos el área del trapecio
        area = ((a + b) / 2) * h;

        // Imprimimos en pantalla el área del trapecio
        System.out.printf("\nEl area del trapecio es de %.2f unidades.", area);
        
        // Finalizamos el programa
        System.out.println("\n\nFin del programa.");
        System.exit(0);
    }
}
