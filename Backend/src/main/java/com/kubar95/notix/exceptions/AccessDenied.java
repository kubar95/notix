package com.kubar95.notix.exceptions;

public class AccessDenied extends Exception {

    public AccessDenied() {
    }

    public AccessDenied(String message) {
        super(message);
    }

    public AccessDenied(String message, Throwable cause) {
        super(message, cause);
    }

    public AccessDenied(Throwable cause) {
        super(cause);
    }
}
