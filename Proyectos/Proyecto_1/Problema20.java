/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Demian
 */
public class Problema20 {
    
    /* Escribe un programa que genere 10 números al azar y los almacene en un arreglo. El programa
debe mostrar el arreglo e indicar cuál es el mayor y cuál es el menor de los números. */
    public static void main(String args[]){
        // Definimos a las variables que vamos a utilizar
        int i = 0, n = 0, n1 = 0, n2 = 0, numeros[] = new int[10];
        
        // Llenamos el arreglo con numeros al azar del 0 al 100
        for(i = 0; i < numeros.length; i++) {
            numeros[i] = (int)(Math.random() * 100 + 1);
        }
        
        // Hallamos el numero menor y el mayor
        n1 = numeros[0];
        for(i = 0; i < numeros.length; i++) {
            n = numeros[i];
            if(n >= n1) {
               n1 = n;
            }
        }
        n2 = numeros[0];
        for(i = 0; i < numeros.length; i++) {
            n = numeros[i];
            if(n <= n2) {
               n2 = n;
            }
        }
        
        // Imprimimos el arreglo y los números
        System.out.print("\nEl arreglo aleatorio es: [");
        for(i = 0; i < numeros.length; i++){
            System.out.printf("%d, ", numeros[i]);
        }
        System.out.print("].");
        System.out.println("\nEl numero mayor es: " + n1 + ".");
        System.out.println("El numero menor es: " + n2 + ".");
        
        // Finalizamos el programa
        System.out.println("\n\nFin del programa.");
        System.exit(0);
    }
}
