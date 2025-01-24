/*    --- Información del proyecto ----
	No. de proyecto: Final
	Nombre completo: Franco Olvera Demian Oder
	Grupo: 7CM1
*/

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
import java.util.ArrayList;
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
	private String WORKER_ADDRESS_1 = "http://35.238.110.83:5000/";
	private String WORKER_ADDRESS_2 = "http://34.16.49.176:5001/";
	private String WORKER_ADDRESS_3 = "http://34.173.157.27:5002/";

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
        String contentType = exchange.getRequestHeaders().getFirst("Content-Type");
        System.out.println("Content-Type: " + contentType);
        FrontendSearchRequest frontendSearchRequest = objectMapper.readValue(exchange.getRequestBody().readAllBytes(), FrontendSearchRequest.class);
        int number = frontendSearchRequest.getSearchQuery();

        Aggregator aggregator = new Aggregator();

        // URLs for checking server status
        String statusURL_1 = WORKER_ADDRESS_1 + "status";
        String statusURL_2 = WORKER_ADDRESS_2 + "status";
        String statusURL_3 = WORKER_ADDRESS_3 + "status";

        // List to store response order
        List<String> responseOrder = new ArrayList<>();

        // Send status check tasks to workers
        List<String> estaActivo = aggregator.sendTasksToWorkers(Arrays.asList(statusURL_1, statusURL_2, statusURL_3));
        int conteoServidores = 0;

        // Count active servers and store their addresses
        List<String> activeServerAddresses = new ArrayList<>();
        for (int i = 0; i < estaActivo.size(); i++) {
            String result = estaActivo.get(i);
            System.out.println("Response from worker " + (i + 1) + ": " + result);
            responseOrder.add(result); // Track the order of responses
            if (result.equals("1")) {
                conteoServidores++;
                switch (i) {
                    case 0:
                        activeServerAddresses.add(WORKER_ADDRESS_1);
                        break;
                    case 1:
                        activeServerAddresses.add(WORKER_ADDRESS_2);
                        break;
                    case 2:
                        activeServerAddresses.add(WORKER_ADDRESS_3);
                        break;
                    default:
                        break;
                }
            }
        }

        // Determine how to divide the task based on the number of active servers
        List<String> finalURLs = new ArrayList<>();
        int start = 0;
        int end = 45;
        int tasksPerServer = end / conteoServidores;
        int remainingTasks = end % conteoServidores;

        for (int i = 0; i < conteoServidores; i++) {
            int tasksToSend = tasksPerServer + (i < remainingTasks ? 1 : 0);
            String finalURL = activeServerAddresses.get(i) + "compare?n=" + number + "&start=" + start + "&end=" + (start + tasksToSend - 1);
            finalURLs.add(finalURL);
            start += tasksToSend;
        }

        // Execute tasks on active servers
        long startTime = System.nanoTime();
        List<String> results = aggregator.sendTasksToWorkers(finalURLs);

        // Adjust the concatenation of results
        StringBuilder responseBuilder = new StringBuilder();
        for (String result : results) {
            String[] sentences = result.split("\\.");
            for (String sentence : sentences) {
                responseBuilder.append(sentence.trim()).append(".\n");
            }
        }
        String responseMessage = responseBuilder.toString();

        long finishTime = System.nanoTime();
        long elapsedTimeNano = finishTime - startTime;
        long elapsedSeconds = elapsedTimeNano / 1_000_000_000;
        long elapsedMilliseconds = (elapsedTimeNano % 1_000_000_000) / 1_000_000;
        String tiempo = String.format("Tiempo de procesamiento: %d segundos con %d milisegundos\n\n", elapsedSeconds, elapsedMilliseconds);

        // Append response order information to the final response
        StringBuilder orderInfo = new StringBuilder("Orden de respuesta de servidores:\n");
        for (int i = 0; i < responseOrder.size(); i++) {
            orderInfo.append("Servidor ").append(i + 1).append(": ").append(responseOrder.get(i)).append("\n");
        }

        String servidoresActivos = "Número de servidores activos: " + conteoServidores + "\n";
		String respuesta = results.get(0);
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
