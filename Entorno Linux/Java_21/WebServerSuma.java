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
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.util.concurrent.Executors;

public class WebServerSuma {

    private int solicitudes = 0;
    private static final String STATUS_ENDPOINT = "/status";    // Endpoint Status
    private static final String SUMA_ENDPOINT = "/suma";    // Endpoint Search Token

    private final int port;    // Puerto
    private HttpServer server;    // Tipo de HTTP Server

    public static void main(String[] args) {
        int serverPort = 4446;    // Se define el puerto por defecto del servidor
        if (args.length == 1) {
            serverPort = Integer.parseInt(args[0]);    // Se asigna un puerto por línea de comandos, de hacerse
        }

        WebServerSuma webServer = new WebServerSuma(serverPort);    // Se instancia un objeto WebServerSuma
        webServer.startServer();    // Se ejecuta el método principal del servidor para empezar su configuración

        System.out.println("Servidor escuchando en el puerto " + serverPort);
    }

    // Constructor para crear un objeto WebServer
    public WebServerSuma(int port) {
        this.port = port;
    }

    public void startServer() {
        try {
            this.server = HttpServer.create(new InetSocketAddress(port), 0);    // Crea una instancia de Socket vinculada a una IP y puerto especificado, además de una lista de solicitudes pendientes
        } catch (IOException e) {
            return;
        }

        // Contextos para los Endpoint a trabajar
        HttpContext statusContext = server.createContext(STATUS_ENDPOINT);
        HttpContext sumaContext = server.createContext(SUMA_ENDPOINT);

        // Implementamos el manejador para los contextos sin inicializar
        statusContext.setHandler(this::handleStatusCheckRequest);
        sumaContext.setHandler(this::handleSumaRequest);

        // Establece un objeto de tipo Executor al server, necesario para su ejecución
        server.setExecutor(Executors.newFixedThreadPool(8));    // Pool de 8 hilos iniciado por el ejecutor
        server.start();    // Se inicia la ejecución del server en un hilo dejado en segundo plano
    }

    // Método para gestionar el Endpoint Status (maneja todo lo relacionado con la transacción HTTP Cliente-Servidor)
    private void handleStatusCheckRequest(HttpExchange exchange) throws IOException {
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

    // Método para gestionar el Endpoint Servicios
    private void handleSumaRequest(HttpExchange exchange) throws IOException {
        // Verificamos que la solicitud pertenezca al método GET
        if (!exchange.getRequestMethod().equalsIgnoreCase("get")) {
            // Si no es GET, se cierra el método
            exchange.close();
            return;
        }

        // Tomamos los parámetros de la URL
        URI uri = exchange.getRequestURI();
        String query = uri.getQuery();
        String[] parametros = query.split("&");
        String numero = parametros[0].split("=")[1];
		
		// Determinamos si es o no número primo
		String recibido = "Parametros:\nNumero: " + numero;
		String respuesta = "";
		int numeroRecibido = Integer.parseInt(numero);
		int resultado = sumaImpares(numeroRecibido);
		respuesta = "El resultado de la suma es: " + resultado;
        
        // Enviamos mensaje de vuelta
		System.out.println(respuesta);
        sendResponse(respuesta.getBytes(), exchange);
        
        // Imprimimos la cantidad de solicitudes realizadas hasta el momento
        solicitudes++;
        System.out.println("Se han realizado " + solicitudes + " solicitudes hasta el momento.");
    }
	
	// Método para definir si un número es o no primo
	public static int sumaImpares(int numero) {
		int resultadoSuma = 0;
		for(int i = 0; i < numero; i++) {
			if(i % 2 != 0) {
				resultadoSuma += i;
			}
		}
		return resultadoSuma;
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
