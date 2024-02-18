/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Demian
 */
import java.util.Scanner;

public class Problema19 {
    /* Leer una serie de 10 números, moverlos una posición hacia adelante en el arreglo y mostrar el
arreglo resultante. Ejemplo, tenemos el siguiente arreglo 1,2,3,4,5, si desplazamos los elementos una
posición hacia adelante debemos obtener: 5,1,2,3,4. Es decir, el primer elemento se mueve hacia el
segundo lugar, el segundo al tercero, etc., y el último al primero. */
    public static void main(String args[]){
        // Generamos una nueva instancia del Scanner
        Scanner console = new Scanner(System.in);
        // Definimos a las variables que vamos a utilizar
        int i = 0, j = 0, n1 = 0, n2 = 0, pos = 0, numeros[] = new int[10];
        
        // Solicitamos al usuario que ingrese los numeros y cuantas posiciones quiere moverlos
        for(i = 0; i < 10; i++) {
            System.out.println("Escriba un numero: \t");
            numeros[i] = console.nextInt();
        }
        System.out.println("\nDigite cuantas posiciones quiere mover los numeros: \t");
        pos = console.nextInt() % 10;
        
        // Realizamos el movimiento
        for(i = 0; i < pos; i++) {
            for(j = 9; j > 0; j--) {
                n1 = numeros[j % 10];
                n2 = numeros[(j - 1) % 10];
                numeros[j % 10] = n2;
                numeros[(j - 1) % 10] = n1;
            }
        }
        
        // Imprimimos el arreglo resultante
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
