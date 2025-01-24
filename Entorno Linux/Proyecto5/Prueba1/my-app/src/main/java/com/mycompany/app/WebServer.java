package com.mycompany.app;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.io.InputStream;  
import java.util.StringTokenizer;
import java.util.List;
import java.util.Arrays;

import com.fasterxml.jackson.databind.DeserializationFeature;   
import com.fasterxml.jackson.databind.ObjectMapper;             
import com.fasterxml.jackson.databind.PropertyNamingStrategy;   

public class WebServer {
   
    private static final String STATUS_ENDPOINT = "/status";
    private static final String HOME_PAGE_ENDPOINT = "/";
    private static final String HOME_PAGE_UI_ASSETS_BASE_DIR = "/ui_assets/";
    private static final String ENDPOINT_PROCESS = "/procesar_datos";
	
	// Direcciones de solicitud
	private String WORKER_ADDRESS_1 = "http://localhost:5000/";

    private final int port; 
    private HttpServer server; 
    private final ObjectMapper objectMapper;

    public WebServer(int port) {
        this.port = port;
        this.objectMapper = new ObjectMapper();
        this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public void startServer() {
        try {
            this.server = HttpServer.create(new InetSocketAddress(port), 0);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        HttpContext statusContext = server.createContext(STATUS_ENDPOINT); 
        HttpContext taskContext = server.createContext(ENDPOINT_PROCESS);
        HttpContext homePageContext = server.createContext(HOME_PAGE_ENDPOINT);
        statusContext.setHandler(this::handleStatusCheckRequest);
        taskContext.setHandler(this::handleTaskRequest);
        homePageContext.setHandler(this::handleRequestForAsset);

        server.setExecutor(Executors.newFixedThreadPool(8));
        server.start();
    }

    private void handleRequestForAsset(HttpExchange exchange) throws IOException {
        if (!exchange.getRequestMethod().equalsIgnoreCase("get")) {
            exchange.close();
            return;
        }

        byte[] response;

        String asset = exchange.getRequestURI().getPath(); 

        if (asset.equals(HOME_PAGE_ENDPOINT)) { 
            response = readUiAsset(HOME_PAGE_UI_ASSETS_BASE_DIR + "index.html");
        } else {
            response = readUiAsset(asset); 
        }
        addContentType(asset, exchange);
        sendResponse(response, exchange);
    }

    private byte[] readUiAsset(String asset) throws IOException {
        InputStream assetStream = getClass().getResourceAsStream(asset);

        if (assetStream == null) {
            return new byte[]{};
        }
        return assetStream.readAllBytes(); 
    }

    private static void addContentType(String asset, HttpExchange exchange) {

        String contentType = "text/html";  
        if (asset.endsWith("js")) {
            contentType = "text/javascript";
        } else if (asset.endsWith("css")) {
            contentType = "text/css";
        }
        exchange.getResponseHeaders().add("Content-Type", contentType);
    }

    private void handleTaskRequest(HttpExchange exchange) throws IOException {
		if (!exchange.getRequestMethod().equalsIgnoreCase("post")) { 
			exchange.close();
			return;
		}

		try {
			// Contador de los servidores activos
			int conteoServidores = 0;
			
			// Obtención del valor n
			String contentType = exchange.getRequestHeaders().getFirst("Content-Type");
			System.out.println("Content-Type: " + contentType);
			FrontendSearchRequest frontendSearchRequest = objectMapper.readValue(exchange.getRequestBody().readAllBytes(), FrontendSearchRequest.class); 
			int number = frontendSearchRequest.getSearchQuery();
			
			String responseMessage = "";
			Aggregator aggregator = new Aggregator();
			
			/* Contar los servidores activos */
			String statusURL = WORKER_ADDRESS_1 + "status";
			List<String> estaActivo = aggregator.sendTasksToWorkers(Arrays.asList(statusURL));
			for (String result : estaActivo) {
				System.out.println("Response from worker: " + result);
                if(result.equals("1")) {
					conteoServidores++;
				}
            }
			// Construcción de la String de servidores activos
			String servidoresActivos = "Número de servidores activos: " + conteoServidores + "\n";
			
			// Construcción de la URL para la petición de procesamiento
			String finalURL = WORKER_ADDRESS_1 + "compare?n=" + number;
			
			// Envío de listas a los trabajadores
			long startTime = System.nanoTime();    // Inicio del procesamiento
			List<String> results = aggregator.sendTasksToWorkers(Arrays.asList(finalURL));
            // Recibe los resultados e imprime los resultados
            for (String result : results) {
                responseMessage = responseMessage + result + "\n";
            }
			long finishTime = System.nanoTime();    // Final del procesamiento
			long elapsedTimeNano = finishTime - startTime;
            long elapsedSeconds = elapsedTimeNano / 1_000_000_000;
            long elapsedMilliseconds = (elapsedTimeNano % 1_000_000_000) / 1_000_000;
            String tiempo = String.format("Tiempo de procesamiento: %d segundos con %d milisegundos\n", elapsedSeconds, elapsedMilliseconds);
			
			// String cadenaPrueba = "asdf";
			String respuestaFinal = servidoresActivos + tiempo + responseMessage;
			FrontendSearchResponse frontendSearchResponse = new FrontendSearchResponse(String.valueOf(number), respuestaFinal);
			byte[] responseBytes = objectMapper.writeValueAsBytes(frontendSearchResponse);
			sendResponse(responseBytes, exchange);

		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
	}

    private void handleStatusCheckRequest(HttpExchange exchange) throws IOException {
        if (!exchange.getRequestMethod().equalsIgnoreCase("get")) {
            exchange.close();
            return;
        }

        String responseMessage = "El servidor está vivo\n";
        sendResponse(responseMessage.getBytes(), exchange);
    }

    private void sendResponse(byte[] responseBytes, HttpExchange exchange) throws IOException {
        exchange.sendResponseHeaders(200, responseBytes.length);
        OutputStream outputStream = exchange.getResponseBody();
        outputStream.write(responseBytes);
        outputStream.flush();
        outputStream.close();
    }
}
