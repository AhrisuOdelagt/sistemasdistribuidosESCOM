import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.text.Normalizer;
import java.util.*;
import java.util.concurrent.Executors;

public class WebServerComparator {

    private int solicitudes = 0;
    private static final String STATUS_ENDPOINT = "/status";    
    private static final String COMPARATOR_ENDPOINT = "/compare";    

    private final int port;    
    private HttpServer server;    

    private static final String LIBROS = "LIBROS_TXT/";

    public static void main(String[] args) {
        int serverPort = 5000;    
        if (args.length == 1) {
            serverPort = Integer.parseInt(args[0]);    
        }

        WebServerComparator webServer = new WebServerComparator(serverPort);    
        webServer.startServer();    

        System.out.println("Servidor escuchando en el puerto " + serverPort);
    }

    public WebServerComparator(int port) {
        this.port = port;
    }

    public void startServer() {
        try {
            this.server = HttpServer.create(new InetSocketAddress(port), 0);    
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        HttpContext statusContext = server.createContext(STATUS_ENDPOINT);
        HttpContext comparatorContext = server.createContext(COMPARATOR_ENDPOINT);

        statusContext.setHandler(this::handleStatusCheckRequest);
        comparatorContext.setHandler(this::handleComparatorRequest);

        server.setExecutor(Executors.newFixedThreadPool(8));    
        server.start();    
    }

    private void handleStatusCheckRequest(HttpExchange exchange) throws IOException {
        if (!exchange.getRequestMethod().equalsIgnoreCase("get")) {
            exchange.close();
            return;
        }

        String responseMessage = "1";
        sendResponse(responseMessage.getBytes(), exchange);

        solicitudes++;
        System.out.println("Se han realizado " + solicitudes + " solicitudes hasta el momento.");
    }

    private void handleComparatorRequest(HttpExchange exchange) throws IOException {
        if (!exchange.getRequestMethod().equalsIgnoreCase("get")) {
            exchange.close();
            return;
        }

        URI uri = exchange.getRequestURI();
        String query = uri.getQuery();
        String[] parametros = query.split("&");
        String numeroStr = parametros[0].split("=")[1];
        int n = Integer.parseInt(numeroStr);

        String response = findCommonPhrases(n);
        sendResponse(response.getBytes(), exchange);

        solicitudes++;
        System.out.println("Se han realizado " + solicitudes + " solicitudes hasta el momento.");
    }

    private String findCommonPhrases(int n) throws IOException {
        Map<String, Set<String>> phraseBookMap = new HashMap<>();
        File directory = new File(LIBROS);
        File[] files = directory.listFiles((dir, name) -> name.endsWith(".txt"));

        if (files == null) {
            return "No se encontraron libros en el directorio.";
        }

        for (File file : files) {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            int lineCount = 0;
            while ((line = reader.readLine()) != null && lineCount < 100) {
                // Remove accents and convert to lowercase
                line = normalizeString(line).toLowerCase();

                String[] words = line.split("\\s+");
                for (int i = 0; i <= words.length - n; i++) {
                    StringBuilder phrase = new StringBuilder();
                    for (int j = 0; j < n; j++) {
                        phrase.append(words[i + j]).append(" ");
                    }
                    String phraseStr = phrase.toString().trim();
                    phraseBookMap.putIfAbsent(phraseStr, new HashSet<>());
                    phraseBookMap.get(phraseStr).add("\"" + file.getName().replace(".txt", "") + "\"");
                }
                lineCount++;
            }
            reader.close();
        }

        TreeSet<String> sortedPhrases = new TreeSet<>();
        for (Map.Entry<String, Set<String>> entry : phraseBookMap.entrySet()) {
            List<String> fileList = new ArrayList<>(entry.getValue());
            if (fileList.size() > 1) {
                for (int i = 0; i < fileList.size(); i++) {
                    for (int j = i + 1; j < fileList.size(); j++) {
                        sortedPhrases.add("“" + entry.getKey() + "” aparece en " + fileList.get(i) + " y " + fileList.get(j));
                    }
                }
            }
        }

        StringBuilder responseBuilder = new StringBuilder();
        responseBuilder.append("Total de enunciados idénticos: ").append(sortedPhrases.size()).append("\n\n");

        for (String phrase : sortedPhrases) {
            responseBuilder.append(phrase).append("\n");
        }

        return responseBuilder.toString();
    }

    private String normalizeString(String input) {
        // Remove accents
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        normalized = normalized.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");

        // Remove punctuation and special characters including the long dash (—)
        normalized = normalized.replaceAll("[\\p{Punct}—]", "");

        return normalized;
    }

    private void sendResponse(byte[] responseBytes, HttpExchange exchange) throws IOException {
        exchange.sendResponseHeaders(200, responseBytes.length);
        OutputStream outputStream = exchange.getResponseBody();
        outputStream.write(responseBytes);
        outputStream.flush();
        outputStream.close();
        exchange.close();
    }
}
