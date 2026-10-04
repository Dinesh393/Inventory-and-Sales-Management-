package com.inventory.repository;
import java.util.List;
import java.util.ArrayList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.inventory.model.Category;
import com.inventory.model.Product;
import com.inventory.util.DatabaseConnection;
public class ProductRepository {
	public void addProduct(Product product) throws SQLException {
		String sql = "INSERT INTO product(productName, categoryId, purchasePrice, sellingPrice, stockQuantity, minimumStockLevel) VALUES (?, ?, ?, ?, ?,?)";
		try(Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)){
			
			statement.setString(1, product.getProductName());
			statement.setInt(2, product.getCategory().getCategoryId());
			statement.setDouble(3, product.getPurchasePrice());
			statement.setDouble(4, product.getSellingPrice());
			statement.setInt(5, product.getStockQuantity());
			statement.setInt(6, product.getMinimumStockLevel());
			
			statement.executeUpdate();
		}
	}
	public List<Product> findAllProducts() throws SQLException {

	    String sql = """
	            SELECT p.productId, p.productName,
	                   c.categoryId, c.categoryName, c.description,
	                   p.purchasePrice, p.sellingPrice,
	                   p.stockQuantity, p.minimumStockLevel
	            FROM product p
	            JOIN category c ON p.categoryId = c.categoryId
	            """;

	    List<Product> products = new ArrayList<>();

	    try (Connection connection = DatabaseConnection.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql);
	         ResultSet resultSet = statement.executeQuery()) {

	        while (resultSet.next()) {

	            int productId = resultSet.getInt("productId");
	            String productName = resultSet.getString("productName");

	            int categoryId = resultSet.getInt("categoryId");
	            String categoryName = resultSet.getString("categoryName");
	            String description = resultSet.getString("description");

	            double purchasePrice = resultSet.getDouble("purchasePrice");
	            double sellingPrice = resultSet.getDouble("sellingPrice");
	            int stockQuantity = resultSet.getInt("stockQuantity");
	            int minimumStockLevel = resultSet.getInt("minimumStockLevel");

	            Category category = new Category(categoryId, categoryName, description);

	            Product product = new Product(
	                    productId,
	                    productName,
	                    category,
	                    purchasePrice,
	                    sellingPrice,
	                    stockQuantity,
	                    minimumStockLevel
	            );

	            products.add(product);
	        }
	    }

	    return products;
	}
	public Product findById(int productId) throws SQLException{
		String sql = """
	            SELECT p.productId, p.productName,
                c.categoryId, c.categoryName, c.description,
                p.purchasePrice, p.sellingPrice,
                p.stockQuantity, p.minimumStockLevel
         FROM product p
         JOIN category c ON p.categoryId = c.categoryId
         WHERE p.productId = ?
         """;
		try(Connection connection = DatabaseConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql)){
			statement.setInt(1, productId);
			try(ResultSet resultSet = statement.executeQuery()){
				if(resultSet.next()) {
					int id = resultSet.getInt("productId");
		            String productName = resultSet.getString("productName");

		            int categoryId = resultSet.getInt("categoryId");
		            String categoryName = resultSet.getString("categoryName");
		            String description = resultSet.getString("description");

		            double purchasePrice = resultSet.getDouble("purchasePrice");
		            double sellingPrice = resultSet.getDouble("sellingPrice");
		            int stockQuantity = resultSet.getInt("stockQuantity");
		            int minimumStockLevel = resultSet.getInt("minimumStockLevel");
		            
		            Category category = new Category(
		                    categoryId,
		                    categoryName,
		                    description
		            );

		            Product product = new Product(
		                    id,
		                    productName,
		                    category,
		                    purchasePrice,
		                    sellingPrice,
		                    stockQuantity,
		                    minimumStockLevel
		            );

		            return product;
		            
				}
			}
		}
		return null;
	}
	
	public Product findByName(String productName) throws SQLException{
		String sql = """
	            SELECT p.productId, p.productName,
                c.categoryId, c.categoryName, c.description,
                p.purchasePrice, p.sellingPrice,
                p.stockQuantity, p.minimumStockLevel
         FROM product p
         JOIN category c ON p.categoryId = c.categoryId
         WHERE p.productName = ?
         """;
		try(Connection connection = DatabaseConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql)){
			statement.setString(1, productName);
			try(ResultSet resultSet = statement.executeQuery()){
				if(resultSet.next()) {
					int productId = resultSet.getInt("productId");
		            String name = resultSet.getString("productName");

		            int categoryId = resultSet.getInt("categoryId");
		            String categoryName = resultSet.getString("categoryName");
		            String description = resultSet.getString("description");

		            double purchasePrice = resultSet.getDouble("purchasePrice");
		            double sellingPrice = resultSet.getDouble("sellingPrice");
		            int stockQuantity = resultSet.getInt("stockQuantity");
		            int minimumStockLevel = resultSet.getInt("minimumStockLevel");
		            
		            Category category = new Category(
		                    categoryId,
		                    categoryName,
		                    description
		            );

		            Product product = new Product(
		                    productId,
		                    name,
		                    category,
		                    purchasePrice,
		                    sellingPrice,
		                    stockQuantity,
		                    minimumStockLevel
		            );

		            return product;
		            
				}
			}
		}
		return null;
	}
	public void updateProduct(Product product) throws SQLException{
		String sql = "UPDATE product SET productName=?, categoryId=?, purchasePrice=?, sellingPrice=?, stockQuantity=?, minimumStockLevel=? WHERE productId = ?";
		try(Connection connection = DatabaseConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, product.getProductName());
			statement.setInt(2, product.getCategory().getCategoryId());
			statement.setDouble(3, product.getPurchasePrice());
			statement.setDouble(4, product.getSellingPrice());
			statement.setInt(5, product.getStockQuantity());
			statement.setInt(6, product.getMinimumStockLevel());
			statement.setInt(7, product.getProductId());
			
			statement.executeUpdate();
		}
	}
	
	public void deleteProduct(int productId) throws SQLException{
		String sql = "DELETE FROM product WHERE productId = ?";
		try(Connection connection = DatabaseConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, productId);
			statement.executeUpdate();
		}
	}
}
