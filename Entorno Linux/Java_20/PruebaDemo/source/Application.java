import java.util.Arrays;
import java.util.List;

public class Application {
    private static final String WORKER_ADDRESS_1 = "http://localhost:4444/task";

    public static void main(String[] args) {
        Aggregator aggregator = new Aggregator();
        Demo object = new Demo("4", "4");
        byte[] serializedObject = SerializationUtils.serialize(object);
        System.out.println("Objeto serializado byte por byte (-128 a 127) (en cliente):");
        System.out.println(Arrays.toString(serializedObject) + "\n");

        List<Object> results = aggregator.sendTasksToWorkers(
            Arrays.asList(WORKER_ADDRESS_1),
            Arrays.asList(serializedObject)
        );

        for (Object result : results) {
            Demo demoCliente = (Demo)result;
            System.out.println("Objeto deserializado: " + demoCliente);
			System.out.println("Valores del objeto: ");
			System.out.println("a: " + demoCliente.getA());
			System.out.println("b: " + demoCliente.getB());
        }
    }
}
