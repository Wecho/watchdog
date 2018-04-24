package com.wecho.watchdog.startup;

import com.wecho.watchdog.connector.HttpConnector;

public class BootStrap {
    public static void main(String[] args) {
        HttpConnector httpConnector = new HttpConnector();
        httpConnector.start();
    }
}
