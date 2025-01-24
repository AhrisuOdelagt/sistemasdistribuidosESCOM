import networking.WebClient;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

public class Aggregator {
    private WebClient webClient;

    public Aggregator() {
        this.webClient = new WebClient();
    }

    public List<Object> sendTasksToWorkers(List<String> workersAddresses, List<byte[]> tasks) {
        CompletableFuture<byte[]>[] futures = new CompletableFuture[workersAddresses.size()];

        for (int i = 0; i < workersAddresses.size(); i++) {
            String workerAddress = workersAddresses.get(i);
            byte[] task = tasks.get(i);
            futures[i] = webClient.sendTask(workerAddress, task);
        }

        List<Object> results = new ArrayList<>();
        for (CompletableFuture<byte[]> future : futures) {
            byte[] responseBytes = future.join();
            Object responseObject = SerializationUtils.deserialize(responseBytes);
            results.add(responseObject);
        }

        return results;
    }
}
