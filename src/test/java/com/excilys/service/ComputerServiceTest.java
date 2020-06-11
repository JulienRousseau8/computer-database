package com.excilys.service;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.excilys.DAO.DAOcompany;
import com.excilys.DAO.DAOcomputer;
import com.excilys.persistence.MySQLConnect;

@RunWith(MockitoJUnitRunner.class)
public class ComputerServiceTest {

	@Mock
	DAOcompany daoCompany;
	@Mock
	DAOcomputer daoComputer;
	
	
	@Before
	public void init() {
		MySQLConnect.getDbCon();
	}

	//@Test
	
	
}
