package networking;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class WebClient {
	// Tiene un único dato del tipo HttpClient para instanciar clientes
    private HttpClient client;
	
	// Crea una instancia del cliente HTTP
    public WebClient() {
        this.client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build();
    }
	
	//Éste método recibe la dirección con la que va a raealizar la conexión y los bytes de los datos a enviar
    public CompletableFuture<String> sendTask(String url) {
        // Crea un objeto del tipo HttpRequest para enviar los datos a la dirección marcada
		HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
				.header("X-debug","true")
                .build();
		// Devuelve un Completable Future como una String, llamando a sendAsync para el envío asíncrono de la solicitud
        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(respuesta -> { return
				respuesta.body().toString(); });
    }
}
