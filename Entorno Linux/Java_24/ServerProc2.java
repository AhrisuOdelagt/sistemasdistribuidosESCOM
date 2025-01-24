import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.concurrent.Executors;
import java.math.BigInteger;

public class ServerProc2{
    private int solicitudes=0;
    private final int port;    // Puerto
    private HttpServer server;    // Tipo de HTTP Server
    private static final String SUM_ENDPOINT = "/sum"; //Endpoint Send

    public static void main(String[] args) {
        int serverPort = 8080;    // Se define el puerto por defecto del servidor
        if (args.length == 1) {
            serverPort = Integer.parseInt(args[0]);    // Se asigna un puerto por línea de comandos, de hacerse
        }

        ServerProc2 procServer2 = new ServerProc2(serverPort);    // Se instancia un objeto WebServer
        procServer2.startServer();    // Se ejecuta el método principal del servidor para empezar su configuración

        System.out.println("Servidor escuchando en el puerto " + serverPort);
    }

    public ServerProc2(int port) {
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
        HttpContext sumContext = server.createContext(SUM_ENDPOINT);

        // Implementamos el manejador para los contextos sin inicializar
        sumContext.setHandler(this::handleSumCheckRequest);

        // Establece un objeto de tipo Executor al server, necesario para su ejecución
        server.setExecutor(Executors.newFixedThreadPool(8));    // Pool de 8 hilos iniciado por el ejecutor
        server.start();    // Se inicia la ejecución del server en un hilo dejado en segundo plano
    }

    // Método para gestionar el Endpoint Suma
    private void handleSumCheckRequest(HttpExchange exchange) throws IOException {
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
        
        //Obtenemos la suma de los numeros impares anteriores a ese numero
        String responseMessage=sumImp(num);

        // Enviar la respuesta
        sendResponse(responseMessage.getBytes(), exchange);

        // Imprimimos la cantidad de solicitudes realizadas hasta el momento
        solicitudes++;
        System.out.println("Se han realizado " + solicitudes + " solicitudes hasta el momento.");
    }

    public static String sumImp(int num) {
        BigInteger sum = BigInteger.ZERO;
    	String sumImp="La suma de los numeros impares menores a "+num+" es: ";
        for (int i = 0; i<num; i++) {
            if (i % 2 != 0) {
            	sum = sum.add(BigInteger.valueOf(i));
            }
        }
        sumImp = sumImp + sum.toString();
        return sumImp;
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
