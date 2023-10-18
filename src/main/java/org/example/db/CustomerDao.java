package org.example.db;

import org.example.cli.Customer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CustomerDao {
    private DatabaseConnector databaseConnector = new DatabaseConnector();

    public Customer getCustomerById(int id) throws SQLException {
        Connection c = DatabaseConnector.getConnection();
        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery("SELECT CustomerID FROM Customer WHERE CustomerID=" + id);

        while (rs.next()) {
            return new Customer(
                    rs.getInt("CustomerID")
            );
        }
        return null;
    }
}
