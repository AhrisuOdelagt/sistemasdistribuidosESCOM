import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.*;
// Importamos la biblioteca Gson
import com.google.gson.Gson;

public class HttpClientSynchronous {

    private static final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_1_1)
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    public static void main(String[] args) throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://api.breakingbadquotes.xyz/v1/quotes/3"))
                .setHeader("User-Agent", "Java 11 HttpClient Bot") // add request header
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        // print response headers
        HttpHeaders headers = response.headers();
        headers.map().forEach((k, v) -> System.out.println(k + ":" + v));

        // print status code
        System.out.println(response.statusCode());

        // print response body
        System.out.println(response.body());
		System.out.println();
		String json_respuesta = response.body();
		
		// JSON de prueba
		// String json_respuesta = "[{\"quote\":\"It’s like Scarface had sex with Mr. Rogers or something.\",\"author\":\"Hank Schrader\"},{\"quote\":\"You're the smartest guy I ever met, and you're too stupid to see he made up his mind 10 minutes ago.\",\"author\":\"Hank Schrader\"},{\"quote\":\"I told you, Skyler, I warned you for a solid year: you cross me, and there will be consequences.\",\"author\":\"Walter White\"}]";
		
		// Creamos una instancia de Gson
        Gson gson = new Gson();
		
		// Creamos la lista de citas
        Cita[] citas = gson.fromJson(json_respuesta, Cita[].class);
		
		// Imprimimos las citas
		int i = 1;
        for (Cita cita : citas) {
			System.out.println("JSON " + i + ": ");
			System.out.println("Cita: " + cita.getQuote());
            System.out.println("Autor: " + cita.getAuthor());
            System.out.println();
			i++;
        }
    }
}

// Clase de ejemplo
class Cita {
	// Atributos
	private String quote;
	private String author;
	
	// Constructor, getters y setters
	public Cita(String quote, String author) {
		this.quote = quote;
		this.author = author;
	}
	
	public String getQuote() {
		return quote;
	}
	public void setQuote(String quote) {
		this.quote = quote;
	}
	
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
}
