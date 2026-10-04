package com.inventory.repository;

import com.inventory.model.Product;
import com.inventory.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InventoryRepository {

    public List<Product> findAllInventory() throws SQLException {

        String sql = "SELECT p.productId, p.productName, "
                   + "c.categoryId, c.categoryName, c.description, "
                   + "p.purchasePrice, p.sellingPrice, "
                   + "p.stockQuantity, p.minimumStockLevel "
                   + "FROM product p "
                   + "JOIN category c ON p.categoryId = c.categoryId";

        List<Product> products = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                int productId = resultSet.getInt("productId");
                String productName = resultSet.getString("productName");

                int categoryId = resultSet.getInt("categoryId");
                String categoryName = resultSet.getString("categoryName");
                String description = resultSet.getString("description");

                double purchasePrice = resultSet.getDouble("purchasePrice");
                double sellingPrice = resultSet.getDouble("sellingPrice");
                int stockQuantity = resultSet.getInt("stockQuantity");
                int minimumStockLevel = resultSet.getInt("minimumStockLevel");

                com.inventory.model.Category category =
                        new com.inventory.model.Category(
                                categoryId,
                                categoryName,
                                description
                        );

                Product product = new Product(
                        productId,
                        productName,
                        category,
                        purchasePrice,
                        sellingPrice,
                        stockQuantity,
                        minimumStockLevel
                );

                products.add(product);
            }
        }

        return products;
    }


    public List<Product> findLowStockProducts() throws SQLException {

        String sql = "SELECT p.productId, p.productName, "
                   + "c.categoryId, c.categoryName, c.description, "
                   + "p.purchasePrice, p.sellingPrice, "
                   + "p.stockQuantity, p.minimumStockLevel "
                   + "FROM product p "
                   + "JOIN category c ON p.categoryId = c.categoryId "
                   + "WHERE p.stockQuantity <= p.minimumStockLevel";

        List<Product> products = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                int productId = resultSet.getInt("productId");
                String productName = resultSet.getString("productName");

                int categoryId = resultSet.getInt("categoryId");
                String categoryName = resultSet.getString("categoryName");
                String description = resultSet.getString("description");

                double purchasePrice = resultSet.getDouble("purchasePrice");
                double sellingPrice = resultSet.getDouble("sellingPrice");
                int stockQuantity = resultSet.getInt("stockQuantity");
                int minimumStockLevel = resultSet.getInt("minimumStockLevel");

                com.inventory.model.Category category =
                        new com.inventory.model.Category(
                                categoryId,
                                categoryName,
                                description
                        );

                Product product = new Product(
                        productId,
                        productName,
                        category,
                        purchasePrice,
                        sellingPrice,
                        stockQuantity,
                        minimumStockLevel
                );

                products.add(product);
            }
        }

        return products;
    }


    public int getStockQuantity(int productId) throws SQLException {

        String sql = "SELECT stockQuantity FROM product WHERE productId = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, productId);
            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {
                    return resultSet.getInt("stockQuantity");
                }
            }
        }

        return -1;
    }
}