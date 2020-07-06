package com.excilys.configuration;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EnableTransactionManagement
@PropertySource("classpath:DatabaseConnection.properties")
public class HibernateConfig {
	private final String DRIVER = "driverClassName";
	private final String URL = "jdbcUrl";
	private final String USER = "dataSource.user";
	private final String PASSWORD = "dataSource.password";

	@Autowired
	private Environment environment;

	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSourceHibernate(environment));
		sessionFactory.setPackagesToScan("com.excilys.model","com.excilys.DAO");

		return sessionFactory;
	}

	@Bean
	public DataSource dataSourceHibernate(Environment environment) {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(environment.getRequiredProperty(DRIVER));
		dataSource.setUrl(environment.getRequiredProperty(URL));
		dataSource.setUsername(environment.getRequiredProperty(USER));
		dataSource.setPassword(environment.getRequiredProperty(PASSWORD));
		return dataSource;
	}

	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(
			SessionFactory sessionFactory) {

		HibernateTransactionManager txManager
		= new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory);

		return txManager;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}
}
