/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Demian
 */
import java.util.Scanner;

public class Problema5 {
    
    /* Escribe un programa para convertir una calificación de 0 a 100 al sistema A-F utilizando la
siguiente tabla: (A - Muy bien) = 90-100, (B = Bien) 80 - 89, (C - Suficiente) = 60 -79, (F - No suficiente) = 0
- 59. El usuario debe escribir la calificación con número e indicar si quiere que la conversión le muestre la
letra (A, B, C, F) o la descripción correspondiente (Muy Bien, Bien, Suficiente, No Suficiente). */
    public static void main(String args[]){
        // Generamos una nueva instancia del Scanner
        Scanner console = new Scanner(System.in);
        // Definimos a las variables que vamos a utilizar
        int calif;
        String sistema;
        
        // Solicitamos la calificación y el medio para mostrar su equivalencia
        System.out.println("Digite el valor de la calificacion: \t");
        while(true) {
            calif = console.nextInt();
            if(calif >= 0 && calif <= 100) {
                break;
            }
            else {
                System.out.println("Entrada invalida. Intente nuevamente");
            }
        }
        console.nextLine();
        System.out.println("Desea utilizar el sistema A-F? (y/n): \t");
        while(true) {
            sistema = console.nextLine();
            if("y".equals(sistema) || "n".equals(sistema)) {
                break;
            }
            else {
                System.out.println("Entrada invalida. Intente nuevamente");
            }
        }
        
        // Respondemos con el valor de la calificación
        if(calif >= 0 && calif <= 59) {
            if("y".equals(sistema)) {
                    System.out.println("La calificacion es: F.");
                }
            else {
                System.out.println("La calificacion es: No Suficiente.");
            }
        }
        else if(calif >= 60 && calif <= 79) {
            if("y".equals(sistema)) {
                    System.out.println("La calificacion es: C.");
                }
            else {
                System.out.println("La calificacion es: Suficiente.");
            }
        }
        else if(calif >= 80 && calif <= 89) {
            if("y".equals(sistema)) {
                    System.out.println("La calificacion es: B.");
                }
            else {
                System.out.println("La calificacion es: Bien.");
            }
        }
        else {
            if("y".equals(sistema)) {
                    System.out.println("La calificacion es: A.");
                }
            else {
                System.out.println("La calificacion es: Muy Bien.");
            }
        }
        
        // Finalizamos el programa
        System.out.println("\n\nFin del programa.");
        System.exit(0);
    }
}
