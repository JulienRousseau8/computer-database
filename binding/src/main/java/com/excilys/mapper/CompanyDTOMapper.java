package com.excilys.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.excilys.dto.CompanyDTO;
import com.excilys.model.Company;

@Component
public class CompanyDTOMapper {

	public CompanyDTOMapper() {
	}

	public Company dtoToCompany(CompanyDTO companyDto){
		Company company = new Company.CompanyBuilder()
				.setId(Long.parseLong(companyDto.getId()))
				.setName(companyDto.getName())
				.build();
		return company;
	}

	public CompanyDTO companyToDto(Company company) {
		CompanyDTO companyDto = new CompanyDTO.Builder()
				.setId(String.valueOf(company.getId()))
				.setName(company.getName()).build();
		return companyDto;
	}
	
	public List<CompanyDTO> listCompanyToDto(List<Company> companyList){
		return companyList.stream().map(company -> companyToDto(company)).collect(Collectors.toList());
	}
	
	public List<Company> listDtoToCompany(List<CompanyDTO> companyDtoList){
		return companyDtoList.stream().map(company -> dtoToCompany(company)).collect(Collectors.toList());
	}

}
