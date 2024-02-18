/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Demian
 */
import java.util.Scanner;

public class Problema16 {
    
    /* El programa debe leer números mientras sean diferentes de 0. Posteriormente calcular la suma
de los cuadrados de los números leídos. */
    public static void main(String args[]){
        // Generamos una nueva instancia del Scanner
        Scanner console = new Scanner(System.in);
        // Definimos a las variables que vamos a utilizar
        double numeros[] = new double[100], resultado = 0, n = 0;
        int i = 0;
        
        // Solicitamos el numero al usuario dentro de un bucle para guardar sus elecciones
        while(true) {
            System.out.println("Digite un numero: \t");
            n = console.nextDouble();
            if(n == 0){
                break;
            }
            else {
                numeros[i] = n;
                i++;
            }
        }
        
        // Calculamos los cuadrados de los numeros ingresados
        i = 0;
        while(i <= numeros.length - 1) {
            resultado = resultado + Math.pow(numeros[i], 2);
            i++;
        }
        
        // Regresamos el resultado
        System.out.printf("\nEl resultado de la operacion es: %.0f.", resultado);
        
        // Finalizamos el programa
        System.out.println("\n\nFin del programa.");
        System.exit(0);
    }
}
