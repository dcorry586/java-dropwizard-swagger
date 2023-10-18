package org.example.client;

public class FailedToCreateOrderException extends Exception {
    public FailedToCreateOrderException(String message) {
        super(message);
    }
}
