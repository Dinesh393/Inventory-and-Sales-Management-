package com.inventory.service;

import com.inventory.model.Product;
import com.inventory.repository.InventoryRepository;

import java.sql.SQLException;
import java.util.List;

public class InventoryService {

    public List<Product> findAllInventory() throws SQLException {

        InventoryRepository repository = new InventoryRepository();

        return repository.findAllInventory();
    }


    public List<Product> findLowStockProducts() throws SQLException {

        InventoryRepository repository = new InventoryRepository();

        return repository.findLowStockProducts();
    }


    public int getStockQuantity(int productId) throws SQLException {

        if (productId <= 0) return -1;

        InventoryRepository repository = new InventoryRepository();

        return repository.getStockQuantity(productId);
    }
}