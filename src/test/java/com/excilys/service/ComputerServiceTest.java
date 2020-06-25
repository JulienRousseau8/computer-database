package com.excilys.service;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.excilys.DAO.DAOcompany;
import com.excilys.DAO.DAOcomputer;

@RunWith(MockitoJUnitRunner.class)
public class ComputerServiceTest {

	@Mock
	DAOcompany daoCompany;
	@Mock
	DAOcomputer daoComputer;
	DataSource connexion;
	
	@Before
	public void init() {
	}

	//@Test
	
	
}
