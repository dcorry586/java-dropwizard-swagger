package org.example.db;

import org.example.cli.Order;
import org.example.cli.OrderRequest;
import org.example.cli.OrderRequestUpdate;
import org.example.cli.ProductRequest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class OrderDAO {
    private DatabaseConnector databaseConnector = new DatabaseConnector();


    public List<Order> getAllOrders() throws SQLException {
        Connection c = databaseConnector.getConnection();
        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery("SELECT OrderID, CustomerID, OrderDate FROM `Order`;");

        List<Order> orderList = new ArrayList<>();

        while (rs.next()) {
            Order order = new Order(
                    rs.getInt("OrderID"),
                    rs.getInt("CustomerID"),
                    rs.getDate("OrderDate")
            );
            orderList.add(order);
        }
        return orderList;
    }

    public Order getOrderById(int id) throws SQLException {
        Connection c = DatabaseConnector.getConnection();
        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery("SELECT OrderID, CustomerID, OrderDate FROM `Order` WHERE OrderID=" + id);

        while (rs.next()) {
            return new Order(
                    rs.getInt("OrderID"),
                    rs.getInt("CustomerID"),
                    rs.getDate("OrderDate")
            );
        }
        return null;
    }

    public int createOrder(OrderRequest order) throws SQLException {
        Connection c = DatabaseConnector.getConnection();

        String insertStatement = "INSERT INTO `Order` (OrderDate, CustomerID) VALUES (?,?)";

        PreparedStatement st = c.prepareStatement(insertStatement, Statement.RETURN_GENERATED_KEYS);

        st.setDate(1, order.getOrderDate());
        st.setInt(2, order.getCustomerId());

        st.executeUpdate();

        ResultSet rs = st.getGeneratedKeys();

        if (rs.next()) {
            return rs.getInt(1);
        }

        return -1;
    }

    public OrderRequestUpdate getOrderRequestById(int id) throws SQLException{
        Connection c = DatabaseConnector.getConnection();
        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery("SELECT OrderID, DispatchDate FROM `Order` WHERE OrderID=" + id);

        while (rs.next()) {
            return new OrderRequestUpdate(
                    rs.getInt("OrderID"),
                    rs.getDate("DispatchDate")
            );
        }
        return null;
    }

    public void updateOrder(int id, OrderRequestUpdate order) throws SQLException {
        Connection c = DatabaseConnector.getConnection();

        String insertStatement = "UPDATE `Order` SET DispatchDate = ? WHERE ProductID = ?";

        PreparedStatement st = c.prepareStatement(insertStatement, Statement.RETURN_GENERATED_KEYS);

        st.setDate(1, order.getDispatchDate());
        st.setInt(2, id);

        st.executeUpdate();
    }
}
