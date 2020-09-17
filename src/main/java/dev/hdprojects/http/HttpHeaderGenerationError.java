package dev.hdprojects.http;

public class HttpHeaderGenerationError extends Exception{
    public HttpHeaderGenerationError(String message) {
        super(message);
    }

    public HttpHeaderGenerationError(String message, Throwable cause) {
        super(message, cause);
    }

    public HttpHeaderGenerationError(Throwable cause) {
        super(cause);
    }
}
