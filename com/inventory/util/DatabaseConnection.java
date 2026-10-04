package com.inventory.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DatabaseConnection {
	private static final String URL =
	        "jdbc:mysql://127.0.0.1:portnumber/database_name";
	private static final String USER = "your mysql username";
	private static final String PASSWORD = "your my sql passwprd";
	
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, USER, PASSWORD);
	}
}
