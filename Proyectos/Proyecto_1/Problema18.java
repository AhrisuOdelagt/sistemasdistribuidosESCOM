/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Demian
 */
import java.util.Scanner;

public class Problema18 {
    
    /* Escribe un programa que lea 2 arreglos de 10 números (arreglo X, arreglo Y). El programa debe
mezclarlos en un tercer arreglo (de 20 elementos obviamente) de la siguiente manera: 1er elemento del
arreglo X, 1er elemento del arreglo Y, 2do elemento del arreglo X, 2do elemento del arreglo Y, 3er
elemento del arreglo X..., etc. El programa deberá mostrar en pantalla el arreglo resultante. */
    public static void main(String args[]){
        // Generamos una nueva instancia del Scanner
        Scanner console = new Scanner(System.in);
        // Definimos a las variables que vamos a utilizar
        int i = 0, j = 0, numeros_1[] = new int[10], numeros_2[] = new int[10], numeros_3[] = new int[20];
    
        // Solicitamos los numeros al usuario
        for(i = 0; i < 10; i++) {
            System.out.println("Escriba un numero: \t");
            numeros_1[i] = console.nextInt();
        }
        for(i = 0; i < 10; i++) {
            System.out.println("Escriba un numero: \t");
            numeros_2[i] = console.nextInt();
        }
        
        // Ensamblamos el tercer arreglo
        for(i = 0; i < 20; i++) {
            if((i % 2) == 0) {
                numeros_3[i] = numeros_1[j];
            }
            else {
                numeros_3[i] = numeros_2[j];
                j++;
            }
        }
        
        // Imprimimos el arreglo en conjunto
        System.out.print("\nEl arreglo resultante es: [");
        for(i = 0; i < numeros_3.length; i++){
            System.out.printf("%d, ", numeros_3[i]);
        }
        System.out.print("].");
        
        // Finalizamos el programa
        System.out.println("\n\nFin del programa.");
        System.exit(0);
    }
}
