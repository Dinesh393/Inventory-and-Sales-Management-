package com.inventory.repository;
import com.inventory.model.Supplier;
import com.inventory.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierRepository {
	public void addSupplier(Supplier supplier) throws SQLException{
		String sql = "INSERT INTO supplier(supplierName, companyName, phone, email, address) VALUES (?, ?, ?, ?, ?)";
		try(Connection connection = DatabaseConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql)){
			statement.setString(1, supplier.getSupplierName());
			statement.setString(2, supplier.getCompanyName());
			statement.setString(3, supplier.getPhone());
			statement.setString(4, supplier.getEmail());
			statement.setString(5, supplier.getAddress());
			
			statement.executeUpdate();
		}
	}
	
	public List<Supplier> findAllSuppliers() throws SQLException{
		String sql = "SELECT * FROM supplier";
		List<Supplier> suppliers= new ArrayList<>();
		try(Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql);
				ResultSet resultSet = statement.executeQuery()){
			while(resultSet.next()) {
				int supplierId = resultSet.getInt("supplierId");
				String supplierName = resultSet.getString("supplierName");
				String companyName = resultSet.getString("companyName");
				String phone = resultSet.getString("phone");
				String email = resultSet.getString("email");
				String address = resultSet.getString("address");
				Supplier supplier = new Supplier(supplierId, supplierName, companyName, phone, email, address);
				suppliers.add(supplier);
			}
		}
		return suppliers;
	}
	
	public Supplier findByName(String supplierName) throws SQLException{
		String sql = "SELECT * FROM supplier WHERE supplierName = ?";
		try(Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, supplierName);
			try(ResultSet resultSet = statement.executeQuery()){
				if(resultSet.next()) {
					int supplierId = resultSet.getInt("supplierId");
					String name = resultSet.getString("supplierName");
					String companyName = resultSet.getString("companyName");
					String phone = resultSet.getString("phone");
					String email = resultSet.getString("email");
					String address = resultSet.getString("address");
					return new Supplier(supplierId, name, companyName, phone, email, address);
				}
			}
		}
		return null;
	}
	
	public Supplier findById(int supplierId) throws SQLException{
		String sql = "SELECT * FROM supplier WHERE supplierId = ?";
		try(Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, supplierId);
			try(ResultSet resultSet = statement.executeQuery()){
				if(resultSet.next()) {
					int id = resultSet.getInt("supplierId");
					String supplierName = resultSet.getString("supplierName");
					String companyName = resultSet.getString("companyName");
					String phone = resultSet.getString("phone");
					String email = resultSet.getString("email");
					String address = resultSet.getString("address");
					return new Supplier(id, supplierName, companyName, phone, email, address);
				}
			}
		}
		return null;
	}
	
	public void updateSupplier(Supplier supplier) throws SQLException{
		String sql = "UPDATE supplier SET supplierName=?, companyName=?, phone=?, email=?, address=? WHERE supplierId=?";
		try(Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, supplier.getSupplierName());
			statement.setString(2, supplier.getCompanyName());
			statement.setString(3, supplier.getPhone());
			statement.setString(4, supplier.getEmail());
			statement.setString(5, supplier.getAddress());
			statement.setInt(6, supplier.getSupplierId());
			
			statement.executeUpdate();
			
		}
	}
	
	public void deleteSupplier(int supplierId) throws SQLException{
		String sql = "DELETE FROM supplier WHERE supplierId = ?";
		try(Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)){
			statement.setInt(1, supplierId);
			statement.executeUpdate();
		}
	}
}











