package com.excilys.configuration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {HibernateConfig.class})
public class SpringConfigTest {
 
	@Autowired
	ApplicationContext app;
	
	/**
	 * Test initialisation spring
	 */
    @Test
    public void springConfiguration() {
    }

}
