package com.excilys.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.context.AbstractContextLoaderInitializer;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

@Configuration
@PropertySource("classpath:DatabaseConnection.properties")
@ComponentScan({"com.excilys.DAO", "com.excilys.service", "com.excilys.controller", "com.excilys.mapper", "com.excilys.restController"})
public class SpringConfig extends AbstractContextLoaderInitializer{
	
	@Override
	protected WebApplicationContext createRootApplicationContext() {
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.register(SpringConfig.class, HibernateConfig.class, WebConfig.class, SecurityConfig.class, SpringSecurityInitializer.class);
		return context;
	}
}
