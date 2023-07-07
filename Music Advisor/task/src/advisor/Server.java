package advisor;

import com.sun.net.httpserver.*;

import java.io.IOException;
import java.net.*;
import java.net.http.*;

public class Server {

    private static String AUTH_CODE = "";
    private static String response = "";
    private static String code;

    void serverCreate() {
        System.out.println("use this link to request the access code:\n" + Configuration.URL);
        try {
            HttpServer server = HttpServer.create();
            server.bind(new InetSocketAddress(8080), 0);
            server.start();
            server.createContext("/",
                    exchange -> {
                        code = exchange.getRequestURI().getQuery();
                        if (code.startsWith("code")) {
                            AUTH_CODE = code.substring(5);
                            System.out.println("code received");
                            response = "Got the code. Return back to your program.";
                        } else {
                            response = "Authorization code not found. Try again.";
                        }
                        exchange.sendResponseHeaders(200, response.length());
                        exchange.getResponseBody().write(response.getBytes());
                        exchange.getResponseBody().close();
                    });
            System.out.println("\nwaiting for code...");
            if (AUTH_CODE.isBlank()) {
                Thread.sleep(10);
            }
            server.stop(10);
        } catch (IOException | InterruptedException e) {
            System.out.println("Server error");
        }
    }

    void accessToken() {
        HttpClient client = HttpClient.newBuilder().build();
        var request = HttpRequest.newBuilder()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .uri(URI.create(Configuration.SERVER_PATH + "/api/token"))
                .POST(HttpRequest.BodyPublishers.ofString("grant_type=" +
                        "authorization_code"  + "&client_id=" + Configuration.CLIENT_ID
                        + "&client_secret=" + Configuration.CLIENT_SECRET
                        + "&redirect_uri=" + Configuration.REDIRECT_URI))
                .build();
        try {
            var response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
            System.out.println("---SUCCESS---");
        } catch (Exception e) {
            System.out.println("We cannot access the site. Please, try later.");
        }
    }
}

