package org.example.client;

public class FailedToUpdateProductException extends Exception {
    public FailedToUpdateProductException(String err) {
   super(err);
    }
}
