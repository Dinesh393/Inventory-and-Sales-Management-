package com.inventory.model;

import java.util.ArrayList;
import java.util.List;

public class Purchase {

    private int purchaseId;
    private Supplier supplier;
    private String purchaseDate;
    private double totalAmount;
    private List<PurchaseItem> purchaseItems;

    public Purchase(int purchaseId, Supplier supplier, String purchaseDate, double totalAmount) {
        super();
        this.purchaseId = purchaseId;
        this.supplier = supplier;
        this.purchaseDate = purchaseDate;
        this.totalAmount = totalAmount;
        this.purchaseItems = new ArrayList<>();
    }

    public int getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(int purchaseId) {
        this.purchaseId = purchaseId;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<PurchaseItem> getPurchaseItems() {
        return purchaseItems;
    }

    public void setPurchaseItems(List<PurchaseItem> purchaseItems) {
        this.purchaseItems = purchaseItems;
    }

    @Override
    public String toString() {
        return "Purchase [purchaseId=" + purchaseId + ", supplier=" + supplier + ", purchaseDate=" + purchaseDate
                + ", totalAmount=" + totalAmount + ", purchaseItems=" + purchaseItems + "]";
    }
}