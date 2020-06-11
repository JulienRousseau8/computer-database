package com.excilys.persistence;

import java.sql.Connection;
import java.sql.DriverManager;

public class H2Connect implements AutoCloseable {
	
	private static Connection conn;
	private static H2Connect db;
	private String url = "jdbc:h2:mem:computer-database-db;INIT=RUNSCRIPT FROM 'src/test/Ressources/H2-database-creation.sql'"; 
	private String driver = "org.h2.Driver";
	private String userName = "sa";
	private String password = "";

	private H2Connect() {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userName, password);
		} catch (Exception sqle) {
			sqle.printStackTrace();
		}
	}

	public static synchronized H2Connect getDbCon() {
		if (db == null) {
			db = new H2Connect();
		}
		return db;
	}

	@Override
	public void close() throws Exception {
		conn.close();
		db.close();
	}
}
