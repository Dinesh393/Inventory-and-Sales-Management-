package com.inventory.repository;

import com.inventory.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportsRepository {

    public double getDailySales(String date) throws SQLException {
        String sql = "SELECT COALESCE(SUM(totalAmount), 0) "
                   + "FROM sale "
                   + "WHERE saleDate = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDate(1, java.sql.Date.valueOf(date));
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getDouble(1);
                }
            }
        }

        return 0;
    }


    public double getMonthlySales(int year, int month) throws SQLException {

        String sql = "SELECT COALESCE(SUM(totalAmount), 0) "
                   + "FROM sale "
                   + "WHERE YEAR(saleDate) = ? "
                   + "AND MONTH(saleDate) = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, year);
            statement.setInt(2, month);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getDouble(1);
                }
            }
        }

        return 0;
    }


    public double getTotalSales() throws SQLException {

        String sql = "SELECT COALESCE(SUM(totalAmount), 0) FROM sale";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getDouble(1);
            }
        }

        return 0;
    }


    public double getTotalPurchases() throws SQLException {
        String sql = "SELECT COALESCE(SUM(totalAmount), 0) FROM purchase";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getDouble(1);
            }
        }
        return 0;
    }


    public void getBestSellingProducts() throws SQLException {
        String sql = "SELECT p.productName, "
                   + "SUM(si.quantity) AS totalQuantity "
                   + "FROM saleitem si "
                   + "JOIN product p ON si.productId = p.productId "
                   + "GROUP BY p.productId, p.productName "
                   + "ORDER BY totalQuantity DESC";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                String productName =
                        resultSet.getString("productName");

                int totalQuantity =
                        resultSet.getInt("totalQuantity");

                System.out.println(
                        productName + " - " + totalQuantity + " units sold"
                );
            }
        }
    }
}