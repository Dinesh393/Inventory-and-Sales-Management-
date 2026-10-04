package com.inventory.service;
import com.inventory.repository.CategoryRepository;
import com.inventory.repository.ProductRepository;
import java.sql.SQLException;
import java.util.List;

import com.inventory.model.Category;
import com.inventory.model.Product;

public class ProductService {
	public void addProduct(Product product) throws SQLException{
		if(product == null) return;
		if(product.getProductName()==null) return;
		if(product.getProductName().isBlank()) return;
		if (product.getCategory() == null) return;

        if (product.getPurchasePrice() <= 0) return;
        if (product.getSellingPrice() <= 0) return;

        if (product.getStockQuantity() < 0) return;
        if (product.getMinimumStockLevel() < 0) return;
		
        CategoryRepository categoryRepository = new CategoryRepository();
        
        if (categoryRepository.findById(product.getCategory().getCategoryId()) == null) {
            return;
        }
		ProductRepository repository = new ProductRepository();
		Product existingProduct =
                repository.findByName(product.getProductName());

        if (existingProduct != null) {
            return;
        }
        
		repository.addProduct(product);
	}
	
	public List<Product> findAllProducts() throws SQLException{
		ProductRepository repository = new ProductRepository();
		return repository.findAllProducts();
	}
	
	public Product findById(int productId) throws SQLException{
		ProductRepository repository = new ProductRepository();
		if(productId <= 0) return null;
		return repository.findById(productId);
	}
	
	public Product findByName(String productName) throws SQLException{
		if(productName == null) return null;
		if(productName.isBlank()) return null;
		ProductRepository repository = new ProductRepository();

		return repository.findByName(productName);
	}
	
	public void updateProduct(Product product) throws SQLException {

        if (product == null) return;

        if (product.getProductId() <= 0) return;

        if (product.getProductName() == null) return;
        if (product.getProductName().isBlank()) return;

        if (product.getCategory() == null) return;

        if (product.getPurchasePrice() <= 0) return;
        if (product.getSellingPrice() <= 0) return;

        if (product.getStockQuantity() < 0) return;
        if (product.getMinimumStockLevel() < 0) return;

        CategoryRepository categoryRepository =
                new CategoryRepository();

        if (categoryRepository.findById(
                product.getCategory().getCategoryId()) == null) {
            return;
        }

        ProductRepository repository = new ProductRepository();

        Product existingProduct =
                repository.findById(product.getProductId());

        if (existingProduct == null) {
            return;
        }

        Product productWithSameName =
                repository.findByName(product.getProductName());

        if (productWithSameName != null &&
            productWithSameName.getProductId() != product.getProductId()) {
            return;
        }

        repository.updateProduct(product);
    }

	
	public void deleteProduct(int productId) throws SQLException{
		if(productId <= 0) return;
		ProductRepository repository = new ProductRepository();
		Product exisitngProduct = repository.findById(productId);
		if(exisitngProduct == null) return;
		repository.deleteProduct(productId);
	}
}
