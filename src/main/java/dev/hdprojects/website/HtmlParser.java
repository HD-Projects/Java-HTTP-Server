package dev.hdprojects.website;

import dev.hdprojects.HttpServer.config.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class HtmlParser {

    private final static Logger LOGGER = LoggerFactory.getLogger(HtmlParser.class);

    private File file;
    private String filePath;
    private String css;
    private String JS;
    private int error = 200;
    private String contents = "";
    private String html = "";
    private Configuration configuration;

    public HtmlParser(String filePath, Configuration conf) {
        this.file = new File(filePath);
        this.filePath = filePath;
        this.configuration = conf;

        // Read the file
        try {
            // Open buffered reader
            // FileReader throws a FileNotFoundException
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

            int charValue;

            // Loop through the characters of
            // the file and store them to the contents String
            // can throw a IOException
            while ((charValue = bufferedReader.read()) != -1) contents += (char) charValue;
        } catch (FileNotFoundException exception) {
            if (filePath.charAt(filePath.length()-1) == '/') {
                try {
                    // Open the file
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(filePath + "index.html")));

                    int charValue;

                    // Loop through the characters of
                    // the file and store them to the contents String
                    // can throw a IOException
                    while ((charValue = bufferedReader.read()) != -1) contents += (char) charValue;
                } catch (FileNotFoundException exception1) {
                    // ERROR: 404
                    LOGGER.error("File Not Found: " + exception);
                } catch (IOException exception1) {
                    // ERROR: 500
                    LOGGER.error("Error reading file: " + exception);
                }
            } else if (!((filePath.substring(filePath.length()-5) == ".html") || (filePath.substring(filePath.length()-4) == ".htm"))) {
                try {
                    // Open the file
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(filePath + "/index.html")));

                    int charValue;

                    // Loop through the characters of
                    // the file and store them to the contents String
                    // can throw a IOException
                    while ((charValue = bufferedReader.read()) != -1) contents += (char) charValue;
                } catch (FileNotFoundException exception1) {
                    contents = error(404);
                    LOGGER.error("File Not Found: " + exception);
                } catch (IOException exception1) {
                    contents = error(500);
                    LOGGER.error("Error reading file: " + exception);
                }
            } else {
                contents = error(404);
                LOGGER.error("File Not Found: " + exception);
            }
        } catch (IOException exception) {
            contents = error(500);
            LOGGER.error("File Not Found: " + exception);
        }

        try {
            // Open the file
            BufferedReader tempBufferedReader = new BufferedReader(new FileReader(new File(configuration.getWebroot() + "/" + configuration.getJs())));

            int charValue;

            JS = "";

            // Loop through the characters of
            // the file and store them to the contents String
            // can throw a IOException
            while ((charValue = tempBufferedReader.read()) != -1) JS += (char) charValue;

            // Set the html tags
            JS = "<script>" + JS + "</script>";
        } catch (Exception exception) {
            JS = "/* Unknown Error */";
            JS = "<script>" + JS + "</script>";
            LOGGER.info("Error opening JS");
        }

        try {
            // Open the file
            BufferedReader tempBufferedReader = new BufferedReader(new FileReader(new File(configuration.getWebroot() + "/" + configuration.getCss())));

            int charValue;

            css = "";

            // Loop through the characters of
            // the file and store them to the contents String
            // can throw a IOException
            while ((charValue = tempBufferedReader.read()) != -1) css += (char) charValue;

            // Set the html tags
            css = "<style>" + css + "</style>";
        } catch (Exception exception) {
            css = "/* Unknown Error */";
            css = "<style>" + css + "</style>";
            LOGGER.info("Error opening css");
        }

        // Replace the style
        contents = contents.replaceAll(configuration.getStyle_replace(), css);

        // And the js
        contents = contents.replaceAll(configuration.getJs_replace(), JS);

        html = contents;
    }

    private String error(int error) {
        LOGGER.info("Error: " + 404);
        switch (error) {
            case 404:
                error = 404;
                File file = new File(configuration.getWebroot() + "/" + error + ".html");

                BufferedReader bufferedReader = null;
                try {
                    bufferedReader = new BufferedReader(new FileReader(file));
                } catch (FileNotFoundException exception) {
                    LOGGER.error(exception.getMessage());
                    return "Error: 500 INTERNAL SERVER ERROR";
                }

                int charValue;

                while (true) {
                    try {
                        if (!((charValue = bufferedReader.read()) != -1)) break;
                    } catch (IOException exception) {
                        LOGGER.error(exception.getMessage());
                        return "Error: 500 INTERNAL SERVER ERROR";
                    }
                    contents += (char) charValue;
                }
                return contents;
            default:
                error = 500;
                return "Error: 500 INTERNAL SERVER ERROR";
        }
    }

    @Override
    public String toString() {
        return html;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public File getFile() {
        return file;
    }

    public String getContents() {
        return contents;
    }
}
