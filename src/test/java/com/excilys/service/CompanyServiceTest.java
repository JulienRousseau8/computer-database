package com.excilys.service;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.Optional;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.excilys.DAO.DAOcompany;
import com.excilys.model.Company;

@RunWith(MockitoJUnitRunner.class)
public class CompanyServiceTest extends Mockito {

	@Mock
	DAOcompany daoCompany;
	//DAOcompany mockDaoCompany = Mockito.mock(DAOcompany.class);
	@Mock
	ComputerService computerService;
	
	DataSource connexion;
	
	CompanyService companyService = new CompanyService(computerService, daoCompany);
	String companyId = "10";

	Optional<Company> mockCompany = Optional.of(new Company
			.CompanyBuilder().setId((long)10).setName("Digital Equipment Corporation").build());
	
	
	@Before
	public void init() throws SQLException {
	}

	@Test
	public void testGetCompanyById() throws SQLException{
		Mockito.when(daoCompany.getCompanyById(10)).thenReturn(mockCompany);
		Company companyRes = companyService.getCompanyById(companyId).get();
		
		assertEquals(mockCompany.get().getId(), companyRes.getId());
		assertEquals("Digital Equipment Corporation", companyRes.getName());
	}

}
