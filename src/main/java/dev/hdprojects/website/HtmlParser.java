package dev.hdprojects.website;

import dev.hdprojects.HttpServer.HttpServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class HtmlParser {

    private final static Logger LOGGER = LoggerFactory.getLogger(HttpServer.class);

    private File file;
    private String contents = "";
    private String html = "";

    public HtmlParser(File file, String css, String cssReplace, String JS, String JSReplace) {
        this.file = file;

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
            LOGGER.error("File Not Found: " + exception);
        } catch (IOException exception) {
            LOGGER.error("Can't Read File: " + exception);
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
