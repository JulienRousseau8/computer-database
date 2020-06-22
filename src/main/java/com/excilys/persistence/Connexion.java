package com.excilys.persistence;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@PropertySource("classpath:/DatabaseConnection.properties")
public class Connexion implements AutoCloseable{

	private static final Logger LOGGER = LoggerFactory.getLogger(Connexion.class);

	private static HikariConfig hikariConfig;
	private static HikariDataSource dataSource;
	private static Connection conn;

	@Bean
	public static DataSource hikariDataSource() {
		hikariConfig = new HikariConfig("/DatabaseConnection.properties");
		dataSource = new HikariDataSource(hikariConfig);
		return dataSource;
	}
	
	public Connexion() {
	}

	public Connection getConn() {
		try {
			if(conn == null) {
				conn = hikariDataSource().getConnection();
			}
			return conn;
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		}
		return conn;
	}

	@Override
	public void close() throws Exception {
		conn.close();
		dataSource.close();
	}
}
