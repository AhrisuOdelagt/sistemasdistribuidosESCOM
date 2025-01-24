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
    private static final String WORKER_ADDRESS_1 = "http://localhost:4444/task";

    public static void main(String[] args) {
        Aggregator aggregator = new Aggregator();
        
        // Crear un polígono con la clase PoligonoIrregular y añadir tres vértices
        PoligonoIrregular pol_1 = new PoligonoIrregular();
        pol_1.anadeVertice();
        pol_1.anadeVertice();
        pol_1.anadeVertice();
		System.out.println("Objeto inicial en cliente " + "(Numero de vertices: " + pol_1.getNumVert() + "): " + pol_1);
        
        while(true) {
            // Serializar el objeto PoligonoIrregular
            byte[] serializedObject = SerializationUtils.serialize(pol_1);

            // Enviar la tarea al servidor y recibir el resultado
            List<Object> results = aggregator.sendTasksToWorkers(
                Arrays.asList(WORKER_ADDRESS_1),
                Arrays.asList(serializedObject)
            );

            // Deserializar la respuesta e incrementar un vértice
            for (Object result : results) {
                pol_1 = (PoligonoIrregular) result;
				pol_1.anadeVertice();
				System.out.println("Objeto deserializado en cliente: " + "(Numero de vertices: " + pol_1.getNumVert() + "): " + pol_1);
            }

            // Sleep de cuatro segundos
            try {
                Thread.sleep(4444);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
