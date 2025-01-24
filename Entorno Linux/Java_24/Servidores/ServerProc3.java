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

public class ServerProc3{
    private int solicitudes=0;
    private final int port;    // Puerto
    private HttpServer server;    // Tipo de HTTP Server
    private static final String FACT_ENDPOINT = "/fact"; //Endpoint Send

    public static void main(String[] args) {
        int serverPort = 8083;    // Se define el puerto por defecto del servidor
        if (args.length == 1) {
            serverPort = Integer.parseInt(args[0]);    // Se asigna un puerto por línea de comandos, de hacerse
        }

        ServerProc3 procServer3 = new ServerProc3(serverPort);    // Se instancia un objeto WebServer
        procServer3.startServer();    // Se ejecuta el método principal del servidor para empezar su configuración

        System.out.println("Servidor escuchando en el puerto " + serverPort);
    }

    public ServerProc3(int port) {
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
        HttpContext factContext = server.createContext(FACT_ENDPOINT);

        // Implementamos el manejador para los contextos sin inicializar
        factContext.setHandler(this::handleFactCheckRequest);

        // Establece un objeto de tipo Executor al server, necesario para su ejecución
        server.setExecutor(Executors.newFixedThreadPool(8));    // Pool de 8 hilos iniciado por el ejecutor
        server.start();    // Se inicia la ejecución del server en un hilo dejado en segundo plano
    }

    // Método para gestionar el Endpoint Factorial con el intervalo recibido
    private void handleFactCheckRequest(HttpExchange exchange) throws IOException {
        // Verificamos que la solicitud pertenezca al método GET
        if (!exchange.getRequestMethod().equalsIgnoreCase("get")) {
            // Si no es GET, se cierra el método
            exchange.close();
            return;
        }
        
        // Obtenemos los parámetros de la URL
        String query = exchange.getRequestURI().getQuery();
        String[] params = query.split("&");

        int num1=0;
        int num2=0;
        
        for(String param: params){
            String[] p = param.split("=");
            if(p[0].equals("num1")){
                num1=Integer.parseInt(p[1]);
            }else if(p[0].equals("num2")){
                num2=Integer.parseInt(p[1]);;
            }
        }

        System.out.println("El rango recibido es: "+num1+" a "+num2);

        String responseMessage=factorial(num1, num2);

        // Enviar la respuesta
        sendResponse(responseMessage.getBytes(), exchange);

        // Imprimimos la cantidad de solicitudes realizadas hasta el momento
        solicitudes++;
        System.out.println("Se han realizado " + solicitudes + " solicitudes hasta el momento.");
    }

    public static String factorial(int num1, int num2) {
        BigInteger mult = BigInteger.valueOf(num2);
        String msg="El resultado de la multiplicación es: ";

        for(int i=num2-1; i>=num1;i--){
            mult=mult.multiply(BigInteger.valueOf(i));
        }

        msg = msg + mult.toString();
        return msg;
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
