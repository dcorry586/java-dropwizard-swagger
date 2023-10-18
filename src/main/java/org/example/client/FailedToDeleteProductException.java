package org.example.client;

public class FailedToDeleteProductException extends Exception {
    @Override
    public String getMessage() {
        return "Failed to delete product from product table";
    }
}
