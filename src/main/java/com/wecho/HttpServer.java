package com.wecho;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {

    private static final String SHUTDOWN_COMMAND = "/SHUTDOWN";

    private boolean shutdown = false;

    public void await() {
        int port = 8080;
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port, 1, Inet4Address.getByName("127.0.0.1"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (!shutdown) {
            assert serverSocket != null;
            try (Socket socket = serverSocket.accept();
                 InputStream inputStream = socket.getInputStream();
                 OutputStream outputStream = socket.getOutputStream()) {

                // process request
                Request request = new Request(inputStream);
                request.parseUri();
                
                // process response

            } catch (IOException e) {
                e.printStackTrace();
            }


        }

    }

    public static void main(String[] args) {
        HttpServer httpServer = new HttpServer();
        httpServer.await();
    }
}
