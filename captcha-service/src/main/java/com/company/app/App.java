package com.company.app;


public class App {
    public static int Port = 8080;

    public static void main( String[] args ) {
        HTTPServer server = new HTTPServer(Port);
        server.start();
    }
}
