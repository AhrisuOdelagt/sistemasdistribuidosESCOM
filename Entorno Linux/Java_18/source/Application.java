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
    private static final String WORKER_ADDRESS_1 = "http://34.125.215.107:80/searchtoken";
	private static final String WORKER_ADDRESS_2 = "http://34.125.187.88:80/searchtoken";
	private static final String WORKER_ADDRESS_3 = "http://34.16.199.134:80/searchtoken";
	private static final String WORKER_ADDRESS_4 = "http://34.125.222.166:80/searchtoken";

    public static void main(String[] args) {
		// Se instancia un Aggregator
        Aggregator aggregator = new Aggregator();
		// Se inicializan las cadenas correspondientes a los valores a multiplicar
        String task1 = "1757600,IPN";
		
		// Se envían las tareas a los trabajadores
        List<String> results = aggregator.sendTasksToWorkers(Arrays.asList(WORKER_ADDRESS_1, WORKER_ADDRESS_2, WORKER_ADDRESS_3, WORKER_ADDRESS_4),
                Arrays.asList(task1, task1, task1, task1));
		
		// Recibe los resultados e imprime los resultados
        for (String result : results) {
            System.out.println(result + "\n");
        }
    }
}
