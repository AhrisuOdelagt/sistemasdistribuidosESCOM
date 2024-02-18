/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Demian
 */
import java.util.Scanner;

public class Problema17 {
    
    /* Leer 10 números enteros, guardarlos y mostrarlos en el mismo orden en que fueron
introducidos. */
    public static void main(String args[]){
        // Generamos una nueva instancia del Scanner
        Scanner console = new Scanner(System.in);
        // Definimos a las variables que vamos a utilizar
        int i = 0, numeros[] = new int[10];
        
        // Solicitamos en bucle los numeros al usuario
        for(i = 0; i < 10; i++) {
            System.out.println("Escriba un numero: \t");
            numeros[i] = console.nextInt();
        }
        
        // Imprimimos los numeros como resultado
        System.out.print("\nEl arreglo resultante es: [");
        for(i = 0; i < numeros.length; i++){
            System.out.printf("%d, ", numeros[i]);
        }
        System.out.print("].");
        
        // Finalizamos el programa
        System.out.println("\n\nFin del programa.");
        System.exit(0);
    }
}
