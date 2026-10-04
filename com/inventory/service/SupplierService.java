package com.inventory.service;

import com.inventory.model.Supplier;
import com.inventory.repository.SupplierRepository;

import java.sql.SQLException;
import java.util.List;

public class SupplierService {

    public void addSupplier(Supplier supplier) throws SQLException {

        if (supplier == null) return;

        if (supplier.getSupplierName() == null) return;
        if (supplier.getSupplierName().isBlank()) return;

        SupplierRepository repository = new SupplierRepository();

        Supplier existingSupplier =
                repository.findByName(supplier.getSupplierName());

        if (existingSupplier != null) return;

        repository.addSupplier(supplier);
    }


    public List<Supplier> findAllSuppliers() throws SQLException {

        SupplierRepository repository = new SupplierRepository();

        return repository.findAllSuppliers();
    }


    public Supplier findById(int supplierId) throws SQLException {

        if (supplierId <= 0) return null;

        SupplierRepository repository = new SupplierRepository();

        return repository.findById(supplierId);
    }


    public Supplier findByName(String supplierName) throws SQLException {

        if (supplierName == null) return null;

        if (supplierName.isBlank()) return null;

        SupplierRepository repository = new SupplierRepository();

        return repository.findByName(supplierName);
    }


    public void updateSupplier(Supplier supplier) throws SQLException {

        if (supplier == null) return;

        if (supplier.getSupplierId() <= 0) return;

        if (supplier.getSupplierName() == null) return;

        if (supplier.getSupplierName().isBlank()) return;

        SupplierRepository repository = new SupplierRepository();

        Supplier existingSupplier =
                repository.findById(supplier.getSupplierId());

        if (existingSupplier == null) return;

        Supplier supplierWithSameName =
                repository.findByName(supplier.getSupplierName());

        if (supplierWithSameName != null &&
            supplierWithSameName.getSupplierId() != supplier.getSupplierId()) {
            return;
        }

        repository.updateSupplier(supplier);
    }


    public void deleteSupplier(int supplierId) throws SQLException {

        if (supplierId <= 0) return;

        SupplierRepository repository = new SupplierRepository();

        Supplier existingSupplier =
                repository.findById(supplierId);

        if (existingSupplier == null) return;

        repository.deleteSupplier(supplierId);
    }
}