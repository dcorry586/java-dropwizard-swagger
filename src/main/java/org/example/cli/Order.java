package org.example.cli;

import java.sql.Date;

public class Order implements Comparable<Order>{
    private int orderId;
    private Date orderDate;
    private int customerID;

    public Order(int orderId, int customerID, Date orderDate) {
        setOrderId(orderId);
        setCustomerID(customerID);
        setOrderDate(orderDate);
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

@Override
    public int compareTo(Order order) {
        return getOrderDate().compareTo(order.getOrderDate());
}

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", orderDate=" + orderDate +
                ", customerID=" + customerID +
                '}';
    }
}
