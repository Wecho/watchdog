package com.wecho;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

public class ServletProcessor {
    public void process(Request request,Response response){
        String uri = request.getUri();
        String servletName = uri.substring(uri.lastIndexOf("/")+1);
        URLClassLoader urlClassLoader = null;

        try {
            URL[] urls = new URL[1];
            URLStreamHandler urlStreamHandler = null;
            File classPath = new File(Constants.WEB_ROOT);

            String repository = (new URL("file",null,classPath.getCanonicalPath())).toString();
            urls[0] = new URL(null,repository,urlStreamHandler);
            urlClassLoader = new URLClassLoader(urls);
        } catch (IOException e) {
            System.out.println(e);
        }
        Class servletClass = null;
        try {
            servletClass = urlClassLoader.loadClass(servletName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Servlet servlet = null;
        try {
            servlet = (Servlet) servletClass.newInstance();
            servlet.service((ServletRequest)request, (ServletResponse)response);
        } catch (InstantiationException | IllegalAccessException | ServletException | IOException e) {
            e.printStackTrace();
        }

    }
}
