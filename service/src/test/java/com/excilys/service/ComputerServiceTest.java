package com.excilys.service;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.excilys.DAO.DAOcompany;
import com.excilys.DAO.DAOcomputer;
import com.excilys.configuration.HibernateConfig;
import com.excilys.configuration.SpringConfig;
import com.excilys.model.Computer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { SpringConfig.class, HibernateConfig.class})
public class ComputerServiceTest extends Mockito{

	@Mock
	DAOcompany daoCompany;
	@Mock
	DAOcomputer daoComputer;
	@InjectMocks
	ComputerService computerService;
	
	Computer mockComputer = new Computer.Builder()
			.setId((long)1)
			.setName("MacBook Pro 15.4 inch")
			.build();
	
	@Before
	public void init() {
		 MockitoAnnotations.initMocks(this); 
	}
	
	@Test
	public void getCompanyById(){
		when(daoComputer.getComputerById(3)).thenReturn(Optional.of(mockComputer));
		Computer computerRes = computerService.getComputerById("3").get();
		assertEquals(mockComputer, computerRes);
	}
	
	
}
