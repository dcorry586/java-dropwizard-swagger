package org.example.cli;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Date;

public class OrderRequestUpdate {

    private int orderId;

    private int customerId;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    private Date dispatchDate;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Date getDispatchDate() {
        return dispatchDate;
    }

    public void setDispatchDate(Date dispatchDate) {
        this.dispatchDate = dispatchDate;
    }

    @JsonCreator
    public OrderRequestUpdate(@JsonProperty("orderId") int orderId,
                              @JsonProperty("dispatchDate") Date dispatchDate
    ) {
        this.orderId = orderId;
        this.dispatchDate = dispatchDate;
    }
}
