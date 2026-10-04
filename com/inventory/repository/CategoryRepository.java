package com.inventory.repository;

import java.util.List;
import java.util.ArrayList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.inventory.model.Category;
import com.inventory.util.DatabaseConnection;
public class CategoryRepository {
	public void addCategory(Category category) throws SQLException{
		String sql = "INSERT INTO category(categoryName, description) VALUES (?, ?)";
		try(Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)){
			
			statement.setString(1, category.getCategoryName());
			statement.setString(2, category.getDescription());
			
			statement.executeUpdate();
		}
	}
	
	public List<Category> findAllCategories() throws SQLException {
		String sql = "SELECT * FROM category";
		List<Category> categories = new ArrayList<>();
		try(Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql);
				ResultSet resultSet = statement.executeQuery()){
			while(resultSet.next()) {
				int categoryId = resultSet.getInt("categoryId");
				String categoryName = resultSet.getString("categoryName");
				String description = resultSet.getString("description");
				Category category = new Category(categoryId, categoryName, description);
				categories.add(category);
			}
		}
		return categories;
	}
	
	public Category findByName(String categoryName) throws SQLException {
		String sql = "SELECT * FROM category WHERE categoryName = ?";
		try(Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, categoryName);
			try(ResultSet resultSet = statement.executeQuery()){
				 if (resultSet.next()) {

		                int categoryId = resultSet.getInt("categoryId");
		                String name = resultSet.getString("categoryName");
		                String description = resultSet.getString("description");

		                return new Category(categoryId, name, description);
		            }
			}
		}
		return null;
	}
	
	public Category findById(int categoryId) throws SQLException {
		String sql = "SELECT * FROM category WHERE categoryId = ?";
		try(Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, categoryId);
			try(ResultSet resultSet = statement.executeQuery()){
				 if (resultSet.next()) {

		                int id = resultSet.getInt("categoryId");
		                String categoryName = resultSet.getString("categoryName");
		                String description = resultSet.getString("description");

		                return new Category(id, categoryName, description);
		            }
			}
		}
		return null;
	}
	
	
	public void updateCategory(Category category) throws SQLException {
		String sql = "UPDATE category SET categoryName = ?, description = ? WHERE categoryId = ?";
		try(Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, category.getCategoryName());
	        statement.setString(2, category.getDescription());
	        statement.setInt(3, category.getCategoryId());

	        statement.executeUpdate();
		}
	}
	public void deleteCategory(int categoryId) throws SQLException{
		String sql = "DELETE FROM category WHERE categoryId=?";
		try(Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, categoryId);
			statement.executeUpdate();
		}
	}
	
}



