package com.inventory.model;

public class PurchaseItem {
	private int purchaseItemId;
	private Product product;
	private int quantity;
	private double unitPrice;
	private double totalPrice;
	public PurchaseItem(int purchaseItemId, Product product, int quantity, double unitPrice, double totalPrice) {
		super();
		this.purchaseItemId = purchaseItemId;
		this.product = product;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
		this.totalPrice = totalPrice;
	}
	@Override
	public String toString() {
		return "PurchaseItem [purchaseItemId=" + purchaseItemId + ", product=" + product + ", quantity=" + quantity
				+ ", unitPrice=" + unitPrice + ", totalPrice=" + totalPrice + "]";
	}
	public int getPurchaseItemId() {
		return purchaseItemId;
	}
	public void setPurchaseItemId(int purchaseItemId) {
		this.purchaseItemId = purchaseItemId;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
}
