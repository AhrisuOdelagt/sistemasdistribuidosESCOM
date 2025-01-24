/*    --- Información del proyecto ----
	No. de proyecto: Final
	Nombre completo: Franco Olvera Demian Oder
	Grupo: 7CM1
*/

package com.mycompany.app;

import java.io.IOException;

public class App {

    public static void main(String[] args) throws IOException, InterruptedException {
        int currentServerPort = 4444;
        if (args.length == 1) {
            currentServerPort = Integer.parseInt(args[0]);
        }
        App application = new App();

        WebServer webServer = new WebServer(currentServerPort);
        webServer.startServer();

        System.out.println("Servidor escuchando en el puerto: " + currentServerPort);
    }
}