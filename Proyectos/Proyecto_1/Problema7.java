/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Demian
 */
import java.util.Scanner;

public class Problema7 {
    
    /* La Comisión Federal de Electricidad cobra el consumo de electricidad de acuerdo con un
tabulador basado en los kilowatts consumidos en el periodo. La tabla es la siguiente:
Costo del kW para Hogares:
De 0 a 250 kW el costo por kW es de $0.65
De 251 a 500 kW el costo por kW es de $0.85
De 501 a 1200 kW el costo por kW es de $1.50
De 1201 a 2100 kW el costo por kW es de $2.50 De
2101 kW hacia arriba el costo por kW es de $3.00
Costo del kW para Negocios:
$5.00, el costo es fijo por kilowatt sin importar la cantidad consumida.
Ejemplo:
a) Si en el hogar se consumen 737 kW durante el periodo entonces el total a pagar es de:
250*0.65 + 250*0,85 + 237* 1.50 = 162.50 + 212.50 + 355.50 = $730.50
b) Si en un negocio se consumen 250 kW, el valor a pagar serían de: 250* 5 = $1,250
Escribe un programa que pregunte la cantidad de kW consumidos, si el cliente tiene un contrato de
Hogar o de Negocio y dé como resultado la cantidad a pagar. */
    public static void main(String args[]){
        // Generamos una nueva instancia del Scanner
        Scanner console = new Scanner(System.in);
        // Definimos a las variables que vamos a utilizar
        double kilowatts, pago = 0.0;
        String contrato;
        
        // Solicitamos la información al cliente de la empresa
        System.out.println("Digite el total de kW consumidos en el periodo: \t");
        while(true) {
            kilowatts = console.nextDouble();
            if(kilowatts >= 0) {
                break;
            }
            else {
                System.out.println("Entrada invalida. Intente nuevamente.");
            }
        }
        console.nextLine();
        System.out.println("Escriba si su contrato es de hogar o negocio (h/n): \t");
        while(true) {
            contrato = console.nextLine();
            if("h".equals(contrato) || "n".equals(contrato)) {
                break;
            }
            else {
                System.out.println("Entrada invalida. Intente nuevamente.");
            }
        }
        
        // Calculamos la cantidad a pagar
        if("h".equals(contrato)) {
            if(kilowatts >= 0 && kilowatts <= 250) {
                pago = kilowatts * 0.65;
            }
            else if(kilowatts >= 251 && kilowatts <= 500) {
                kilowatts = kilowatts - 250;
                pago = (250 * 0.65) + (kilowatts * 0.85);
            }
            else if(kilowatts >= 501 && kilowatts <= 1200) {
                kilowatts = kilowatts - 500;
                pago = (250 * 0.65) + (250 * 0.85) + (kilowatts * 1.50);
            }
            else if(kilowatts >= 1201 && kilowatts <= 2100) {
                kilowatts = kilowatts - 1200;
                pago = (250 * 0.65) + (250 * 0.85) + (700 * 1.50) + (kilowatts * 2.50);
            }
            else if(kilowatts >= 2101) {
                kilowatts = kilowatts - 2100;
                pago = (250 * 0.65) + (250 * 0.85) + (700 * 1.50) + (900 * 2.50) + (kilowatts * 3);
            }
        }
        else {
            pago = kilowatts * 5;
        }
        
        // Regresamos la cantidad a pagar
        System.out.printf("\nLa cantidad a pagar es de: $%.2f.", pago);
        
        // Finalizamos el programa
        System.out.println("\n\nFin del programa.");
        System.exit(0);
    }
}
