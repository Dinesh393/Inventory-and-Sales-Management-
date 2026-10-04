package com.inventory.main;
import java.sql.SQLException;


import com.inventory.service.ProductService;
public class DatabaseTest {
	public static void main(String[] args) throws SQLException{
//	    
		ProductService service = new ProductService();
		service.deleteProduct(-3);
	}
}
