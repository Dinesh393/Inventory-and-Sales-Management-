package com.inventory.main;

import com.inventory.model.Category;

public class Main {
	public static void main(String[] args) {
		Category category = new Category(1, "Electronics", "Electronic products");
		System.out.println(category);
	}
}
