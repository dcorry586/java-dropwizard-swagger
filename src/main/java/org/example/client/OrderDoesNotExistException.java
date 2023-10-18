package org.example.client;

public class OrderDoesNotExistException extends Throwable {

    @Override
    public String getMessage() {
        return "Order does exist with that ID";
    }
}
