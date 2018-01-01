package com.company.app;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;


public class HTTPServer {
    public int port;

    private HttpServer server;

    public HTTPServer(int port) {
        this.port = port;
    }

    private int settingUp() {
        try {
            this.server = HttpServer.create(new InetSocketAddress(port), 0);
            this.server.createContext("/", new RootHandler());
            this.server.setExecutor(null);
            return 0;
        } catch (IOException e) {
            e.printStackTrace();
            return 1;
        }
    }

    public int start() {
        if (this.settingUp() == 0) {
            this.server.start();
            return 0;
        }
        return 1;
    }
}

class RootHandler implements HttpHandler {
    public void handle(HttpExchange httpExchange) throws IOException {
        String response = "<h1> Server works just fine </h1>";
        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}