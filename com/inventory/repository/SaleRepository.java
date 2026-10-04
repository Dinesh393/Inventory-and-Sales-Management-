package com.inventory.repository;

import com.inventory.model.Sale;
import com.inventory.model.SaleItem;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SaleRepository {

    public int addSale(Sale sale, Connection connection) throws SQLException {

        String sql = "INSERT INTO sale(customerId, saleDate, totalAmount) "
                   + "VALUES (?, ?, ?)";

        try (PreparedStatement statement =
                     connection.prepareStatement(
                             sql,
                             Statement.RETURN_GENERATED_KEYS)) {

            statement.setInt(1, sale.getCustomer().getCustomerId());
            statement.setDate(2, Date.valueOf(sale.getSaleDate()));
            statement.setDouble(3, sale.getTotalAmount());

            statement.executeUpdate();

            try (ResultSet resultSet = statement.getGeneratedKeys()) {

                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }
        }

        return 0;
    }


    public void addSaleItem(
            SaleItem saleItem,
            int saleId,
            Connection connection) throws SQLException {

        String sql = "INSERT INTO saleitem"
                   + "(saleId, productId, quantity, unitPrice, totalPrice) "
                   + "VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, saleId);
            statement.setInt(2, saleItem.getProduct().getProductId());
            statement.setInt(3, saleItem.getQuantity());
            statement.setDouble(4, saleItem.getUnitPrice());
            statement.setDouble(5, saleItem.getTotalPrice());

            statement.executeUpdate();
        }
    }


    public void decreaseProductStock(
            int productId,
            int quantity,
            Connection connection) throws SQLException {

        String sql = "UPDATE product "
                   + "SET stockQuantity = stockQuantity - ? "
                   + "WHERE productId = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, quantity);
            statement.setInt(2, productId);

            statement.executeUpdate();
        }
    }
}