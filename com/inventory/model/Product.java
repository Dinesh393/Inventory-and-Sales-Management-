package com.inventory.model;
public class Product {
	private int productId;
	private String productName;
	private Category category;
	private double purchasePrice;
	private double sellingPrice;
	private int stockQuantity;
	private int minimumStockLevel;
	public Product() {
		super();
	}
	public Product(int productId) {
		this();
		this.productId = productId;
	}
	public Product(int productId, String productName) {
		this(productId);
		this.productName = productName;
	}
	public Product(int productId, String productName, Category category) {
		this(productId, productName);
		this.category = category;
	}
	public Product(int productId, String productName, Category category, double purchasePrice) {
		this(productId, productName, category);
		this.purchasePrice = purchasePrice;
	}
	public Product(int productId, String productName, Category category, double purchasePrice, double sellingPrice) {
		this(productId, productName, category, purchasePrice);
		this.sellingPrice = sellingPrice;
	}
	public Product(int productId, String productName, Category category, double purchasePrice, double sellingPrice, int stockQuantity) {
		this(productId, productName, category, purchasePrice, sellingPrice);
		this.stockQuantity = stockQuantity;
	}
	public Product(int productId, String productName, Category category, double purchasePrice, double sellingPrice, int stockQuantity, int minimumStockLevel) {
		this(productId, productName, category, purchasePrice, sellingPrice, stockQuantity);
		this.minimumStockLevel = minimumStockLevel;
	}
	public Product(String productName, Category category, double purchasePrice,
            double sellingPrice, int stockQuantity, int minimumStockLevel) {
		 this.productName = productName;
		 this.category = category;
		 this.purchasePrice = purchasePrice;
		 this.sellingPrice = sellingPrice;
		 this.stockQuantity = stockQuantity;
		 this.minimumStockLevel = minimumStockLevel;
}
	
	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", category=" + category
				+ ", purchasePrice=" + purchasePrice + ", sellingPrice=" + sellingPrice + ", stockQuantity="
				+ stockQuantity + ", minimumStockLevel=" + minimumStockLevel + "]";
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public double getPurchasePrice() {
		return purchasePrice;
	}
	public void setPurchasePrice(double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}
	public double getSellingPrice() {
		return sellingPrice;
	}
	public void setSellingPrice(double sellingPrice) {
		this.sellingPrice = sellingPrice;
	}
	public int getStockQuantity() {
		return stockQuantity;
	}
	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}
	public int getMinimumStockLevel() {
		return minimumStockLevel;
	}
	public void setMinimumStockLevel(int minimumStockLevel) {
		this.minimumStockLevel = minimumStockLevel;
	}
}
