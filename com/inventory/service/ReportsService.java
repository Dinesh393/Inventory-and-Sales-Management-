package com.inventory.service;

import com.inventory.repository.ReportsRepository;

import java.sql.SQLException;

public class ReportsService {

    public double getDailySales(String date) throws SQLException {
        if (date == null) return 0;
        if (date.isBlank()) return 0;
        ReportsRepository repository = new ReportsRepository();
        return repository.getDailySales(date);
    }

    public double getMonthlySales(int year, int month) throws SQLException {
        if (year <= 0) return 0;
        if (month < 1 || month > 12) return 0;
        ReportsRepository repository = new ReportsRepository();
        return repository.getMonthlySales(year, month);
    }


    public double getTotalSales() throws SQLException {
        ReportsRepository repository = new ReportsRepository();
        return repository.getTotalSales();
    }


    public double getTotalPurchases() throws SQLException {
        ReportsRepository repository = new ReportsRepository();
        return repository.getTotalPurchases();
    }


    public void getBestSellingProducts() throws SQLException {
        ReportsRepository repository = new ReportsRepository();
        repository.getBestSellingProducts();
    }
}