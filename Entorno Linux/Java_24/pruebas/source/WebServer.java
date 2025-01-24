// Librerías para construir un servidor HTTP en Java
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.Executors;

public class WebServer {
	private int solicitudes = 0;
	private static final String SERVICIOS_ENDPOINT = "/servicios"; //Endpoint Send
	private String WORKER_ADDRESS_1 = "http://localhost:8000/primo";
    private String WORKER_ADDRESS_2 = "http://localhost:8080/sum";

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
        HttpContext serviciosContext = server.createContext(SERVICIOS_ENDPOINT);
		
		// Implementamos el manejador para los contextos sin inicializar
        serviciosContext.setHandler(this::handleServiciosCheckRequest);
		
		// Establece un objeto de tipo Executor al server, necesario para su ejecución
        server.setExecutor(Executors.newFixedThreadPool(8));    // Pool de 8 hilos iniciado por el ejecutor
        server.start();    // Se inicia la ejecución del server en un hilo dejado en segundo plano
    }
	
	// Método para gestionar el Endpoint Status (maneja todo lo relacionado con la transacción HTTP Cliente-Servidor)
    private void handleServiciosCheckRequest(HttpExchange exchange) throws IOException {
		
        // Verificamos que la solicitud pertenezca al método GET
		if (!exchange.getRequestMethod().equalsIgnoreCase("get")) {
            // Si no es GET, se cierra el método
			exchange.close();
            return;
        }
        String num="";
        String servicio="";

        //http://127.0.0.1:8080/servicios?numero=5623273&servicio=2
        Aggregator aggregator = new Aggregator();

        // Obtenemos los parámetros de la URL
        String query = exchange.getRequestURI().getQuery();
        String[] params = query.split("&");
        
        for(String param: params){
        	String[] p = param.split("=");
        	if(p[0].equals("numero")){
        		num=p[1];
        	}else if(p[0].equals("servicio")){
        		servicio=p[1];
        	}
        }

        String responseMessage = "";

        //Hacemos la opcion coarrespondiente segun el tipo de servicio recibido
        if(servicio.equals("1")){
        	//ServerProc1 //http://127.0.0.1:8000/primo?numero=5623273
        	String workeraddress1 = WORKER_ADDRESS_1+"?numero="+num;
        	// Se envían las tareas a los trabajadores
        	List<String> results = aggregator.sendTasksToWorkers(Arrays.asList(workeraddress1));
        	// Recibe los resultados e imprime los resultados
        	for (String result : results) {
            	responseMessage=result;
        	}
        }else if(servicio.equals("2")){
        	//ServerProc2 //http://127.0.0.1:8080/sum?numero=5623273
            String workeraddress2 = WORKER_ADDRESS_2+"?numero="+num;
            // Se envían las tareas a los trabajadores
            List<String> results = aggregator.sendTasksToWorkers(Arrays.asList(workeraddress2));
            // Recibe los resultados e imprime los resultados
            for (String result : results) {
                responseMessage=result;
            }
        }else if(servicio.equals("3")){
        	//ServerProc1 //http://127.0.0.1:8000/primo?numero=5623273
            String workeraddress1 = WORKER_ADDRESS_1+"?numero="+num;
        	//ServerProc2 //http://127.0.0.1:8080/sum?numero=5623273
            String workeraddress2 = WORKER_ADDRESS_2+"?numero="+num;
            // Se envían las tareas a los trabajadores
            List<String> results = aggregator.sendTasksToWorkers(Arrays.asList(workeraddress1,workeraddress2));
            // Recibe los resultados e imprime los resultados
            for (String result : results) {
                responseMessage=responseMessage+result+"\n";
            }
        }else{
        	responseMessage="El servicio ingresado es incorrecto";
        }
		
		
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
