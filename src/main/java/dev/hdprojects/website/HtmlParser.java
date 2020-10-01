package dev.hdprojects.website;

import dev.hdprojects.HttpServer.HttpServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class HtmlParser {

    private final static Logger LOGGER = LoggerFactory.getLogger(HttpServer.class);

    private File file;
    private String filePath;
    private String contents = "";
    private String html = "";

    public HtmlParser(String filePath, String css, String cssReplace, String JS, String JSReplace) {
        this.file = new File(filePath);
        this.filePath = filePath;

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
                    // ERROR: 404
                    LOGGER.error("File Not Found: " + exception);
                } catch (IOException exception1) {
                    // ERROR: 500
                    LOGGER.error("Error reading file: " + exception);
                }
            } else {
                // ERROR: 404
                LOGGER.error("File Not Found: " + exception);
            }
        } catch (IOException exception) {
            // ERROR: 500
            LOGGER.error("File Not Found: " + exception);
        }
        LOGGER.info(contents);

        // Replace the style
        //Replacer styleReplace = new Replacer(cssReplace, css);

        // And the js
        //Replacer jsReplace = new Replacer(styleReplace.toString(), JS);

        html = contents;
    }

    @Override
    public String toString() {
        return html;
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
