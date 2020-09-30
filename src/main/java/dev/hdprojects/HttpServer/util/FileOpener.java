package dev.hdprojects.HttpServer.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileOpener {

    private final static Logger LOGGER = LoggerFactory.getLogger(FileOpener.class);

    private String filePath;
    private String fileContents;


    /**
     * Get a file as a String
     *
     * @param fileReader The fileReader object that you want read
     * @return The contents of the file
     * @throws IOException Throws an IOException if it cannot read the file
     */
    private String getFileAsString(FileReader fileReader) throws IOException{
        String result = new String("");

        int i;
        while ((i=fileReader.read()) != -1)
            result += (char) i;
        return result;
    }

    private void openFileString() {

        FileReader fileReader = null;

        try {
            fileReader = new FileReader(filePath);
        } catch (FileNotFoundException e) {
            LOGGER.error("File not found");
        }

        try {
            // Throws error if file is not found
            if (fileReader == null) throw new FileOpenerException("File Not Found");

            // Sets file to the contents of the fileReader object
            else this.fileContents = getFileAsString(fileReader);
        } catch (IOException e) {
            LOGGER.error("File failed to read");
        }
    }

    public FileOpener(String filePath) {
        this.filePath = filePath;

        openFileString();


    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
        openFileString();
    }

    public String getFileContents() {
        return fileContents;
    }

    public void setFileContents(String fileContents) {
        this.fileContents = fileContents;
        openFileString();
    }
}
