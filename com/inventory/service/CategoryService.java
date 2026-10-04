package com.inventory.service;
import java.sql.SQLException;
import java.util.List;

import com.inventory.model.Category;
import com.inventory.repository.CategoryRepository;
public class CategoryService {
	public void addCategory(Category category) throws SQLException {
		if (category == null) {
			return;
		}
		if(category.getCategoryName() == null) {
			return;
		}
		if(category.getCategoryName().isBlank()) {
			return;
		} 
		CategoryRepository repository = new CategoryRepository();
		Category existingCategory = repository.findByName(category.getCategoryName());
		if(existingCategory != null) {
			return;
		}
		repository.addCategory(category);
	}
	
	public List<Category> findAllCategories() throws SQLException{
		CategoryRepository repository = new CategoryRepository();
		return repository.findAllCategories();
	}
	
	
	public Category findById(int categoryId) throws SQLException{
		CategoryRepository repository = new CategoryRepository();
		if(categoryId <= 0) {
			return null;
		}
		return repository.findById(categoryId);
	}
	
	
	public Category findByName(String categoryName) throws SQLException{
		CategoryRepository repository = new CategoryRepository();
		if(categoryName == null) {
			return null;
		}
		if(categoryName.isBlank()) {
			return null;
		}
		return repository.findByName(categoryName);
	}
	
	
	public void updateCategory(Category category) throws SQLException {
		if(category == null) return;
		if(category.getCategoryId() <= 0) return;
		if(category.getCategoryName() == null) return;
		if(category.getCategoryName().isBlank()) return;
		
		CategoryRepository repository = new CategoryRepository();
	    Category existingCategory = repository.findByName(category.getCategoryName());

	    if (existingCategory != null &&
	        existingCategory.getCategoryId() != category.getCategoryId()) {

	        return;
	    }

	    repository.updateCategory(category);
	}
	
	public void deleteCategory(int categoryId) throws SQLException{
		if(categoryId <= 0) return;
		CategoryRepository repository = new CategoryRepository();
		Category exisitngId = repository.findById(categoryId);
		if(exisitngId == null) return;
		repository.deleteCategory(categoryId);
	}
	
	
}