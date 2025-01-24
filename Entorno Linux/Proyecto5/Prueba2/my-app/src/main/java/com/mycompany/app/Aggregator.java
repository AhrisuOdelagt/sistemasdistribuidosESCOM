/*    --- Información del proyecto ----
	No. de proyecto: Final
	Nombre completo: Franco Olvera Demian Oder
	Grupo: 7CM1
*/

package com.mycompany.app;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Aggregator {
    private WebClient webClient;

    public Aggregator() {
        this.webClient = new WebClient();
    }

    public List<String> sendTasksToWorkers(List<String> workersAddresses) {
        CompletableFuture<String>[] futures = new CompletableFuture[workersAddresses.size()];

        for (int i = 0; i < workersAddresses.size(); i++) {
            String workerAddress = workersAddresses.get(i);
            futures[i] = webClient.sendTask(workerAddress)
                                  .orTimeout(6, TimeUnit.SECONDS)  // Tiempo de espera total (6 segundos)
                                  .exceptionally(ex -> "Error: " + ex.getMessage());
        }

        List<String> results = new ArrayList<>();
        for (int i = 0; i < workersAddresses.size(); i++) {
            try {
                results.add(futures[i].join());
            } catch (Exception e) {
                results.add("Error: " + e.getMessage());
            }
        }

        return results;
    }
}
