package org.example.client;

public class FailedToGetOrdersException extends Throwable {

    private String message;

    public FailedToGetOrdersException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
//        return super.getMessage();
        return message;
    }
}
