/*
 *  MIT License
 *
 *  Copyright (c) 2019 Michael Pogrebinsky - Distributed Systems & Cloud Computing with Java
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
 */

import networking.WebClient;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

// Clase Aggregator
public class Aggregator {
	// Único dato del tipo WebClient
    private WebClient webClient;
	// Órden e índice de los servidores
	private int[] orden;
	private String[] servers;
	
	// Su constructor recibe únicamente un objeto del tipo Web Client
    public Aggregator() {
        this.webClient = new WebClient();
    }
	
	// El único método que tiene la clase es la que recibe la lista de direcciones de trabajadores y tareas que desarrollan
    public List<String> sendTasksToWorkers(List<String> workersAddresses, List<String> tasks) {
        // Manejo de comunicación asíncrona
		// Permite continuar la ejecución de código bloqueante, reactivándose cuando hayan datos disponibles
		// Éste arreglo almacena las respuestas futuras de los servidores
		CompletableFuture<String>[] futures = new CompletableFuture[tasks.size()];
		
		// Inicializamos a los arreglos
		orden = new int[tasks.size()];
		servers = new String[tasks.size()];
		
		// Se itera la lista para obtener las direcciones de los trabajadores y sus tareas
        for (int i = 0; i < workersAddresses.size(); i++) {
            String workerAddress = workersAddresses.get(i);
            String task = tasks.get(i);
			servers[i] = workerAddress + " --- " + task;
			
			// Se almacenan las tareas en formato de bytes y se envían las tareas asíncronas
            byte[] requestPayload = task.getBytes();
			// Se asignan las tareas a cada uno de los Futures
            futures[i] = webClient.sendTask(workerAddress, requestPayload);
        }
		
		// Evaluamos los servidores que han terminado primero
		boolean bandera = true;
		while(bandera) {
			for(int j = 0; j < 2; j++){
				// System.out.println("futures["+j+"].isDone() = " + futures[j].isDone());
				if (true == futures[j].isDone()) {
					// Establecemos el orden de los servers
					orden[0] = j;
					if(j == 0)
						orden[1] = j + 1;
					else
						orden[1] = j - 1;
					orden[2] = j;
					servers[2] = workersAddresses.get(j) + " --- " + tasks.get(2);
					bandera = false;
				}
			}
		}
		
		// Se asigna la tarea restante al server que terminó primero
        futures[2] = webClient.sendTask(workersAddresses.get(orden[2]), tasks.get(2).getBytes());
		
		// Se declara una lista de resultados, que se agregan conforme vayan llegando
        List<String> results = new ArrayList<>();
		int iter = 0;
		for (int i = 0; i < tasks.size(); i++) {
			results.add("Tarea " + (int)(iter + 1) + " (" + servers[iter] + "):" + futures[i].join() + "\n");
			iter++;
		}

        return results;
    }
}
