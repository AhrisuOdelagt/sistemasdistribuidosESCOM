/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Demian
 */
import java.util.Scanner;

public class Problema13 {
    
    /* Escribir un programa que reciba una cadena de caracteres y la imprima al revés. */
    public static void main(String args[]){
        // Generamos una nueva instancia del Scanner
        Scanner console = new Scanner(System.in);
        // Definimos a las variables que vamos a utilizar
        String cadena, cadena_inv = "";
        
        // Solicitamos al usuario la cadena a invertir
        System.out.println("Escriba la cadena a invertir: \t");
        cadena = console.nextLine();
        
        // Invertimos la cadena
        for(int i = cadena.length() - 1; i >= 0; i--) {
            cadena_inv = cadena_inv.concat(String.valueOf(cadena.charAt(i)));
        }
        
        // Retormamos la cadena invertida
        System.out.println("La cadena invertida es: " +  cadena_inv + ".");
        
        // Finalizamos el programa
        System.out.println("\n\nFin del programa.");
        System.exit(0);
    }
}
