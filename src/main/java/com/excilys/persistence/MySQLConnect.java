package com.excilys.persistence;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySQLConnect implements AutoCloseable {

	public static Connection conn;
	public static MySQLConnect db;
	private String url = "jdbc:mysql://localhost:3306/computer-database-db";
	private String timeZoneErr = "? useUnicode=true & useJDBCCompliantTimezoneShift=true & useLegacyDatetimeCode=false & serverTimezone=UTC";
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String userName = "admincdb";
	private String password = "qwerty1234";

	private MySQLConnect() {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url+timeZoneErr, userName, password);
		} catch (Exception sqle) {
			sqle.printStackTrace();
		}
	}

	public static synchronized MySQLConnect getDbCon() {
		if (db == null) {
			db = new MySQLConnect();
		}
		return db;
	}

	@Override
	public void close() throws Exception {
		conn.close();
		db.close();
	}
}
