package com.wecho.watchdog.connector;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpConnector implements Runnable{

    private boolean shutdown;

    private int port = 8080;

    @Override
    public void run() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port,1,InetAddress.getByName("127.0.0.1"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        while(!shutdown){
            Socket socket = null;
            try {
                socket = serverSocket.accept();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // HttpProcessor do process

        }
    }

    public void start(){
        Thread thread = new Thread(this);
        thread.start();
    }

}
