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

// Librerías para construir un servidor HTTP en Java
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.concurrent.Executors;

public class WebServer {
	private int solicitudes = 0;
    private static final String TASK_ENDPOINT = "/task";    // Endpoint Task
    private static final String STATUS_ENDPOINT = "/status";    // Endpoint Status
	private static final String SEARCHTOKEN_ENDPOINT = "/searchtoken";    // Endpoint Search Token

    private final int port;    // Puerto
    private HttpServer server;    // Tipo de HTTP Server

    public static void main(String[] args) {
        int serverPort = 4444;    // Se define el puerto por defecto del servidor
        if (args.length == 1) {
            serverPort = Integer.parseInt(args[0]);    // Se asigna un puerto por línea de comandos, de hacerse
        }

        WebServer webServer = new WebServer(serverPort);    // Se instancia un objeto WebServer
        webServer.startServer();    // Se ejecuta el método principal del servidor para empezar su configuración

        System.out.println("Servidor escuchando en el puerto " + serverPort);
    }
	
	// Constructor para crear un objeto WebServer
    public WebServer(int port) {
        this.port = port;
    }

    public void startServer() {
        try {
            this.server = HttpServer.create(new InetSocketAddress(port), 0);    // Crea una instancia de Socket vinculada a una IP y puerto especificado, además de una lista de solicitudes pendientes
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

		// Contextos para los Endpoint a trabajar
        HttpContext statusContext = server.createContext(STATUS_ENDPOINT);
        HttpContext taskContext = server.createContext(TASK_ENDPOINT);
		HttpContext searchtokenContext = server.createContext(SEARCHTOKEN_ENDPOINT);
		
		// Implementamos el manejador para los contextos sin inicializar
        statusContext.setHandler(this::handleStatusCheckRequest);
        taskContext.setHandler(this::handleTaskRequest);
		searchtokenContext.setHandler(this::handleSearchTokenRequest);
		
		// Establece un objeto de tipo Executor al server, necesario para su ejecución
        server.setExecutor(Executors.newFixedThreadPool(8));    // Pool de 8 hilos iniciado por el ejecutor
        server.start();    // Se inicia la ejecución del server en un hilo dejado en segundo plano
    }
	
	// Método para gestionar el Endpoint Task (maneja todo lo relacionado con la transacción HTTP Cliente-Servidor)
    private void handleTaskRequest(HttpExchange exchange) throws IOException {
		/* Agregamos un Sleep de cinco segundos */
		/*try {
			Thread.sleep(5000);
		}
		catch(InterruptedException e) {
			e.printStackTrace();
		}*/
		
        // Verificamos que la solicitud pertenezca al método POST
		if (!exchange.getRequestMethod().equalsIgnoreCase("post")) {
			// Si no es POST, el intercambio se cierra
            exchange.close();
            return;
        }
		
		// Éste método recupera todos los Headers del intercambio a través de un Map
        Headers headers = exchange.getRequestHeaders();
		// Si los Headers contienen a X-Test y su valor asociado es true, entonces procede
        if (headers.containsKey("X-Test") && headers.get("X-Test").get(0).equalsIgnoreCase("true")) {
            // Retorna una respuesta fija de prueba y se envía de vuelta
			String dummyResponse = "123\n";
            sendResponse(dummyResponse.getBytes(), exchange);
            return;
        }
		
		// Si entre los Headers se encuentra X-Debug y tiene un true, entonces activamos la depuración
        boolean isDebugMode = false;
        if (headers.containsKey("X-Debug") && headers.get("X-Debug").get(0).equalsIgnoreCase("true")) {
            isDebugMode = true;
        }
		
		// Generamos la cantidad de tiempo que tomó el procesamiento
        long startTime = System.nanoTime();    // Inicio del procesamiento

        byte[] requestBytes = exchange.getRequestBody().readAllBytes();    // Recupera la información de todo el cuerpo del mensaje
        System.out.println("Objeto serializado byte por byte (-128 a 127) (en servidor):");
        System.out.println(Arrays.toString(requestBytes) + "\n");
		byte[] responseBytes = calculateResponse(requestBytes);    // Calculamos la multiplicación con los datos enviados

        long finishTime = System.nanoTime();    // Final del procesamiento
		
		// Si está en modo Debug, entonces devolvemos esa información en los Headers de respuesta
        if (isDebugMode) {
            String debugMessage = String.format("La operación tomó %d nanosegundos", finishTime - startTime);
            exchange.getResponseHeaders().put("X-Debug-Info", Arrays.asList(debugMessage));
        }
		
		// Regresamos la respuesta mediante el interacambio
        sendResponse(responseBytes, exchange);
		
		// Imprimimos la cantidad de solicitudes realizadas hasta el momento
		solicitudes++;
		System.out.println("Se han realizado " + solicitudes + " solicitudes hasta el momento.");
    }
	
	// Función que calcula la respuesta de la multiplicación
    private byte[] calculateResponse(byte[] requestBytes) {
		// Deserializamos el objeto serializado
		Demo objeto = null;
		objeto = (Demo)SerializationUtils.deserialize(requestBytes);
        String[] stringNumbers = {objeto.a, objeto.b};

        BigInteger result = BigInteger.ONE;

        for (String number : stringNumbers) {
            BigInteger bigInteger = new BigInteger(number);
            result = result.multiply(bigInteger);
        }

        return String.format("El resultado de la multiplicación es %s\n", result).getBytes();
    }
	
	// Método para gestionar el Endpoint Status (maneja todo lo relacionado con la transacción HTTP Cliente-Servidor)
    private void handleStatusCheckRequest(HttpExchange exchange) throws IOException {
		/* Agregamos un Sleep de cinco segundos */
		/*try {
			Thread.sleep(5000);
		}
		catch(InterruptedException e) {
			e.printStackTrace();
		}*/
        // Verificamos que la solicitud pertenezca al método GET
		if (!exchange.getRequestMethod().equalsIgnoreCase("get")) {
            // Si no es GET, se cierra el método
			exchange.close();
            return;
        }
		
		// Respondemos con el mensaje de que el servidor está vivo
        String responseMessage = "El servidor está vivo\n";
        sendResponse(responseMessage.getBytes(), exchange);
		
		// Imprimimos la cantidad de solicitudes realizadas hasta el momento
		solicitudes++;
		System.out.println("Se han realizado " + solicitudes + " solicitudes hasta el momento.");
    }
	
	/* Método que gestiona al Endpoint Search Token */
	private void handleSearchTokenRequest(HttpExchange exchange) throws IOException {
		// Verificamos que la solicitud pertenezca al método POST
		if (!exchange.getRequestMethod().equalsIgnoreCase("post")) {
			// Si no es POST, el intercambio se cierra
            exchange.close();
            return;
        }
		
		// Éste método recupera todos los Headers del intercambio a través de un Map
        Headers headers = exchange.getRequestHeaders();
		
		// Si entre los Headers se encuentra X-Debug y tiene un true, entonces activamos la depuración
        boolean isDebugMode = false;
        if (headers.containsKey("X-Debug") && headers.get("X-Debug").get(0).equalsIgnoreCase("true")) {
            isDebugMode = true;
        }
		
		// Generamos la cantidad de tiempo que tomó el procesamiento
        long startTime = System.nanoTime();    // Inicio del procesamiento
		
		// Ejecutamos la instrucción
		byte[] requestBytes = exchange.getRequestBody().readAllBytes();    // Recupera la información de todo el cuerpo del mensaje
        String bodyString = new String(requestBytes);
		String[] partesString = bodyString.split(",");
		// Accedemos a las cadenas ya separadas
		int veces = Integer.parseInt(partesString[0]);
		String palabra = partesString[1]; // "SOL"
		
		/* Inicio del ejercicio 5 adaptado */
		int palabras = veces * 4;
		String cadena_usuario = palabra + "";
		int contador_usuario = 0, contador_index[] = new int[90000];
		StringBuilder cadenota = new StringBuilder("");
		StringBuilder leer_cadena = new StringBuilder("");
		
		// Generamos las palabras
		for(int i = 0; i < palabras; i++) {
			// Creamos las mayúsculas aleatorias
			if(((i + 1) % 4) != 0)
				cadenota.append((char)(Math.random() * 25 + 65));
			else
				cadenota.append((char)(32));
		}
		
		// Leemos las palabras
		int i = 0, j = 0;
		while((cadenota.indexOf(cadena_usuario, i + 1)) != -1) {
			contador_usuario++;
			i = cadenota.indexOf(cadena_usuario, i + 1);
			contador_index[j] = i;
			j++;
		}
		
		// Generamos la cadena con las coincidencias
		String coincidencias = "\nLa cadena " + cadena_usuario + " fue leida: " + contador_usuario + " veces.";
		
		// Finalizamos el tiempo del conteo del procesamiento
		long finishTime = System.nanoTime();    // Final del procesamiento
        long tiempoTotalMili = (finishTime - startTime) / 1000000;
        long tiempoSegundos = tiempoTotalMili / 1000;
        long miliRestantes = tiempoTotalMili % 1000;
		
		// Si está en modo Debug, entonces devolvemos esa información en los Headers de respuesta
        if (isDebugMode) {
            String debugMessage = String.format("Tiempo: %d nanosegundos = %d milisegundos = %d segundos con %d milisegundos", finishTime - startTime, tiempoTotalMili, tiempoSegundos, miliRestantes);
            exchange.getResponseHeaders().put("X-Debug-Info", Arrays.asList(debugMessage));
        }
		
		// Retorno de prueba
        String responseMessage = coincidencias;
        sendResponse(responseMessage.getBytes(), exchange);
		
		// Imprimimos la cantidad de solicitudes realizadas hasta el momento
		solicitudes++;
		System.out.println("Se han realizado " + solicitudes + " solicitudes hasta el momento.");
	}
	
	// Método que genera la respuesta que se envía al cliente desde el servidor
    private void sendResponse(byte[] responseBytes, HttpExchange exchange) throws IOException {
        exchange.sendResponseHeaders(200, responseBytes.length);
        OutputStream outputStream = exchange.getResponseBody();
        outputStream.write(responseBytes);
        outputStream.flush();
        outputStream.close();
        exchange.close();
    }
}
