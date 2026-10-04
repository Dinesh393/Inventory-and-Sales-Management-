package com.inventory.service;

import com.inventory.model.Product;
import com.inventory.model.Sale;
import com.inventory.model.SaleItem;
import com.inventory.repository.CustomerRepository;
import com.inventory.repository.ProductRepository;
import com.inventory.repository.SaleRepository;
import com.inventory.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class SaleService {

    public void addSale(Sale sale) throws SQLException {

        if (sale == null) return;

        if (sale.getCustomer() == null) return;

        if (sale.getSaleDate() == null) return;

        if (sale.getSaleDate().isBlank()) return;

        if (sale.getSaleItems() == null) return;

        if (sale.getSaleItems().isEmpty()) return;


        CustomerRepository customerRepository =
                new CustomerRepository();

        if (customerRepository.findById(
                sale.getCustomer().getCustomerId()) == null) {
            return;
        }


        double saleTotal = 0;

        ProductRepository productRepository =
                new ProductRepository();


        for (SaleItem saleItem : sale.getSaleItems()) {

            if (saleItem == null) return;

            if (saleItem.getProduct() == null) return;

            if (saleItem.getQuantity() <= 0) return;

            if (saleItem.getUnitPrice() <= 0) return;


            Product product =
                    productRepository.findById(
                            saleItem.getProduct().getProductId());

            if (product == null) return;


            if (saleItem.getQuantity() > product.getStockQuantity()) {
                return;
            }


            double itemTotal =
                    saleItem.getQuantity()
                    * saleItem.getUnitPrice();

            saleItem.setTotalPrice(itemTotal);

            saleTotal += itemTotal;
        }


        sale.setTotalAmount(saleTotal);


        SaleRepository saleRepository =
                new SaleRepository();


        try (Connection connection =
                     DatabaseConnection.getConnection()) {

            try {

                connection.setAutoCommit(false);


                int saleId =
                        saleRepository.addSale(
                                sale,
                                connection);


                if (saleId <= 0) {
                    connection.rollback();
                    return;
                }


                for (SaleItem saleItem : sale.getSaleItems()) {

                    saleRepository.addSaleItem(
                            saleItem,
                            saleId,
                            connection);


                    saleRepository.decreaseProductStock(
                            saleItem.getProduct().getProductId(),
                            saleItem.getQuantity(),
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