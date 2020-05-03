package com.kubar95.notix.exceptions;

public class UserAlreadyInRepository extends Exception {
    public UserAlreadyInRepository() {
    }

    public UserAlreadyInRepository(String message) {
        super(message);
    }
}
