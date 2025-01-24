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

import java.util.Arrays;
import java.util.List;

public class Application {
	// Se definen las cadenas que corresponden a las direcciones de los Endpoints para ambos servidores
    private static final String WORKER_ADDRESS_1 = "http://34.122.121.164:80/searchtoken";

    public static void main(String[] args) {
		// Se instancia un Aggregator
        Aggregator aggregator = new Aggregator();
		// Se inicializan las cadenas correspondientes a los valores a multiplicar
        String task1 = "1757600,SIN";
        String task2 = "1757600,IPN";
		String task3 = "1757600,END";
		String task4 = "1757600,NYD";
		
		// Se envían las tareas a los trabajadores
        List<String> results = aggregator.sendTasksToWorkers(Arrays.asList(WORKER_ADDRESS_1, WORKER_ADDRESS_1, WORKER_ADDRESS_1, WORKER_ADDRESS_1),
                Arrays.asList(task1, task2, task3, task4));
		
		// Recibe los resultados e imprime los resultados
        for (String result : results) {
            System.out.println(result + "\n");
        }
    }
}
