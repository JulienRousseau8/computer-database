package com.excilys.service;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.excilys.DAO.DAOcompany;
import com.excilys.DAO.DAOcomputer;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = { SpringConfig.class, HibernateConfig.class})
public class ComputerServiceTest {

	@Mock
	DAOcompany daoCompany;
	@Mock
	DAOcomputer daoComputer;
	
	@Before
	public void init() {
	}

	//@Test
	
	
}
