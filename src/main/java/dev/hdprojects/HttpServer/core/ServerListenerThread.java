package dev.hdprojects.HttpServer.core;

import dev.hdprojects.HttpServer.config.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerListenerThread extends Thread {

    private final static Logger LOGGER = LoggerFactory.getLogger(ServerListenerThread.class);

    private int port;
    private String webroot;
    private ServerSocket serverSocket;
    private Configuration configuration;

    public ServerListenerThread(Configuration configuration) throws IOException {
        this.port = configuration.getPort();
        this.webroot = configuration.getWebroot();
        this.configuration = configuration;

        this.serverSocket = new ServerSocket(this.port);
    }

    @Override
    public void run() {
        try {

            while (serverSocket.isBound() && !serverSocket.isClosed()) {
                Socket socket = this.serverSocket.accept();

                LOGGER.info("Connection accepted" + socket.getInetAddress());

                HttpConnectionWorkerThread workerThread = new HttpConnectionWorkerThread(socket, webroot, configuration);
                workerThread.start();

            }
            // TODO Handle Close

        } catch (IOException e) {
            LOGGER.error("Problem with setting socket", e);
        } finally {
            if(serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {}
            }
        }
    }
}
