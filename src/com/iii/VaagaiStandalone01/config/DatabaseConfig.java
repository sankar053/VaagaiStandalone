package com.iii.VaagaiStandalone01.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {
	
	public static String url = "jdbc:mysql://localhost:3306/vaagai";
    public static String username = "root";
    public static String password = "password";
    public static String jdbcDriver = "com.mysql.cj.jdbc.Driver";
    
    public static Connection getDBConnection() throws ClassNotFoundException, SQLException {
    	
    	Class.forName(jdbcDriver);
    	Connection connection = DriverManager.getConnection(url, username, password);
    	System.out.println("DB Connected!");
		return connection;
    	
    }

}
