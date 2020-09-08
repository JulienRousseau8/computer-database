package com.excilys.mapper;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;

import com.excilys.dto.CompanyDTO;
import com.excilys.model.Company;

public class CompanyDTOMapperTest{

	CompanyDTOMapper mapper = new CompanyDTOMapper();
	
	CompanyDTO companyDto = new CompanyDTO.Builder()
			.setId("10")
			.setName("Digital Equipment Corporation")
			.build();

	Company company = new Company.CompanyBuilder().setId((long) 10).setName("Digital Equipment Corporation").build();
	Optional<Company> optComp = Optional.of(company);
	
	List<CompanyDTO> companiesDtoList = new ArrayList<CompanyDTO>();
	List<Company> companiesList = new ArrayList<Company>();
	
	@Test
	public void dtoToCompany() {
		Company companyRes = mapper.dtoToCompany(companyDto);
		assertEquals(company, companyRes);
	}

	@Test
	public void companyToDto() {
		CompanyDTO companyRes = mapper.companyToDto(company);
		assertEquals(companyDto, companyRes);
	}
	
	@Test
	public void listDtoToCompany() {
		companiesDtoList.add(companyDto);
		companiesList.add(company);
		List<Company> listCompanies = mapper.listDtoToCompany(companiesDtoList);
		assertEquals(listCompanies, companiesList);
	}
	
	@Test
	public void listCompanyToDto() {
		companiesDtoList.add(companyDto);
		companiesList.add(company);
		List<CompanyDTO> listDTOCompanies = mapper.listCompanyToDto(companiesList);
		assertEquals(listDTOCompanies, companiesDtoList);
	}
}
