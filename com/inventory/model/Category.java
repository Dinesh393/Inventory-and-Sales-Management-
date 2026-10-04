package com.inventory.model;

public class Category {
	private int categoryId;
	private String categoryName;
	private String description;
	public Category() {
	    super();
	}

	public Category(int categoryId) {
	    this();
	    this.categoryId = categoryId;
	}

	public Category(int categoryId, String categoryName) {
	    this(categoryId);
	    this.categoryName = categoryName;
	}
	
	public Category(String categoryName, String description) {
		this.categoryName = categoryName;
		this.description = description;
	}
	public Category(int categoryId, String categoryName, String description) {
	    this(categoryId, categoryName);
	    this.description = description;
	}
	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId + ", categoryName=" + categoryName + ", description=" + description
				+ "]";
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
