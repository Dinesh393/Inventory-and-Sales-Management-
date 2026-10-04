package com.inventory.main;

import java.sql.SQLException;

import com.inventory.model.Supplier;
import com.inventory.repository.SupplierRepository;

public class SupplierRepositoryTest {

    public static void main(String[] args) throws SQLException{

            SupplierRepository repository = new SupplierRepository();

            repository.deleteSupplier(1);

            System.out.println("Supplier updated successfully.");
    }
}