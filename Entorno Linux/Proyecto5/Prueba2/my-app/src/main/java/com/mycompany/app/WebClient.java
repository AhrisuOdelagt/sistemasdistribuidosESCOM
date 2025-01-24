/*    --- Información del proyecto ----
	No. de proyecto: Final
	Nombre completo: Franco Olvera Demian Oder
	Grupo: 7CM1
*/

package com.mycompany.app;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.CompletableFuture;

public class WebClient {

    public CompletableFuture<String> sendTask(String address) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                URL url = new URL(address);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setConnectTimeout(5000);  // Tiempo de espera para la conexión (5 segundos)
                connection.setReadTimeout(5000);     // Tiempo de espera para la lectura (5 segundos)

                int responseCode = connection.getResponseCode();
                if (responseCode == 200) {  // OK
                    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();
                    return response.toString();
                } else {
                    return "Error: " + responseCode;
                }
            } catch (Exception e) {
                return "Error: " + e.getMessage();
            }
        });
    }
}
