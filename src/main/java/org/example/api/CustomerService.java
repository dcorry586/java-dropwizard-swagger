package org.example.api;

import org.example.cli.Customer;
import org.example.cli.Order;
import org.example.client.CustomerDoesNotExistException;
import org.example.client.FailedToGetOrdersException;
import org.example.client.OrderDoesNotExistException;
import org.example.db.CustomerDao;

import java.sql.SQLException;

public class CustomerService {
private CustomerDao customerDao = new CustomerDao();

    public int getCustomerById(int id) throws CustomerDoesNotExistException {
        try {
            Customer customer = customerDao.getCustomerById(id);

            if (customer == null) {
                throw new CustomerDoesNotExistException("Customer does not exist with that id.");
            }
            return customer.getCustomerId();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new CustomerDoesNotExistException(e.getMessage());
        }
    }
}
