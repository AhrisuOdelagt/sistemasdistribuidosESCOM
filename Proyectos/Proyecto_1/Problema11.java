/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Demian
 */

public class Problema11 {
    
    /* Encontrar los números entre el 1 y el 5000 que cumplen la regla de que la suma del cubo de sus
dígitos es igual al número mismo. Ejemplo: 153=13+53+33 */
    public static void main(String args[]){
        // Definimos a las variables que vamos a utilizar
        int suma, unidad, decena, centena, millar;
        
        // Iniciamos el ciclo for
        for(int i = 1; i <= 5000; i++) {
            if ((i / 10) == 0) {
                unidad = i;
                suma = unidad * unidad * unidad;
                if(suma == i) {
                    System.out.println(i);
                }
            }
            else if((i / 100) == 0) {
                decena = i / 10;
                unidad = i - (decena * 10);
                suma = (unidad * unidad * unidad) + (decena * decena * decena);
                if(suma == i) {
                    System.out.println(i);
                }
            }
            else if((i / 1000) == 0) {
                centena = i / 100;
                decena = (i - (centena * 100)) / 10;
                unidad = i - (centena * 100) - (decena * 10);
                suma = (unidad * unidad * unidad) + (decena * decena * decena) + (centena * centena * centena);
                if(suma == i) {
                    System.out.println(i);
                }
            }
            else if((i / 10000) == 0) {
                millar = i / 1000;
                centena = (i - (millar * 1000)) / 100;
                decena = (i - (millar * 1000) - (centena * 100)) / 10;
                unidad = i - (millar * 1000) - (centena * 100) - (decena * 10);
                suma = (unidad * unidad * unidad) + (decena * decena * decena) + (centena * centena * centena) + (millar * millar * millar);
                if(suma == i) {
                    System.out.println(i);
                }
            }
        }
        
        // Finalizamos el programa
        System.out.println("\n\nFin del programa.");
        System.exit(0);
    }
}
