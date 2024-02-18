/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Demian
 */
public class Problema12 {
    
    /* Inversiones. Calcular cuánto dinero tendría en una cuenta de ahorro al final de 20 años si al inicio
de cada año añado 500 dólares (solo de los años 1 al 10), el rendimiento anual es de 5% y
reinvierto las ganancias. */
    public static void main(String args[]) {
        // Definimos a las variables que vamos a utilizar
        double monto = 0, ahorro = 0;
        
        // Iniciamos el ciclo for
        for(int i = 1; i <= 20; i++) {
            if(i >= 1 && i <= 10) {
                monto = monto + 500;
                monto = monto + (monto * 0.05);
            }
            else {
                monto = monto + (monto * 0.05);
            }
        }
        
        // Regresamos el ahorro final
        ahorro = monto;
        System.out.printf("El ahorro final tras los 20 anios es de: $%.2f", ahorro);
        
        // Finalizamos el programa
        System.out.println("\n\nFin del programa.");
        System.exit(0);
    }
}
