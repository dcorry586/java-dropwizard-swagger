package org.example.client;

public class InvalidOrderException extends Throwable {
    public InvalidOrderException(String err) {
        super(err);
    }
}
