package com.loginscreen.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

	public static Connection getConnection() {
		
		Connection connection;
		
		Properties properties = new Properties();
		InputStream is = ConnectionFactory.class.getResourceAsStream("../resources/dbconnection.properties");
		
		try {
			
			if(is == null) {
				throw new FileNotFoundException();
			}
			
			properties.load(is);
			
			String url = properties.getProperty("url");
			String user = properties.getProperty("user");
			String password = properties.getProperty("password");
			
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			connection = DriverManager.getConnection(url, user, password);
			
		}catch(IOException | SQLException e) {
			System.out.println(e.getClass());
			throw new RuntimeException(e.getMessage());
		}
		
		return connection;
	}
}
