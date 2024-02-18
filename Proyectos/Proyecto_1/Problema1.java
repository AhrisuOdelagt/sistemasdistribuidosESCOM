/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Demian
 */
import java.util.Scanner;

public class Problema1 {

    /* Dados los valores del radio de un círculo y su altura, calcular: el volumen del cono, el volumen
del cilindro e indicar la diferencia de volumen entre ambos. Los resultados deben mostrarse en pantalla
con 4 decimales. */
    public static void main(String args[]) {
        // Generamos una nueva instancia del Scanner y el valor de pi
        Scanner console = new Scanner(System.in);
        // Definimos a las variables que vamos a utilizar
        double pi = 3.1416, radio, altura, volCono, volCilindro, diff;

        // Solicitamos al usuario el radio y la altura del círculo
        System.out.println("Digite el valor del radio: \t");
        radio = console.nextDouble();
        System.out.println("Digite el valor de la altura: \t");
        altura = console.nextDouble();

        // Calculamos los volúmenes y la diferencia
        // Cono     pi * r^2 * h / 3
        volCono = (pi * Math.pow(radio, 2) * altura) / 3;
        // Cilindro     pi * r^2 * h
        volCilindro = pi * Math.pow(radio, 2) * altura;
        // Diferencia
        diff = volCilindro - volCono;

        // Imprimimos los resultados
        System.out.println("\nLos resultados son:");
        System.out.printf("Volumen del cono: %.4f unidades.\n", volCono);
        System.out.printf("Volumen del cilindro: %.4f unidades.\n", volCilindro);
        System.out.printf("Diferencia: %.4f unidades.\n", diff);
        
        // Finalizamos el programa
        System.out.println("\n\nFin del programa.");
        System.exit(0);
    }
}
