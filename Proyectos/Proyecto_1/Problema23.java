/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Demian
 */
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Problema23 {
    
    /* Escribe un programa que solicite edades y estaturas de personas y guarde los datos en un
archivo de texto de la siguiente manera: edad, estatura. El programa debe crear el archivo en caso de
que este no exista. En caso de que el archivo ya exista entonces deberá añadir los datos al archivo. El
programa debe terminar cuando el usuario introduzca el número 0 como edad. */
    public static void main(String args[]){
        // Generamos una nueva instancia del Scanner
        Scanner console = new Scanner(System.in);
        // Definimos a las variables que vamos a utilizar
        int edad = 0, estatura = 0;
        String nombre_archivo = "personas.txt";
        
        // Creamos el archivo si no existe
        File archivo = new File(nombre_archivo);
        if(!archivo.exists()) {
            try {
                archivo.createNewFile();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        
        // Solicitamos estaturas y edades de personas
        // Abrimos el printwriter
        try {
            PrintWriter pw = new PrintWriter(new FileWriter(nombre_archivo, true));
            while(true) {
                System.out.println("Escriba una estatura: \t");
                estatura = console.nextInt();
                System.out.println("Escriba una edad (0 para salir): \t");
                edad = console.nextInt();
                if(edad == 0) {
                    pw.close();
                    break;
                }
                else {
                    pw.println("Nueva persona: ");
                    pw.println("\tEstatura: " + estatura);
                    pw.println("\tEdad: " + edad + "\n");
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        
        // Finalizamos el programa
        System.out.println("\n\nFin del programa.");
        System.exit(0);
    }
}
