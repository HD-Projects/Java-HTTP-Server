package dev.hdprojects.HttpServer.config;

public class Configuration {

    private int port;
    private String webroot;
    private String[] css;
    private String[] js;

    public String[] getCss() {
        return css;
    }

    public void setCss(String[] css) {
        this.css = css;
    }

    public String[] getJs() {
        return js;
    }

    public void setJs(String[] js) {
        this.js = js;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getWebroot() {
        return webroot;
    }

    public void setWebroot(String webroot) {
        this.webroot = webroot;
    }
}
