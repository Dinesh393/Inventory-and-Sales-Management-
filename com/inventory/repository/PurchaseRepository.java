package com.inventory.repository;

import com.inventory.model.Purchase;
import com.inventory.model.PurchaseItem;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PurchaseRepository {

    public int addPurchase(Purchase purchase, Connection connection) throws SQLException {

        String sql = "INSERT INTO purchase(supplierId, purchaseDate, totalAmount) "
                   + "VALUES (?, ?, ?)";

        try (PreparedStatement statement =
                     connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setInt(1, purchase.getSupplier().getSupplierId());
            statement.setDate(2, Date.valueOf(purchase.getPurchaseDate()));
            statement.setDouble(3, purchase.getTotalAmount());

            statement.executeUpdate();

            try (ResultSet resultSet = statement.getGeneratedKeys()) {

                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }
        }

        return 0;
    }


    public void addPurchaseItem(
            PurchaseItem purchaseItem,
            int purchaseId,
            Connection connection) throws SQLException {

        String sql = "INSERT INTO purchaseitem"
                   + "(purchaseId, productId, quantity, unitPrice, totalPrice) "
                   + "VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, purchaseId);
            statement.setInt(2, purchaseItem.getProduct().getProductId());
            statement.setInt(3, purchaseItem.getQuantity());
            statement.setDouble(4, purchaseItem.getUnitPrice());
            statement.setDouble(5, purchaseItem.getTotalPrice());

            statement.executeUpdate();
        }
    }


    public void increaseProductStock(
            int productId,
            int quantity,
            Connection connection) throws SQLException {

        String sql = "UPDATE product "
                   + "SET stockQuantity = stockQuantity + ? "
                   + "WHERE productId = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, quantity);
            statement.setInt(2, productId);

            statement.executeUpdate();
        }
    }
}