package org.example.client;

public class FailedToUpdateOrderException extends Throwable {
    public FailedToUpdateOrderException(String message) {
   super(message);
    }
}
