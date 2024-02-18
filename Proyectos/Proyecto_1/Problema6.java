/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Demian
 */
import java.util.Scanner;

public class Problema6 {
    
    /* La cadena de Comida “Hamburguesas Teresita” quiere dar una promoción a las mujeres entre 16
y 22 años o aquellas con nombre “Teresa”, “Tere” o “Teresita”, en estos casos, el sistema debe dar un
descuento de 7% en el valor de la compra. Escribir un programa que solicite el valor de la compra, el
primer nombre, edad y sexo del cliente e indique el monto final a pagar. El nombre del cliente puede ser
escrito en mayúsculas o minúsculas sin que esto afecte el cálculo final. */
    public static void main(String args[]){
        // Generamos una nueva instancia del Scanner
        Scanner console = new Scanner(System.in);
        // Definimos a las variables que vamos a utilizar
        double valorCompra;
        String primerNombre, sexo;
        int edad;
        boolean descuento_1 = false, descuento_2 = false, descuento_3 = false, descuento_f = false;
        
        // Solicitamos la información al cliente de “Hamburguesas Teresita”
        System.out.println("Digite el valor de su compra: \t");
        valorCompra = console.nextDouble();
        console.nextLine();
        System.out.println("Escriba su primer nombre: \t");
        primerNombre = console.nextLine().toLowerCase();
        System.out.println("Escriba su edad: \t");
        while(true) {
            edad = console.nextInt();
            if(edad >= 0) {
                break;
            }
            else {
                System.out.println("Entrada invalida. Intente nuevamente.");
            }
        }
        console.nextLine();
        System.out.println("Escriba su sexo (m/f): \t");
        while(true) {
            sexo = console.nextLine();
            if("m".equals(sexo) || "f".equals(sexo)) {
                break;
            }
            else {
                System.out.println("Entrada invalida. Intente nuevamente.");
            }
        }
        
        // Verificamos si se cumple alguna condición para el descuento
        if("f".equals(sexo)) {
            descuento_1 = true;
        }
        if((edad >= 16 && edad <= 22)) {
            descuento_2 = true;
        }
        if(("teresa".equals(primerNombre) || "tere".equals(primerNombre) || "teresita".equals(primerNombre))) {
            descuento_3 = true;
        }
        // Definimos si hay descuento o no según lo recopilado
        if((descuento_1 && descuento_2) || (descuento_1 && descuento_3)) {
            descuento_f = true;
        }
        
        // Regresamos el monto a pagar
        if(descuento_f) {
            valorCompra = valorCompra - valorCompra * 0.07;
        }
        System.out.printf("\nEl monto a pagar es de: $%.2f.", valorCompra);
        
        // Finalizamos el programa
        System.out.println("\n\nFin del programa.");
        System.exit(0);
    }
}
