package com.excilys.mapper;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.excilys.DAO.DAOcompany;
import com.excilys.dto.CompanyDTO;
import com.excilys.model.Company;

@RunWith(MockitoJUnitRunner.class)
public class CompanyDTOMapperTest extends Mockito{

	@Mock
	DAOcompany daoCompany;
	
	CompanyDTOMapper mapper = new CompanyDTOMapper(daoCompany);
	
	CompanyDTO companyDto = new CompanyDTO.CompanyDTOBuilder()
			.setId("10")
			.setName("Digital Equipment Corporation")
			.build();

	Company company = new Company.CompanyBuilder().setId((long) 10).setName("Digital Equipment Corporation").build();

	Optional<Company> optComp = Optional.of(company);
	
	@Before
	public void setUp() {
		
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testDtoToCompany() throws SQLException {
		Company companyRes = mapper.dtoToCompany(companyDto);

		assertEquals(company.getId(), companyRes.getId());
		assertEquals(company.getName(), companyRes.getName());
	}

	@Test
	public void testCompanyToDto() {
		CompanyDTO companyRes = mapper.companyToDto(company);

		assertEquals(companyDto.getId(), companyRes.getId());
		assertEquals(companyDto.getName(), companyRes.getName());
	}
}
