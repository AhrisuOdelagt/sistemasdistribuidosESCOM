import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.net.http.HttpRequest.BodyPublishers;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpClientSynchronous {

    private static int MAX_T = 10;
	private static int MAX_S = 0;

    public static void main(String[] args) {
		
		// Tomamos el valor desde los argumentos
		MAX_S = Integer.parseInt(args[0]);
		
		Runnable r1 = new Task("Cliente 1", "http://localhost:4444/searchtoken");
		Runnable r2 = new Task("Cliente 2", "http://localhost:4444/searchtoken");
		Runnable r3 = new Task("Cliente 3", "http://localhost:4444/searchtoken");
		Runnable r4 = new Task("Cliente 4", "http://localhost:4444/searchtoken");
		Runnable r5 = new Task("Cliente 5", "http://localhost:4444/searchtoken");
		Runnable r6 = new Task("Cliente 6", "http://localhost:4444/searchtoken");
		Runnable r7 = new Task("Cliente 7", "http://localhost:4444/searchtoken");
		Runnable r8 = new Task("Cliente 8", "http://localhost:4444/searchtoken");
		Runnable r9 = new Task("Cliente 9", "http://localhost:4444/searchtoken");
		Runnable r10 = new Task("Cliente 10", "http://localhost:4444/searchtoken");
		
		ExecutorService pool = Executors.newFixedThreadPool(MAX_T);
		
		for(int i = 0; i < MAX_S; i++) {
			pool.execute(r1);
			pool.execute(r2);
			pool.execute(r3);
			pool.execute(r4);
			pool.execute(r5);
			pool.execute(r6);
			pool.execute(r7);
			pool.execute(r8);
			pool.execute(r9);
			pool.execute(r10);
		}
		
		pool.shutdown();
	}
	
	// Clase Task
	static class Task implements Runnable {
		private String name;
		private String uri;
		private static final HttpClient httpClient = HttpClient.newBuilder()
				.version(HttpClient.Version.HTTP_1_1)
				.connectTimeout(Duration.ofSeconds(10))
				.build();

		public Task(String nombre, String uriServer) {
			name = nombre;
			this.uri = uriServer;
		}
		
		public void run() {
			System.out.println("Inicia: " + name);
			HttpRequest request = HttpRequest.newBuilder()
				.POST(BodyPublishers.ofString("175760000,IPN"))
				.uri(URI.create(uri))
				.setHeader("X-Debug", "true") // add request header
				.build();
					
			try {
				HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

				// print response headers
				HttpHeaders headers = response.headers();
				headers.map().forEach((k, v) -> System.out.println(k + ":" + v));

				// print status code
				System.out.println(response.statusCode());

				// print response body
				System.out.println(response.body());
			}
			catch(IOException | InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
