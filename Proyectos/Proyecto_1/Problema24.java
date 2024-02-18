/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Demian
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Problema24 {
    
    /* Leer un archivo de texto que contiene un número entero por cada línea e indicar cuál es el
número mayor dentro del archivo y el número de renglón en que se encuentra. */
    public static void main(String args[]){
        // Definimos a las variables que vamos a utilizar
        int i = 0, n = 0, n1 = 0, linea = 0;
        String nombre_archivo = "numeros.txt", lineaStr = "";
        
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
        
        // Leemos el contenido del archivo linea por linea
        int numeros[] = new int[(int)archivo.length()];
        try {
            FileReader fr = new FileReader(archivo);
            BufferedReader br = new BufferedReader(fr);
            while ((lineaStr = br.readLine()) != null) {
                numeros[i] = Integer.parseInt(lineaStr);
                i++;
            }
            
            // Hallamos al número mayor y su posición
            n1 = numeros[0];
            for(i = 0; i < numeros.length; i++) {
                n = numeros[i];
                if(n >= n1) {
                   n1 = n;
                }
            }
            for(i = 0; i < numeros.length; i++) {
                if(numeros[i] == n1) {
                    linea = i + 1;
                }
            }
            
            // Retornamos los resultados
            System.out.println("El numero mas grande en el archivo es: " + n1 + ".");
            System.out.println("Dicho numero se halla en la linea: " + linea + ".");
            br.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        
        // Finalizamos el programa
        System.out.println("\n\nFin del programa.");
        System.exit(0);
    }
}
