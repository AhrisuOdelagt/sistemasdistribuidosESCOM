/*@FunctionalInterface
public interface Consumer <T> {
        void accept(T t);
}*/
// Observe que <T> representa a una clase que puede ser de cualquier tipo.
// El siguiente es un ejemplo de su uso:
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        // Creamos una lista de números enteros
        List<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        // Definimos un Consumer que imprime el cuadrado de un número
        Consumer<Integer> squarePrinter = (Integer number) -> {
            System.out.println("El cuadrado de " + number + " es: " + (number * number));
        };
        // Aplicamos el Consumer a cada elemento de la lista
        numbers.forEach(squarePrinter);
    }
}
