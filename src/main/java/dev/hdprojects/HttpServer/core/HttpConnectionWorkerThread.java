package dev.hdprojects.HttpServer.core;

import dev.hdprojects.HttpServer.config.Configuration;
import dev.hdprojects.http.GenerateHttpHeader;
import dev.hdprojects.http.HttpParser;
import dev.hdprojects.website.HtmlParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class HttpConnectionWorkerThread extends Thread {

    private final static Logger LOGGER = LoggerFactory.getLogger(HttpConnectionWorkerThread.class);

    private String webRoot;
    private Socket socket;
    private Configuration conf;

    public HttpConnectionWorkerThread(Socket socket, String webRoot, Configuration conf) {
        this.webRoot = webRoot;
        this.socket = socket;

        this.conf = conf;
    }

    @Override
    public void run() {

        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {

            // Create the streams to write to
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();

            // Create an instance of the HttpParser
            LOGGER.info("Starting HTTP Parser ... ");
            HttpParser httpParser = new HttpParser(inputStream);
            httpParser.parseHttpRequest();
            HtmlParser htmlParser = new HtmlParser(webRoot + httpParser.getRequestedPage(), conf);
            LOGGER.info("Done With HTTP Parser.");

            // Set HTML variable
            String html = htmlParser.toString();

            /* 13, 10 ASCII */
            final String CRLF = "\r\n";

            // Get the length of the html
            int contentLength = html.getBytes().length;

            // Generate an HTTP Header and response
            LOGGER.info("Generating HTTP Header ... ");
            LOGGER.info("error: " + htmlParser.getError());
            GenerateHttpHeader httpHeader = new GenerateHttpHeader(htmlParser.getError(), contentLength, "text/html", "hd");
            String response = httpHeader.toString() + html + CRLF + CRLF;
            LOGGER.info("Done Generating HTTP Header.");

            // Send the response
            LOGGER.info("Sending response ... ");
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
