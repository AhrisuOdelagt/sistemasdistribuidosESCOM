/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Demian
 */
import java.util.Scanner;

public class Problema22 {
    
    /* Escribe un programa que solicite al usuario los elementos de dos matrices (una matriz de 3
renglones x3 columnas por una de 2 renglones x3 columnas). El programa debe realizar la multiplicación
de ambas matrices y mostrar el resultado. */
    public static void main(String args[]){
        // Generamos una nueva instancia del Scanner
        Scanner console = new Scanner(System.in);
        // Definimos a las variables que vamos a utilizar
        int i = 0, j = 0, k = 0;
        double matriz1[][] = new double[3][3], matriz2[][] = new double[3][2], matriz3[][] = new double[3][2];
        
        // Pedimos los valores de la matriz 3x3
        System.out.println("Valores de la matriz 3x3: ");
        for(i = 0; i < matriz1.length; i++){
            for(j = 0; j < matriz1[0].length; j++){
                System.out.printf("Escriba el valor de la posicion [%d][%d]: \t", i + 1, j + 1);
                matriz1[i][j] = console.nextDouble();
            }
        }
        // Pedimos los valores de la matriz 3x2
        System.out.println("\nValores de la matriz 3x2: ");
        for(i = 0; i < matriz2.length; i++){
            for(j = 0; j < matriz2[0].length; j++){
                System.out.printf("Escriba el valor de la posicion [%d][%d]: \t", i + 1, j + 1);
                matriz2[i][j] = console.nextDouble();
            }
        }
        
        // Realizamos la multiplicacion de matrices
        System.out.println("\nSe multiplican las matrices: ");
        for(i = 0; i < matriz3.length; i++) {
            for(j = 0; j < matriz3[0].length; j++) {
                for(k = 0; k < matriz2.length; k++) {
                    System.out.printf("Posicion [%d][%d]: %.2f + (%.2f * %.2f)\n", i, j, matriz3[i][j], matriz1[i][k], matriz2[k][j]);
                    matriz3[i][j] = matriz3[i][j] + (matriz1[i][k] * matriz2[k][j]);
                }
                System.out.printf("Posicion [%d][%d]: %.2f\n\n", i, j, matriz3[i][j]);
            }
        }
        
        // Imprimimos la matriz resultante
        System.out.print("El resultado de la multiplicacion es: \n[");
        for(i = 0; i < matriz3.length; i++){
            for(j = 0; j < matriz3[0].length; j++){
                System.out.printf("%.2f, ", matriz3[i][j]);
            }
            if(i == matriz3.length - 1) {
                System.out.print("].");
            }
            else {
                System.out.println("");
            }
        }
        
        // Finalizamos el programa
        System.out.println("\n\nFin del programa.");
        System.exit(0);
    }
}
