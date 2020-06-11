package com.excilys.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.excilys.DAO.DAOcompany;
import com.excilys.dto.CompanyDTO;
import com.excilys.model.Company;

public class CompanyDTOMapper {

	public static Company dtoToCompany(CompanyDTO companyDto){
		long companyId = Long.parseLong(companyDto.getId());
		Optional<Company> optCompany = DAOcompany.getInstance().getCompanyById(companyId);

		Company company = new Company.CompanyBuilder().setId(companyId).setName(optCompany.get().getName()).build();

		return company;
	}

	public static CompanyDTO companyToDto(Company company) {
		CompanyDTO companyDto = new CompanyDTO.CompanyDTOBuilder().setId(String.valueOf(company.getId()))
				.setName(company.getName()).build();

		return companyDto;
	}
	
	public static List<CompanyDTO> listCompanyToDto(List<Company> companyList){
		List<CompanyDTO> companyDTOList = new ArrayList<CompanyDTO>();
		
		for(int i=0; i<companyList.size(); i++) {
			companyDTOList.add(companyToDto(companyList.get(i)));
		}
		
		return companyDTOList;
	}
	
	public static List<Company> listDtoToCompany(List<CompanyDTO> companyDtoList){
		List<Company> companyList = new ArrayList<Company>();
		
		for(int i=0; i<companyList.size(); i++) {
			companyList.add(dtoToCompany(companyDtoList.get(i)));
		}
		
		return companyList;
	}

}
