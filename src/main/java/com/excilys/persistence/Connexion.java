package com.excilys.persistence;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class Connexion implements AutoCloseable{

//	private static Properties connectionProperties;
//	
	private static Connection conn;
//	private static String url;
//	private static String userName;
//	private static String password;
	
	private static final String CONFIGURATION_LOCATION = "/DatabaseConnection.properties";
	
    private static HikariConfig config = new HikariConfig(CONFIGURATION_LOCATION);
    private static HikariDataSource dataSource = new HikariDataSource(config);
	
//	private Connexion(){
//		connectionProperties = new Properties();
//
//			try {
//				connectionProperties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(CONFIGURATION_LOCATION));
//					
//				url = connectionProperties.getProperty("url");
//				userName = connectionProperties.getProperty("userName");
//				password = connectionProperties.getProperty("password");
//
//				Class.forName(connectionProperties.getProperty("driver"));
//
//				conn = DriverManager.getConnection(url, userName, password);
//				
//			} catch (ClassNotFoundException ClassNFexc) {
//				ClassNFexc.printStackTrace();
//			} catch (IOException IOexc) {
//				IOexc.printStackTrace();
//			} catch (SQLException SQLexc) {
//				SQLexc.printStackTrace();
//			}
//
//	}
    private Connexion() { 
    }
	
	public static synchronized Connection getDbCon(){
		try {
			conn = dataSource.getConnection();
		} catch (SQLException SQLexc) {
			SQLexc.printStackTrace();
		}
		return conn;
	}
	
//	public static Connection getConn() {
//		return conn;
//	}
//
//	public static void setConn(Connection conn) {
//		Connexion.conn = conn;
//	}

	@Override
	public void close() throws Exception {
//		conn.close();
//		db.close();
		dataSource.close();
	}
}
