package com.inventory.model;

import java.util.ArrayList;
import java.util.List;

public class Sale {

    private int saleId;
    private Customer customer;
    private String saleDate;
    private double totalAmount;
    private List<SaleItem> saleItems;

    public Sale(int saleId, Customer customer, String saleDate, double totalAmount) {
        super();
        this.saleId = saleId;
        this.customer = customer;
        this.saleDate = saleDate;
        this.totalAmount = totalAmount;
        this.saleItems = new ArrayList<>();
    }

    public int getSaleId() {
        return saleId;
    }

    public void setSaleId(int saleId) {
        this.saleId = saleId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(String saleDate) {
        this.saleDate = saleDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<SaleItem> getSaleItems() {
        return saleItems;
    }

    public void setSaleItems(List<SaleItem> saleItems) {
        this.saleItems = saleItems;
    }

    @Override
    public String toString() {
        return "Sale [saleId=" + saleId + ", customer=" + customer + ", saleDate=" + saleDate
                + ", totalAmount=" + totalAmount + ", saleItems=" + saleItems + "]";
    }
}