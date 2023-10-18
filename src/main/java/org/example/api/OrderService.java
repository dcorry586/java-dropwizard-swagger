package org.example.api;

import org.example.cli.Order;
import org.example.cli.OrderRequest;
import org.example.cli.OrderRequestUpdate;
import org.example.cli.Product;
import org.example.client.*;
import org.example.core.OrderValidator;
import org.example.db.OrderDAO;

import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OrderService {
    private OrderDAO orderDAO = new OrderDAO();
private OrderValidator orderValidator = new OrderValidator();
    public List<Order> getAllOrders() throws FailedToGetOrdersException {
        List<Order> orderList = null;
        try {
            orderList = orderDAO.getAllOrders();
        } catch (SQLException e) {
            throw new FailedToGetOrdersException("Failed to fetch from Order table.");
        }

        // 21. Update your `OrderService` and `Order` classes to print
        // out the `OrderID`, `CustomerID` and `OrderDate` of all orders
//        for (Order order: orderList) {
//            System.out.println(order.getOrderId());
//            System.out.println(order.getCustomerID());
//            System.out.println(order.getOrderDate());
//        }

        // 22. Update your `OrderService` and `Order` classes to print out order the list by order date descending
//        Collections.sort(orderList);
//       Collections.reverse(orderList);
//      orderList.stream().forEach(o -> System.out.println(o.getOrderDate()));

        // 23. Update your `OrderService` to only show orders from the last week
//        final long DAYS_IN_WEEK = 60 * 60 * 24 * 7;
//        orderList.stream().filter(item -> item.getOrderDate()
//                .after(new Date(Instant.now().minusSeconds(DAYS_IN_WEEK).toEpochMilli())
//                )).forEach(item -> System.out.println(item.getOrderDate()));

        // 24. Update your `OrderService` to only show orders from customer with `CustomerID` 1
//       orderList.stream().filter(order -> order.getCustomerID() == 1)
//               .forEach(item -> System.out.println(item.getOrderId() + " " + item.getCustomerID()));

        // 25. Update your `OrderService` to only show the most recent order
        // order by date and reverse and show last element
//      Collections.sort(orderList);
//      Collections.reverse(orderList);
//        System.out.println("last one ordered item -> " + orderList.get(0).getOrderId());

        // 26. Update your `OrderService` to only show the oldest order
//        Collections.sort(orderList);
//        System.out.println("last one ordered item -> " + orderList.get(0).getOrderId());

        // 27. Update your `OrderService` to show the total count of all orders
//        int totalOrderCount = orderList.size();
//        System.out.println("total orders -> " + totalOrderCount);

        // 28. Update your `OrderService` to show the customer ID with the most orders
        Map<Integer, Long> orderMap = orderList
                .stream()
                .collect(Collectors.groupingBy(Order::getCustomerID, Collectors.counting()));
        System.out.println("orderMap -> " + orderMap);


        System.out.println("Customer ID with most orders: " + Collections.max(orderMap.entrySet(), Map.Entry.comparingByValue()).getKey());
        System.out.println("Customer ID with least orders: " + Collections.min(orderMap.entrySet(), Map.Entry.comparingByValue()).getKey());

        return orderList;
    }

    public Order getOrderById(int id) throws FailedToGetOrdersException, OrderDoesNotExistException {
        try {
            Order order = orderDAO.getOrderById(id);

            if (order == null) {
                throw new OrderDoesNotExistException();
            }
            return order;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new FailedToGetOrdersException("Failed to fetch from Order.");
        }
    }


//    public int createOrder(OrderRequest order) throws FailedToCreateOrderException, InvalidOrderException, CustomerDoesNotExistException {
//        try {
//            String validation = orderValidator.isValidOrder(order);
//
//            if (validation != null) {
//                throw new InvalidOrderException(validation);
//            }
//
//            int id = orderDAO.createOrder(order);
//
//            if (id == -1) {
//                throw new FailedToCreateOrderException("failed to insert into order table");
//            }
//            return id;
//        } catch (SQLException e) {
//            System.err.println(e.getMessage());
//            throw new CustomerDoesNotExistException(e.getMessage());
//        }
//
//    }

    public void updateOrder(int id, OrderRequestUpdate order) throws InvalidOrderException, OrderDoesNotExistException, FailedToUpdateOrderException {
        try {
            String validation = orderValidator.isValidUpdateOrder(order);

            if (validation != null) {
                throw new InvalidOrderException(validation);
            }

            OrderRequestUpdate orderToUpdate = orderDAO.getOrderRequestById(id);

            if (orderToUpdate == null) {
                throw new OrderDoesNotExistException();
            }

            orderDAO.updateOrder(id, order);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new FailedToUpdateOrderException(e.getMessage());
        }
    }
}
