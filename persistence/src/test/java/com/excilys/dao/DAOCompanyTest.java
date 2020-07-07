package com.excilys.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.DAO.DAOcompany;
import com.excilys.configuration.HibernateConfig;
import com.excilys.configuration.SpringConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringConfig.class, HibernateConfig.class})
@Transactional
public class DAOCompanyTest {

	@Autowired
	private	DAOcompany daoCompany;
	
	@Test
	public void getById5Present() {
		assertTrue(daoCompany.getCompanyById(5).isPresent());
	}
	
	@Test
	public void getById5Empty() {
		assertFalse(daoCompany.getCompanyById(5).get().toString().isEmpty());
	}

	@Test
	public void getAllComputers() {
		assertEquals(daoCompany.getCompanies().size(), 20);
	}

}
