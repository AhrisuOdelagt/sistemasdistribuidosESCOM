import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.concurrent.Executors;

public class ServerProc1{
    private int solicitudes=0;
    private final int port;    // Puerto
    private HttpServer server;    // Tipo de HTTP Server
    private static final String PRIMO_ENDPOINT = "/primo"; //Endpoint Send

    public static void main(String[] args) {
        int serverPort = 8000;    // Se define el puerto por defecto del servidor
        if (args.length == 1) {
            serverPort = Integer.parseInt(args[0]);    // Se asigna un puerto por línea de comandos, de hacerse
        }

        ServerProc1 procServer1 = new ServerProc1(serverPort);    // Se instancia un objeto WebServer
        procServer1.startServer();    // Se ejecuta el método principal del servidor para empezar su configuración

        System.out.println("Servidor escuchando en el puerto " + serverPort);
    }

    public ServerProc1(int port) {
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
        HttpContext primoContext = server.createContext(PRIMO_ENDPOINT);

        // Implementamos el manejador para los contextos sin inicializar
        primoContext.setHandler(this::handlePrimoCheckRequest);

        // Establece un objeto de tipo Executor al server, necesario para su ejecución
        server.setExecutor(Executors.newFixedThreadPool(8));    // Pool de 8 hilos iniciado por el ejecutor
        server.start();    // Se inicia la ejecución del server en un hilo dejado en segundo plano
    }

    // Método para gestionar el Endpoint Primo 
    private void handlePrimoCheckRequest(HttpExchange exchange) throws IOException {
        // Verificamos que la solicitud pertenezca al método GET
        if (!exchange.getRequestMethod().equalsIgnoreCase("get")) {
            // Si no es GET, se cierra el método
            exchange.close();
            return;
        }
        
        // Obtenemos los parámetros de la URL
        String query = exchange.getRequestURI().getQuery();
        String[] params = query.split("=");
        int num = Integer.parseInt(params[1]);
        
        //Verificamos si el numero es primo
        String responseMessage=esPrimo(num);

        // Enviar la respuesta
        sendResponse(responseMessage.getBytes(), exchange);

        // Imprimimos la cantidad de solicitudes realizadas hasta el momento
        solicitudes++;
        System.out.println("Se han realizado " + solicitudes + " solicitudes hasta el momento.");
    }

    public static String esPrimo(int num) {
    	int div=0;

        for (int i = 2; i<=num; i++) {
            if (num % i == 0) {
            	div++;
            }
        }
        if(div>1){
            return "El numero no es primo";
        }
        else{
        	return "El numero es primo";
        }
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
