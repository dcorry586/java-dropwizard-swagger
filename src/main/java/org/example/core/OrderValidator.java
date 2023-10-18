package org.example.core;

import org.example.api.CustomerService;
import org.example.api.OrderService;
import org.example.cli.OrderRequest;
import org.example.cli.OrderRequestUpdate;
import org.example.client.CustomerDoesNotExistException;
import org.example.client.FailedToGetOrdersException;
import org.example.client.OrderDoesNotExistException;

import java.sql.Date;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;

public class OrderValidator {
    private CustomerService customerService = new CustomerService();
//    private OrderService orderService= new OrderService();


    public String isValidOrder(OrderRequest order) throws CustomerDoesNotExistException {
        // TODO: Validate customerID // using a new custoerDAO class, check if customerID is in DB
        try {
            if (order.getCustomerId() != customerService.getCustomerById(order.getCustomerId())) {
                return "Customer with given ID does not exist.";
            }
        } catch (CustomerDoesNotExistException e) {
            System.err.println(e.getMessage());
            throw new CustomerDoesNotExistException(e.getMessage());
        }

        // TODO: validate order date is not older than 1 year
        LocalDate currentDate = LocalDate.now();
        LocalDate currentDateMinus12Months = currentDate.minusMonths(12);

        // Get the date of one year ago
        Date oneYearAgo = (Date) Date.from(Instant.now().minus(Duration.ofDays(365)));

        // If the order date os older than 1 year, return false
        if (order.getOrderDate().before(oneYearAgo)) {
            return "Order date is older than 1 year.";
        }

        return null;
    }

    public String isValidUpdateOrder(OrderRequestUpdate order) throws OrderDoesNotExistException{
       OrderService orderService = new OrderService();
        // TODO: Validate order id
        try {
            if (order.getOrderId() != orderService.getOrderById(order.getOrderId()).getOrderId()) {
                return "Customer with given ID does not exist.";
            }
        }  catch (FailedToGetOrdersException e) {
            System.err.println(e.getMessage());
            throw new OrderDoesNotExistException();
        } catch (OrderDoesNotExistException e) {
            throw new RuntimeException(e);
        }

        // TODO: validate order date is not older than 1 year

        // Get the date of one year ago
        Date z = (Date) Date.from(Instant.now().minus(Duration.ofDays(365)));

        // If the order date os older than 1 year, return false
        if (order.getDispatchDate().before(z)) {
            return "Dispatch date is older than 1 year.";
        }

        return null;
    }

}
