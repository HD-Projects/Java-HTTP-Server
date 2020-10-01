package dev.hdprojects.http;

import java.util.Date;

public class GenerateHttpHeader {

    final private String CRLF = "\r\n";

    private int responseCode;
    private int contentLength;
    private String responseString;
    private String contentType;
    private String server;
    private String header;


    /**
     * A simple http header constructor
     *
     */
    public GenerateHttpHeader() {
        this(200, 0, "HD-Projects", "text/html");
    }

    /**
     * A simple http header constructors
     *
     * @param responseCode The response code you want
     * @param contentLength The length of the content
     * @param server The server string that you want
     */
    public GenerateHttpHeader(int responseCode, int contentLength, String contentType, String server) {
        this.responseCode = responseCode;
        this.contentLength = contentLength;
        this.contentType = contentType;
        this.server = server;

        this.header = "HTTP/1.1 " + responseCode + this.generateResponseString() + CRLF +
                "Date: "+ new Date() + CRLF +
                "Server: " + CRLF +
                "Content-Length: " + this.contentLength + CRLF +
                "Content-Type: " + this.contentType + CRLF + CRLF;
    }

    private String generateResponseString(){
        switch (this.responseCode) {
            case 200:
                this.responseString = "OK";
                break;
            case 201:
                this.responseString = "Created";
                break;
            case 202:
                this.responseString = "Accepted";
                break;
            case 203:
                this.responseString = "Non-Authoritative Information";
                break;
            case 204:
                this.responseString = "No Content";
                break;
            case 205:
                this.responseString = "Reset Content";
                break;
            case 206:
                this.responseString = "Partial Content";
                break;
            case 207:
                this.responseString = "Multi-Status";
                break;
            case 208:
                this.responseString = "Already Reported";
                break;
            case 226:
                this.responseString = "IM Used";
                break;
            case 400:
                this.responseString = "Bad Request";
                break;
            case 401:
                this.responseString = "Unauthorized";
                break;
            case 402:
                this.responseString = "Payment Required";
                break;
            case 403:
                this.responseString = "Forbidden";
                break;
            case 404:
                this.responseString = "Not Found";
                break;
            case 409:
                this.responseString = "Gone";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + this.responseCode);
        }

        return this.responseString;
    }

    @Override
    public String toString() {
        return this.header;
    }

    // Getters and Setters
    public String getResponseString() {
        return responseString;
    }

    public void setResponseString(String responseString) {
        this.responseString = responseString;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public int getContentLength() {
        return contentLength;
    }

    public void setContentLength(int contentLength) {
        this.contentLength = contentLength;
    }
}
