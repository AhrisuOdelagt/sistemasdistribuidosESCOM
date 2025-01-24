package com.mycompany.app;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

public class Aggregator {
    private WebClient webClient;

    public Aggregator() {
        this.webClient = new WebClient();
    }

    public List<String> sendTasksToWorkers(List<String> workersAddresses) {
        CompletableFuture<String>[] futures = new CompletableFuture[workersAddresses.size()];

        for (int i = 0; i < workersAddresses.size(); i++) {
            String workerAddress = workersAddresses.get(i);
            futures[i] = webClient.sendTask(workerAddress);
        }

        List<String> results = new ArrayList();
        for (int i = 0; i < workersAddresses.size(); i++) {
            results.add(futures[i].join());
        }

        return results;
    }
}
