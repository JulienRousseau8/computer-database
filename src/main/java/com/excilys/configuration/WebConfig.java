package com.excilys.configuration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
public class WebConfig implements WebApplicationInitializer, WebMvcConfigurer{

	@Bean
	public ViewResolver getViewLocation() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry
		.addResourceHandler("/resources/**")
		.addResourceLocations("/resources/"); 
	}

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext webContext = new AnnotationConfigWebApplicationContext();
		webContext.register(WebConfig.class, SpringConfig.class, HibernateConfig.class);
		webContext.setServletContext(servletContext);

		DispatcherServlet dispatcherServlet = new DispatcherServlet(webContext);
		ServletRegistration.Dynamic appServlet = servletContext.addServlet("Dashboard", dispatcherServlet);
		appServlet.setLoadOnStartup(1);
		appServlet.addMapping("/");


	}


}
