/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Demian
 */
import java.util.Scanner;

public class Problema15 {
    
    /* En este programa la computadora debe elegir un número al azar entre 1 y 100 y solicitará al
usuario que “adivine” el número. A cada intento del usuario la computadora debe indicar si el número
que el usuario introdujo es mayor o menor que el número prefijado. El programa debe terminar cuando
el usuario “adivine” el número o introduzca el número “0” para salir. */
    public static void main(String args[]){
        // Generamos una nueva instancia del Scanner
        Scanner console = new Scanner(System.in);
        // Definimos a las variables que vamos a utilizar
        int n = 0, n_usuario = 0;
        
        // Generamos un numero aleatorio del 1 al 100;
        n = (int)(Math.random() * 100 + 1);
        
        // Solicitamos el numero al usuario dentro de un bucle para adivinar
        while(true) {
            // Solicitamos el numero
            System.out.println("Digite un numero para intentar adivinarlo: \t");
            while(true) {
                n_usuario = console.nextInt();
                if(n_usuario >= 0 && n_usuario <= 100){
                    break;
                }
                else {
                    System.out.println("Entrada invalida. Intente nuevamente.");
                }
            }
            console.nextLine();
            
            // Verificamos si el numero que ingresó el usuario es acertado o no
            if(n == n_usuario) {
                System.out.println("\nHa acertado con el numero.");
                break;
            }
            else if(n_usuario == 0) {
                System.out.println("\nHa salido del programa.");
                break;
            }
            else if(n > n_usuario) {
                System.out.println("\nEl numero ingresado es menor. Intente nuevamente.");
            }
            else {
                System.out.println("\nEl numero ingresado es mayor. Intente nuevamente.");
            }
        }
        
        // Finalizamos el programa
        System.out.println("\n\nFin del programa.");
        System.exit(0);
    }
}
