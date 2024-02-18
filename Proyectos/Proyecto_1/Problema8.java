/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Demian
 */
import java.util.Scanner;

public class Problema8 {
    /*Escribe un programa que pida 2 números e indique si uno es múltiplo del otro, ejemplo: 2 y 4, el
4 es múltiplo de 2; otro ejemplo 9 y 3, el 9 es múltiplo de 3. */
    public static void main(String args[]) {
        // Generamos una nueva instancia del Scanner
        Scanner console = new Scanner(System.in);
        // Definimos a las variables que vamos a utilizar
        int a, b, M, m;
        boolean multiplos = false;
        
        // Le solicitamos al usuario dos numeros cualesquiera
        System.out.println("Escriba el numero a: \t");
        a = console.nextInt();
        System.out.println("Escriba el numero b: \t");
        b = console.nextInt();
        
        // Detectamos al numero mayor
        if((a - b) >= 0) {
            M = a;
            m = b;
        }
        else {
            M = b;
            m = a;
        }
        
        // Determinamos si son múltiplos o no
        if((M % m) == 0) {
            multiplos = true;
        }
        
        // Regresamos el resultado
        if(multiplos) {
            System.out.printf("\nEl numero %d es multiplo del numero %d.", M, m);
        }
        else {
            System.out.println("\nLos numeros no son multiplos.");
        }
        
        // Finalizamos el programa
        System.out.println("\n\nFin del programa.");
        System.exit(0);
    }
}
