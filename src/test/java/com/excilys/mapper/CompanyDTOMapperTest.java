package com.excilys.mapper;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.excilys.DAO.DAOcompany;
import com.excilys.dto.CompanyDTO;
import com.excilys.model.Company;
import com.excilys.persistence.MySQLConnect;

@RunWith(MockitoJUnitRunner.class)
public class CompanyDTOMapperTest {

	CompanyDTO companyDto = new CompanyDTO.CompanyDTOBuilder()
			.setId("10")
			.setName("Digital Equipment Corporation")
			.build();

	Company company = new Company.CompanyBuilder().setId((long) 10).setName("Digital Equipment Corporation").build();

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Mock
	DAOcompany daoCompany;
	
	@Test
	public void testDtoToCompany() throws SQLException {
		MySQLConnect.getDbCon();
		Company companyRes = CompanyDTOMapper.dtoToCompany(companyDto);

		assertEquals(company.id, companyRes.id);
		assertEquals(company.name, companyRes.name);
	}

	@Test
	public void testCompanyToDto() {
		MySQLConnect.getDbCon();
		CompanyDTO companyRes = CompanyDTOMapper.companyToDto(company);

		assertEquals(companyDto.id, companyRes.id);
		assertEquals(companyDto.name, companyRes.name);
	}
}
