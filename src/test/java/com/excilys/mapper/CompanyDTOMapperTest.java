package com.excilys.mapper;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.junit.Test;

import com.excilys.dto.CompanyDTO;
import com.excilys.model.Company;
import com.excilys.persistence.MySQLConnect;

public class CompanyDTOMapperTest {
	
	CompanyDTO companyDto = new CompanyDTO.CompanyDTOBuilder()
			.setId("10")
			.setName("Digital Equipment Corporation")
			.build();
	
	Company company = new Company.CompanyBuilder()
			.setId((long)10)
			.setName("Digital Equipment Corporation")
			.build();
	
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
