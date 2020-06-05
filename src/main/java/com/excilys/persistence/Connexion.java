package com.excilys.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class Connexion implements AutoCloseable{

	private static Properties connectionProperties;
	
	public static Connection conn;
	public static Connexion db;
	private static String url;
	private static String userName;
	private static String password;
	private static final String CONFIGURATION_LOCATION = "DatabaseConnection.properties";
	
	private Connexion() {
		connectionProperties = new Properties();
		try {
			connectionProperties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(CONFIGURATION_LOCATION));
				
			url = connectionProperties.getProperty("url");
			userName = connectionProperties.getProperty("userName");
			password = connectionProperties.getProperty("password");
			
			Class.forName(connectionProperties.getProperty("driver"));
			
			conn = DriverManager.getConnection(url, userName, password);
		}
		catch (Exception e3) {
			e3.printStackTrace();
		}
	}
	
	public static synchronized Connexion getDbCon() {
		if (db == null) {
			db = new Connexion();
		}
		return db;
	}
	
	@Override
	public void close() throws Exception {
		conn.close();
		db.close();
	}
}
