/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Demian
 */
import java.util.Scanner;

public class Problema3 {
    
    /* Utilizando operaciones de división entera y módulo, convertir un número dado en segundos a su
equivalente en horas, minutos y segundos. Ejemplo, el valor en segundos de 3725 segundos es igual a 62
minutos, lo cual es igual a 1 hora, 2 minutos y 5 segundos. */
    public static void main(String args[]){
        // Generamos una nueva instancia del Scanner
        Scanner console = new Scanner(System.in);
        // Definimos a las variables que vamos a utilizar
        int tiempo, tiempo_1, segundos, horas, minutos;
        
        // Solicitamos el número al usuario
        System.out.println("Digite el tiempo a convertir: \t");
        tiempo = console.nextInt();
        // Convertimos el número a minutos y segundos
        tiempo_1 = tiempo / 60;
        segundos = tiempo % 60;
        // Convertimos el número a horas y minutos
        horas = tiempo_1 / 60;
        minutos = tiempo_1 % 60;
        
        // Imprimimos el resultado
        System.out.println("El tiempo " + tiempo + " equivale a " + horas + " horas, " + minutos + " minutos y " + segundos + " segundos.");
        
        // Finalizamos el programa
        System.out.println("\n\nFin del programa.");
        System.exit(0);
    }
}
