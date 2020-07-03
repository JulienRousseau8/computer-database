package com.excilys.service;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.excilys.DAO.DAOcompany;
import com.excilys.configuration.HibernateConfig;
import com.excilys.configuration.SpringConfig;
import com.excilys.model.Company;

@RunWith(MockitoJUnitRunner.class)
//@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { SpringConfig.class, HibernateConfig.class})
public class CompanyServiceTest extends Mockito {

	@Mock
	DAOcompany daoCompanyMock;
	@InjectMocks
	CompanyService companyService;
//	@Autowired
//	SessionFactory session;
	
	Company mockCompany = new Company.CompanyBuilder()
			.setId((long)3)
			.setName("RCA")
			.build();

//	@Before
//	public void init() {
//		 MockitoAnnotations.initMocks(this);
//		 
//	}
	
	@Test
	public void getCompanyById(){
		when(daoCompanyMock.getCompanyById(3)).thenReturn(Optional.of(mockCompany));
		Company companyRes = companyService.getCompanyById("3").get();
		assertEquals(mockCompany, companyRes);
	}

}
