package com.excilys.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.AbstractContextLoaderInitializer;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

@Configuration
@ComponentScan({"com.excilys.DAO", "com.excilys.service", "com.excilys.servlets", "com.excilys.persistence", "com.excilys.mapper", "com.excilys.ui"})
public class SpringConfig extends AbstractContextLoaderInitializer{
	
	@Override
	protected WebApplicationContext createRootApplicationContext() {
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.register(SpringConfig.class);
		return context;
	}
}
