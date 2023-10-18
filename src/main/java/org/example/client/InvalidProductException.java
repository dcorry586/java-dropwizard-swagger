package org.example.client;

public class InvalidProductException extends Throwable {
    public InvalidProductException(String err) {
        super(err);
    }
}
