package org.example.client;

public class ProductDoesNotExistException extends Exception {

    private String message;

//    public ProductDoesNotExistException(String message) {
//        super(message);
//        this.message = message;
//    }

    @Override
    public String getMessage() {
        return "Product does not exist with that Id";
    }
}
