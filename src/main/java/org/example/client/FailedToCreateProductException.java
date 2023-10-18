package org.example.client;

public class FailedToCreateProductException extends Exception {
    @Override
    public String getMessage() {
        return "Failed to insert product into Product table.";
    }
}
