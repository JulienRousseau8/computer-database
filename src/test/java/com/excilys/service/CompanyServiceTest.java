package com.excilys.service;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.excilys.DAO.DAOcompany;
import com.excilys.model.Company;
import com.excilys.persistence.H2Connect;

@RunWith(MockitoJUnitRunner.class)
public class CompanyServiceTest extends Mockito {

	@Mock
	DAOcompany daoCompany;
	//DAOcompany mockDaoCompany = Mockito.mock(DAOcompany.class);
	
	
	CompanyService companyService = new CompanyService();
	String companyId = "10";

	Optional<Company> mockCompany = Optional.of(new Company
			.CompanyBuilder().setId((long)10).setName("Digital Equipment Corporation").build());
	
	
	@Before
	public void init() throws SQLException {
		H2Connect.getDbCon();
	}

	@Test
	public void testGetCompanyById() throws SQLException{
		Mockito.when(daoCompany.getCompanyById(10)).thenReturn(mockCompany);
		Company companyRes = companyService.getCompanyById(companyId).get();
		
		assertEquals(mockCompany.get().id, companyRes.id);
		assertEquals("Digital Equipment Corporation", companyRes.name);
	}

}
