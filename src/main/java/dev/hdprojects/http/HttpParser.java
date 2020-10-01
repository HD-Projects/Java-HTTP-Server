package dev.hdprojects.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

public class HttpParser {

    private final static Logger LOGGER = LoggerFactory.getLogger(HttpParser.class);

    private InputStream inputStream;

    // Instantiate the http request inputs
    private String method = "";
    private String requestedPage = "";
    private String referer = "";
    private String userAgent = "";
    private String encoding = "";
    private String language = "";
    private String accept = "";
    private String connection = "";

    private int count = 0;

    public HttpParser(InputStream inputStream){
        this.inputStream = inputStream;
    }

    public void parseHttpRequest() throws IOException {

        LOGGER.info("HTTP Parser started");

        int _byte;

        String tempString = "";

        while( (_byte = this.inputStream.read()) >= 0){
            tempString += (char) _byte;

            // This if statement checks if there is a newline and resets the string if there is
            if (tempString.indexOf("\r\n") > 0) {
                tempString = "";
                count ++;
            } else if (count == 1) {
                if ((char) _byte == '\r') {
                    LOGGER.info("Exiting Parser Loop ... ");
                    break;
                } else {
                    count = 0;
                }
            }

            switch (tempString.toLowerCase()){
                case "get":
                    this.inputStream.read();
                    while ( (char) ( _byte = this.inputStream.read()) != ' ') {
                        requestedPage += (char) _byte;
                    }
                    while ( (char) (_byte = this.inputStream.read()) != '\r') {
                        method += (char) _byte;
                    }
                    this.inputStream.read();
                    count ++;
                    tempString = "";
                    break;
                case "connection":
                    this.inputStream.read();
                    this.inputStream.read();
                    while ( (char) ( _byte = this.inputStream.read()) != '\r') {
                        connection += (char) _byte;
                    }
                    this.inputStream.read();
                    count ++;
                    tempString = "";
                    break;
                case "user-agent":
                    this.inputStream.read();
                    this.inputStream.read();
                    while ( (char) ( _byte = this.inputStream.read()) != '\r') {
                        userAgent += (char) _byte;
                    }
                    this.inputStream.read();
                    count ++;
                    tempString = "";
                    break;
                case "referer":
                    this.inputStream.read();
                    this.inputStream.read();
                    while ( (char) ( _byte = this.inputStream.read()) != '\r') {
                        referer += (char) _byte;
                    }
                    this.inputStream.read();
                    count ++;
                    tempString = "";
                    break;
                case "accept":
                    tempString += (char) this.inputStream.read();
                    if ("accept-".equals(tempString.toLowerCase())) {
                        tempString += (char) this.inputStream.read();
                        if (tempString.toLowerCase().equals("accept-l")) {
                            while ((char) (_byte = this.inputStream.read()) != ':');
                            this.inputStream.read();
                            while ((char) (_byte = this.inputStream.read()) != '\r') language += (char) _byte;
                            this.inputStream.read();
                        } else if (tempString.toLowerCase().equals("accept-e")) {
                            while ((char) (_byte = this.inputStream.read()) != ':');
                            this.inputStream.read();
                            while ((char) (_byte = this.inputStream.read()) != '\r') encoding += (char) _byte;
                            this.inputStream.read();
                        }
                        count++;
                        tempString = "";
                    } else if ("accept:".equals(tempString.toLowerCase())) {
                        this.inputStream.read();
                        while ((char) (_byte = this.inputStream.read()) != '\r') {
                            accept += (char) _byte;
                        }
                        this.inputStream.read();
                        count++;
                        tempString = "";
                    }
                    break;
                default:
                    break;
            }
        }

        LOGGER.info("HTTP Parser Done.");
        LOGGER.info("Requested Page: {}", requestedPage);
        LOGGER.info("Connection mode: {}", connection);
        LOGGER.info("Method: {}", method);
        LOGGER.info("Referer: {}", referer);
        LOGGER.info("User-Agent: {}", userAgent);
        LOGGER.info("Accept: {}", accept);
        LOGGER.info("Accept-Encoding: {}", encoding);
        LOGGER.info("Accept-Language: {}", language);
    }

    public String getAccept() {
        return accept;
    }

    public void setAccept(String accept) {
        this.accept = accept;
    }

    public String getConnection() {
        return connection;
    }

    public void setConnection(String connection) {
        this.connection = connection;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getRequestedPage() {
        return requestedPage;
    }

    public void setRequestedPage(String requestedPage) {
        this.requestedPage = requestedPage;
    }

    public String getReferer() {
        return referer;
    }

    public void setReferer(String referer) {
        this.referer = referer;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
