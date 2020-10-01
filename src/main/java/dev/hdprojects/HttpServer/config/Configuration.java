package dev.hdprojects.HttpServer.config;

public class Configuration {

    private int port;
    private String webroot;
    private String style_replace;
    private String js_replace;
    private String[] css;
    private String[] js;

    public String getStyle_replace() {
        return style_replace;
    }

    public void setStyle_replace(String style_replace) {
        this.style_replace = style_replace;
    }

    public String getJs_replace() {
        return js_replace;
    }

    public void setJs_replace(String js_replace) {
        this.js_replace = js_replace;
    }

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
