/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Demian
 */

public class Problema21 {
    
    /* Escribe un programa que lea un arreglo bidimensional de 5x5 y muestre la suma de la diagonal
principal sin utilizar ciclos anidados. “En álgebra lineal, la diagonal principal de una matriz cuadrada
contiene los elementos situados desde A(1,1) hasta A(N,N). */
    public static void main(String args[]){
        // Definimos a las variables que vamos a utilizar
        int i = 0, res = 0, numeros[][] = new int[5][5];
        
        // Llenamos el arreglo con numeros al azar del 0 al 100
        for(i = 0; i < numeros.length; i++) {
            numeros[i][0] = (int)(Math.random() * 100 + 1);
            numeros[i][1] = (int)(Math.random() * 100 + 1);
            numeros[i][2] = (int)(Math.random() * 100 + 1);
            numeros[i][3] = (int)(Math.random() * 100 + 1);
            numeros[i][4] = (int)(Math.random() * 100 + 1);
        }
        
        // Leemos y sumamos la diagonal
        for(i = 0; i < numeros.length; i++) {
            res = res + numeros[i][i];
        }
        
        // Imprimimos el arreglo bidimensional y el resultado de la suma
        System.out.print("\nEl arreglo bidimensional 5x5 es: \n[");
        for(i = 0; i < numeros.length; i++) {
            System.out.printf("%d, ", numeros[i][0]);
            System.out.printf("%d, ", numeros[i][1]);
            System.out.printf("%d, ", numeros[i][2]);
            System.out.printf("%d, ", numeros[i][3]);
            System.out.printf("%d, ", numeros[i][4]);
            if(i == numeros.length - 1) {
                System.out.print("].");
            }
            else {
                System.out.println("");
            }
        }
        System.out.println("\nEl valor de la suma de los valores en la diagonal es: " + res + ".");
        
        // Finalizamos el programa
        System.out.println("\n\nFin del programa.");
        System.exit(0);
    }
}
