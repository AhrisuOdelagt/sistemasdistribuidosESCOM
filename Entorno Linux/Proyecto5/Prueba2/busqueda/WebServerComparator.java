/*    --- Información del proyecto ----
	No. de proyecto: Final
	Nombre completo: Franco Olvera Demian Oder
	Grupo: 7CM1
*/

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

    private static final int DEFAULT_PORT = 5000;  // Puerto por defecto
    private static final String STATUS_ENDPOINT = "/status";
    private static final String COMPARE_ENDPOINT = "/compare";
    private static final String LIBROS = "LIBROS_TXT/";

    private final HttpServer server;
    private int solicitudes = 0;
    private Map<String, Set<String>> phraseBookMap = new HashMap<>(); // Almacena frases y archivos asociados

    public WebServerComparator(int port) throws IOException {
        this.server = HttpServer.create(new InetSocketAddress(port), 0);
        server.setExecutor(Executors.newFixedThreadPool(8));

        // Registrar manejadores para los endpoints
        HttpContext statusContext = server.createContext(STATUS_ENDPOINT);
        statusContext.setHandler(this::handleStatusCheckRequest);
        HttpContext compareContext = server.createContext(COMPARE_ENDPOINT);
        compareContext.setHandler(this::handleComparatorRequest);
    }

    public void startServer() {
        server.start();
        System.out.println("Servidor escuchando en el puerto " + server.getAddress().getPort());
    }

    private void handleStatusCheckRequest(HttpExchange exchange) throws IOException {
        if (!exchange.getRequestMethod().equalsIgnoreCase("GET")) {
            exchange.close();
            return;
        }

        String responseMessage = "1";
        sendResponse(responseMessage.getBytes(), exchange);
        solicitudes++;
        System.out.println("Se han realizado " + solicitudes + " solicitudes hasta el momento.");
    }

    private void handleComparatorRequest(HttpExchange exchange) throws IOException {
        if (!exchange.getRequestMethod().equalsIgnoreCase("GET")) {
            exchange.close();
            return;
        }

        // Extraer parámetros de la cadena de consulta
        URI uri = exchange.getRequestURI();
        String query = uri.getQuery();
        String[] parameters = query.split("&");

        try {
            int n = Integer.parseInt(parameters[0].split("=")[1]);
            int startFileIndex = Math.max(0, Integer.parseInt(parameters[1].split("=")[1]));
            int endFileIndex = Math.min(getEndFileIndex(startFileIndex), getNumberOfFiles() - 1);

            // Procesar archivos específicos para esta solicitud
            processFiles(n, startFileIndex, endFileIndex);

            // Buscar frases comunes dentro del rango especificado
			System.out.println();
            String response = findCommonPhrases(n, startFileIndex, endFileIndex);
            sendResponse(response.getBytes(), exchange);
        } catch (NumberFormatException e) {
            sendResponse("Error: Parámetros inválidos.".getBytes(), exchange);
        }

        solicitudes++;
        System.out.println("Se han realizado " + solicitudes + " solicitudes hasta el momento.");
    }

    private void processFiles(int n, int startFileIndex, int endFileIndex) throws IOException {
        // Limpiar frases existentes antes de procesar nuevos archivos
        phraseBookMap.clear();

        File directory = new File(LIBROS);
        File[] files = directory.listFiles((dir, name) -> name.endsWith(".txt"));

        if (files == null) {
            return;
        }

        // Ajustar para el rango de archivos específico
        startFileIndex = Math.max(0, startFileIndex); // Asegurar que startFileIndex no sea negativo
        endFileIndex = Math.min(endFileIndex, files.length - 1); // Asegurar que endFileIndex no supere el número de archivos

        for (int i = startFileIndex; i <= endFileIndex; i++) {
            File file = files[i];
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            int lineCount = 0;
            while ((line = reader.readLine()) != null && lineCount < 100) { // Limitar a las primeras 100 líneas
                lineCount++;
                line = normalizeString(line).toLowerCase();
                String[] words = line.split("\\s+");
                for (int j = 0; j <= words.length - n; j++) {
                    StringBuilder phrase = new StringBuilder();
                    for (int k = 0; k < n; k++) {
                        phrase.append(words[j + k]).append(" ");
                    }
                    String phraseStr = phrase.toString().trim();
                    phraseBookMap.putIfAbsent(phraseStr, new HashSet<>());
                    phraseBookMap.get(phraseStr).add(file.getName().replace(".txt", ""));
                }
            }
            reader.close();
        }
    }

    private String findCommonPhrases(int n, int startFileIndex, int endFileIndex) throws IOException {
        TreeSet<String> sortedPhrases = new TreeSet<>();

        File directory = new File(LIBROS);
        File[] files = directory.listFiles((dir, name) -> name.endsWith(".txt"));

        if (files == null) {
            return "No se encontraron libros en el directorio.";
        }

        // Ajuste para el rango de archivos
        startFileIndex = Math.max(0, startFileIndex); // Asegurar que startFileIndex no sea negativo
        endFileIndex = Math.min(endFileIndex, files.length - 1); // Asegurar que endFileIndex no supere el número de archivos

        for (int i = startFileIndex; i <= endFileIndex; i++) {
            File file = files[i];
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            int lineCount = 0;
            while ((line = reader.readLine()) != null && lineCount < 100) { // Limitar a las primeras 100 líneas
                lineCount++;
                line = normalizeString(line).toLowerCase();
                String[] words = line.split("\\s+");
                for (int j = 0; j <= words.length - n; j++) {
                    StringBuilder phrase = new StringBuilder();
                    for (int k = 0; k < n; k++) {
                        phrase.append(words[j + k]).append(" ");
                    }
                    String phraseStr = phrase.toString().trim();
                    if (phraseBookMap.containsKey(phraseStr)) {
                        Set<String> fileList = phraseBookMap.get(phraseStr);
                        if (fileList.size() > 1) {
                            List<String> sortedFiles = new ArrayList<>(fileList);
                            Collections.sort(sortedFiles);
                            for (int fileIndex = 0; fileIndex < sortedFiles.size(); fileIndex++) {
                                for (int nextFileIndex = fileIndex + 1; nextFileIndex < sortedFiles.size(); nextFileIndex++) {
                                    sortedPhrases.add("\"" + phraseStr + "\" aparece en " + sortedFiles.get(fileIndex) + " y " + sortedFiles.get(nextFileIndex) + ".");
                                }
                            }
                        }
                    }
                }
            }
            reader.close();
        }

        StringBuilder responseBuilder = new StringBuilder();
        for (String phrase : sortedPhrases) {
            responseBuilder.append(phrase).append("\n");
        }

        return responseBuilder.toString();
    }

    private String normalizeString(String input) {
        // Eliminar acentos
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        normalized = normalized.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");

        // Eliminar signos de puntuación y caracteres especiales incluyendo el guion largo (—)
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

    private int getNumberOfFiles() {
        File directory = new File(LIBROS);
        File[] files = directory.listFiles((dir, name) -> name.endsWith(".txt"));
        return files != null ? files.length : 0;
    }

    private int getEndFileIndex(int startFileIndex) {
        return getNumberOfFiles() - 1;
    }

    public static void main(String[] args) {
        int port = DEFAULT_PORT;

        if (args.length > 0) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.err.println("Puerto inválido, usando el puerto por defecto " + DEFAULT_PORT);
            }
        }

        try {
            WebServerComparator webServer = new WebServerComparator(port);
            webServer.startServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
