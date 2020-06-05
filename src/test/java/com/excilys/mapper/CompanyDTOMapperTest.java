package com.excilys.mapper;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.excilys.DAO.DAOcompany;
import com.excilys.dto.CompanyDTO;
import com.excilys.model.Company;
import com.excilys.persistence.Connexion;

@RunWith(MockitoJUnitRunner.class)
public class CompanyDTOMapperTest extends Mockito{

	CompanyDTO companyDto = new CompanyDTO.CompanyDTOBuilder()
			.setId("10")
			.setName("Digital Equipment Corporation")
			.build();

	Company company = new Company.CompanyBuilder().setId((long) 10).setName("Digital Equipment Corporation").build();

	Optional<Company> optComp = Optional.of(company);
	
	DAOcompany daoCompany = mock(DAOcompany.class);
	
	@Before
	public void setUp() {
		Connexion.getDbCon();
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testDtoToCompany() throws SQLException {
		Company companyRes = CompanyDTOMapper.dtoToCompany(companyDto);

		assertEquals(company.id, companyRes.id);
		assertEquals(company.name, companyRes.name);
	}

	@Test
	public void testCompanyToDto() {
		CompanyDTO companyRes = CompanyDTOMapper.companyToDto(company);

		assertEquals(companyDto.id, companyRes.id);
		assertEquals(companyDto.name, companyRes.name);
	}
}
