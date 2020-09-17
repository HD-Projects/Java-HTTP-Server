package dev.hdprojects.HttpServer.core;

import dev.hdprojects.http.GenerateHttpHeader;
import dev.hdprojects.http.HttpParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class HttpConnectionWorkerThread extends Thread{

    private final static Logger LOGGER = LoggerFactory.getLogger(HttpConnectionWorkerThread.class);

    private Socket socket;

    public HttpConnectionWorkerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();

            HttpParser httpParser = new HttpParser(inputStream);

            httpParser.parseHttpRequest();

            String html = "<html><head> <title>Simple Java HTTP Server</title></head><body><h1>Build this server with java HTTP</h1></body></html>";

            final String CRLF = "\r\n"; // 13, 10 ASCII

            GenerateHttpHeader httpHeader = new GenerateHttpHeader(200, html.length(), "text/html", "hd");

            String response = httpHeader + html + CRLF + CRLF;

            outputStream.write(response.getBytes());

            LOGGER.info("Connection processing finished");
        } catch (IOException e) {
            LOGGER.error("Problem with communication", e);
        } finally {
            if(inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {}
            }

            if(outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {}
            }

            if(socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {}
            }
        }
    }
}
