package org.example.cli;

public class Customer {
private int customerId;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public Customer(int customerId) {
        this.customerId = customerId;
    }
}
