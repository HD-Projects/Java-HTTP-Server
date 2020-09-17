package dev.hdprojects.HttpServer;

import dev.hdprojects.HttpServer.config.Configuration;
import dev.hdprojects.HttpServer.config.ConfigurationManager;
import dev.hdprojects.HttpServer.core.ServerListenerThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Driver class for the HTTP Server
 */
public class HttpServer {

    private final static Logger LOGGER = LoggerFactory.getLogger(HttpServer.class);

    public static void main(String[] args){

        LOGGER.info("Server Starting ... ");

        ConfigurationManager.getInstance().loadConfigurationFile("src/main/resources/http.json");

        Configuration conf = ConfigurationManager.getInstance().getCurrentConfiguration();

        LOGGER.info("Using port: " + conf.getPort());
        LOGGER.info("Using webroot: " + conf.getWebroot());

        ServerListenerThread serverListenerThread = null;
        try {
            serverListenerThread = new ServerListenerThread(conf.getPort(), conf.getWebroot());
            serverListenerThread.start();
        } catch (IOException e) {
            e.printStackTrace();
            // TODO Handle later
        }


    }
}
