package com.inventory.repository;

import com.inventory.model.Customer;
import com.inventory.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepository {

    public void addCustomer(Customer customer) throws SQLException {

        String sql = "INSERT INTO customer(customerName, phone, email, address) "
                   + "VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, customer.getCustomerName());
            statement.setString(2, customer.getPhone());
            statement.setString(3, customer.getEmail());
            statement.setString(4, customer.getAddress());

            statement.executeUpdate();
        }
    }


    public List<Customer> findAllCustomers() throws SQLException {

        String sql = "SELECT * FROM customer";

        List<Customer> customers = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                int customerId = resultSet.getInt("customerId");
                String customerName = resultSet.getString("customerName");
                String phone = resultSet.getString("phone");
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");

                Customer customer = new Customer(
                        customerId,
                        customerName,
                        phone,
                        email,
                        address
                );

                customers.add(customer);
            }
        }

        return customers;
    }


    public Customer findByName(String customerName) throws SQLException {

        String sql = "SELECT * FROM customer WHERE customerName = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, customerName);

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {

                    int customerId = resultSet.getInt("customerId");
                    String name = resultSet.getString("customerName");
                    String phone = resultSet.getString("phone");
                    String email = resultSet.getString("email");
                    String address = resultSet.getString("address");

                    return new Customer(
                            customerId,
                            name,
                            phone,
                            email,
                            address
                    );
                }
            }
        }

        return null;
    }


    public Customer findById(int customerId) throws SQLException {

        String sql = "SELECT * FROM customer WHERE customerId = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, customerId);

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {

                    int id = resultSet.getInt("customerId");
                    String customerName = resultSet.getString("customerName");
                    String phone = resultSet.getString("phone");
                    String email = resultSet.getString("email");
                    String address = resultSet.getString("address");

                    return new Customer(
                            id,
                            customerName,
                            phone,
                            email,
                            address
                    );
                }
            }
        }

        return null;
    }


    public void updateCustomer(Customer customer) throws SQLException {

        String sql = "UPDATE customer SET customerName=?, phone=?, "
                   + "email=?, address=? WHERE customerId=?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, customer.getCustomerName());
            statement.setString(2, customer.getPhone());
            statement.setString(3, customer.getEmail());
            statement.setString(4, customer.getAddress());
            statement.setInt(5, customer.getCustomerId());

            statement.executeUpdate();
        }
    }


    public void deleteCustomer(int customerId) throws SQLException {

        String sql = "DELETE FROM customer WHERE customerId = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, customerId);

            statement.executeUpdate();
        }
    }
}