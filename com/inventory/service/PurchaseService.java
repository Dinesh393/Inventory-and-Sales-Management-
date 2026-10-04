package com.inventory.service;

import com.inventory.model.Product;
import com.inventory.model.Purchase;
import com.inventory.model.PurchaseItem;
import com.inventory.repository.ProductRepository;
import com.inventory.repository.PurchaseRepository;
import com.inventory.repository.SupplierRepository;
import com.inventory.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PurchaseService {

    public void addPurchase(Purchase purchase) throws SQLException {

        if (purchase == null) return;

        if (purchase.getSupplier() == null) return;

        if (purchase.getPurchaseDate() == null) return;

        if (purchase.getPurchaseDate().isBlank()) return;

        if (purchase.getPurchaseItems() == null) return;

        if (purchase.getPurchaseItems().isEmpty()) return;


        SupplierRepository supplierRepository = new SupplierRepository();

        if (supplierRepository.findById(
                purchase.getSupplier().getSupplierId()) == null) {
            return;
        }


        double purchaseTotal = 0;


        ProductRepository productRepository = new ProductRepository();


        for (PurchaseItem purchaseItem : purchase.getPurchaseItems()) {

            if (purchaseItem == null) return;

            if (purchaseItem.getProduct() == null) return;

            if (purchaseItem.getQuantity() <= 0) return;

            if (purchaseItem.getUnitPrice() <= 0) return;


            Product product =
                    productRepository.findById(
                            purchaseItem.getProduct().getProductId());

            if (product == null) return;


            double itemTotal =
                    purchaseItem.getQuantity()
                    * purchaseItem.getUnitPrice();

            purchaseItem.setTotalPrice(itemTotal);

            purchaseTotal += itemTotal;
        }


        purchase.setTotalAmount(purchaseTotal);


        PurchaseRepository purchaseRepository =
                new PurchaseRepository();


        try (Connection connection =
                     DatabaseConnection.getConnection()) {

            try {

                connection.setAutoCommit(false);


                int purchaseId =
                        purchaseRepository.addPurchase(
                                purchase,
                                connection);


                if (purchaseId <= 0) {
                    connection.rollback();
                    return;
                }


                for (PurchaseItem purchaseItem :
                        purchase.getPurchaseItems()) {

                    purchaseRepository.addPurchaseItem(
                            purchaseItem,
                            purchaseId,
                            connection);


                    purchaseRepository.increaseProductStock(
                            purchaseItem.getProduct().getProductId(),
                            purchaseItem.getQuantity(),
                            connection);
                }


                connection.commit();

            } catch (SQLException e) {

                connection.rollback();

                throw e;
            }
        }
    }
}