package org.example.cli;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Date;

public class OrderRequest {
    private Date orderDate;
    private int customerId;

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    @JsonCreator
    public OrderRequest(
            @JsonProperty("orderDate") Date orderDate,
            @JsonProperty("customerId") int customerId
    ) {
        this.orderDate = orderDate;
        this.customerId = customerId;
    }
}
