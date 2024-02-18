/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Demian
 */
import java.util.Scanner;

public class Problema9 {

    /* Escribe un programa que reciba un número en el rango de 1 a 3000 y lo convierta en número
romano. Ejemplo: 1997 sería MCMXCVII */
    public static void main(String args[]){
        // Generamos una nueva instancia del Scanner
        Scanner console = new Scanner(System.in);
        // Definimos a las variables que vamos a utilizar
        int num, unidad = 0, decena = 0, centena = 0, millar = 0;
        String unidad_rom, decena_rom, centena_rom, millar_rom;

        // Solicitamos el número al usuario
        System.out.println("Escribe el numero a convertir: \t");
        while(true) {
            num = console.nextInt();
            if(num >= 0 && num <= 3000) {
                break;
            }
            else {
                System.out.println("Entrada invalida. Intente nuevamente.");
            }
        }
        console.nextLine();

        // Calculamos los dígitos del número
        if ((num / 10) == 0) {
            unidad = num;
        }
        else if ((num / 100) == 0) {
            decena = num / 10;
            unidad = num - (decena * 10);
        }
        else if ((num / 1000) == 0) {
            centena = num / 100;
            decena = (num - (centena * 100)) / 10;
            unidad = num - (centena * 100) - (decena * 10);
        }
        else if ((num / 10000) == 0) {
            millar = num / 1000;
            centena = (num - (millar * 1000)) / 100;
            decena = (num - (millar * 1000) - (centena * 100)) / 10;
            unidad = num - (millar * 1000) - (centena * 100) - (decena * 10);
        }
        
        // Sustituimos por numeros romanos
        // Unidades
        switch(unidad) {
            case 1 -> unidad_rom = "I";
            case 2 -> unidad_rom = "II";
            case 3 -> unidad_rom = "III";
            case 4 -> unidad_rom = "IV";
            case 5 -> unidad_rom = "V";
            case 6 -> unidad_rom = "VI";
            case 7 -> unidad_rom = "VII";
            case 8 -> unidad_rom = "VIII";
            case 9 -> unidad_rom = "IX";
            default -> unidad_rom = ""; 
        }
        // Decenas
        switch(decena) {
            case 1 -> decena_rom = "X";
            case 2 -> decena_rom = "XX";
            case 3 -> decena_rom = "XXX";
            case 4 -> decena_rom = "XL";
            case 5 -> decena_rom = "L";
            case 6 -> decena_rom = "LX";
            case 7 -> decena_rom = "LXX";
            case 8 -> decena_rom = "LXXX";
            case 9 -> decena_rom = "XC";
            default -> decena_rom = ""; 
        }
        // Centenas
        switch(centena) {
            case 1 -> centena_rom = "C";
            case 2 -> centena_rom = "CC";
            case 3 -> centena_rom = "CCC";
            case 4 -> centena_rom = "CD";
            case 5 -> centena_rom = "D";
            case 6 -> centena_rom = "DC";
            case 7 -> centena_rom = "DCC";
            case 8 -> centena_rom = "DCCC";
            case 9 -> centena_rom = "CM";
            default -> centena_rom = ""; 
        }
        // Millares
        switch(millar) {
            case 1 -> millar_rom = "M";
            case 2 -> millar_rom = "MM";
            case 3 -> millar_rom = "MMM";
            default -> millar_rom = ""; 
        }
        
        // Regresamos el número romano
        System.out.println("\nEl numero romano equivalente es: " + millar_rom + centena_rom + decena_rom + unidad_rom + ".");
        
        // Finalizamos el programa
        System.out.println("\n\nFin del programa.");
        System.exit(0);
    }
}
