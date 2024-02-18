/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Demian
 */
import java.util.Scanner;

public class Problema14 {
    
    /* Escribe un programa que solicite un número al usuario y entonces mostrar los números de 5 en 5
desde el número indicado hasta 150. Ejemplo: si el usuario introduce el número 27, la secuencia sería
27, 32, 37, 42, 47..... 142, 147. */
    public static void main(String args[]){
        // Generamos una nueva instancia del Scanner
        Scanner console = new Scanner(System.in);
        // Definimos a las variables que vamos a utilizar
        int n;
        
        // Solicitamos el número al usuario
        System.out.println("Digite el numero a operar: \t");
        n = console.nextInt();
        
        // Mostramos las adiciones
        System.out.println("\nLos numeros son: ");
        while(n <= 150) {
            System.out.println(n);
            n += 5;
        }
        
        // Finalizamos el programa
        System.out.println("\n\nFin del programa.");
        System.exit(0);
    }
}
