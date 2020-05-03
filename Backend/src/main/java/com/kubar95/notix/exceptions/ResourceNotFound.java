package com.kubar95.notix.exceptions;

public class ResourceNotFound extends Exception {
    public ResourceNotFound() {
    }

    public ResourceNotFound(String message) {
        super(message);
    }

    public ResourceNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceNotFound(Throwable cause) {
        super(cause);
    }
}
