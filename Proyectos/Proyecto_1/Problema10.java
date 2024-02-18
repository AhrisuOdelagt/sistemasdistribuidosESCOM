/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Demian
 */
import java.util.Scanner;

public class Problema10 {

    /*Escribe un programa que solicite al usuario un número entero y dé como resultado el factorial de
ese número. */
    public static void main(String args[]){
        // Generamos una nueva instancia del Scanner
        Scanner console = new Scanner(System.in);
        // Definimos a las variables que vamos a utilizar
        int n, factorial;
        
        // Solicitamos el número al usuario
        System.out.println("Digite el numero del que desea calcular el factorial: \t");
        while(true) {
            n = console.nextInt();
            if(n >= 0) {
                break;
            }
            else {
                System.out.println("Entrada invalida. Intente nuevamente.");
            }
        }
        console.nextLine();
        
        // Calculamos el factorial de dicho número
        if(n == 0 || n == 1) {
            factorial = 1;
        }
        else {
            factorial = n;
            for(int i = n; i > 1; i--) {
                factorial = factorial * (i - 1);
            }
        }
        
        // Mostramos el resultado en pantalla
        System.out.printf("\nEl factorial de %d es: %d.", n, factorial);
        
        // Finalizamos el programa
        System.out.println("\n\nFin del programa.");
        System.exit(0);
    }
}
