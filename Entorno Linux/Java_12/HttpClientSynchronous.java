
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

public class HttpClientSynchronous {

    private static final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_1_1)
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    public static void main(String[] args) throws IOException, InterruptedException {
		
		List<String> uris = Arrays.asList("http://192.168.43.71:4444/searchtoken", "http://192.168.43.37:4444/searchtoken", "http://192.168.43.78:4444/searchtoken");

        for(String uri : uris) {
			HttpRequest request = HttpRequest.newBuilder()
                .POST(BodyPublishers.ofFile(Paths.get("info.txt")))
                .uri(URI.create(uri))
                .setHeader("X-Debug", "true") // add request header
                .build();

			HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

			// print response headers
			HttpHeaders headers = response.headers();
			headers.map().forEach((k, v) -> System.out.println(k + ":" + v));

			// print status code
			System.out.println(response.statusCode());

			// print response body
			System.out.println(response.body());
		}

    }

}
