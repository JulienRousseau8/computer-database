package com.excilys.mapper;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.junit.Test;

import com.excilys.dto.CompanyDTO;
import com.excilys.model.Company;
import com.excilys.persistence.MySQLConnect;

public class CompanyDTOMapperTest {
	
	@Test
	public void testDtoToCompany() throws SQLException {
		MySQLConnect.getDbCon();
		CompanyDTO companyDto = new CompanyDTO.CompanyDTOBuilder()
				.setId("10")
				.setName("Digital Equipment Corporation")
				.build();
		Company companyRes = CompanyDTOMapper.dtoToCompany(companyDto);
		Company company = new Company.CompanyBuilder()
				.setId(10)
				.setName("Digital Equipment Corporation")
				.build();
		
		assertEquals(company.id, companyRes.id);
		assertEquals(company.name, companyRes.name);
	}
	
	@Test
	public void testCompanyToDto() {
		MySQLConnect.getDbCon();
		Company company = new Company.CompanyBuilder()
				.setId(10)
				.setName("Digital Equipment Corporation")
				.build();
		CompanyDTO companyRes = CompanyDTOMapper.companyToDto(company);
		CompanyDTO companyDto = new CompanyDTO.CompanyDTOBuilder()
				.setId("10")
				.setName("Digital Equipment Corporation")
				.build();
		assertEquals(companyDto.id, companyRes.id);
		assertEquals(companyDto.name, companyRes.name);
	}
}
