import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

public class Comparador {

  public static void main(String[] args) {
    // TODO Auto-generated method stub

    List<String>lista=Arrays.asList("pedro", "angel", "eugenio", "ana", "maria");

    Comparator<String> comparadorA= (pa, pb)->pa.compareTo(pb);
    // Comparator<Persona> comparadorB=comparadorA.thenComparing((pa,pb)->pa.getApellido2().compareTo(pb.getApellido2()));
    lista.sort(comparadorA);

    lista.forEach(System.out::println);

  }

}