package dev.hdprojects.HttpServer.util;

import java.io.IOException;

public class FileOpenerException extends IOException {

    public FileOpenerException() {
        super();
    }

    public FileOpenerException(String message) {
        super(message);
    }

    public FileOpenerException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileOpenerException(Throwable cause) {
        super(cause);
    }
}
