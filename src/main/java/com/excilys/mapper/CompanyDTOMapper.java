package com.excilys.mapper;

import java.sql.SQLException;
import java.util.Optional;

import com.excilys.DAO.DAOcompany;
import com.excilys.dto.CompanyDTO;
import com.excilys.model.Company;

public class CompanyDTOMapper {

	public static Company dtoToCompany(CompanyDTO companyDto) throws SQLException {
		long companyId = Long.parseLong(companyDto.id);
		Optional<Company> optCompany = DAOcompany.getInstance().getCompanyById(companyId);

		Company company = new Company.CompanyBuilder().setId(companyId).setName(optCompany.get().name).build();

		return company;
	}

	public static CompanyDTO companyToDto(Company company) {
		CompanyDTO companyDto = new CompanyDTO.CompanyDTOBuilder().setId(String.valueOf(company.id))
				.setName(company.name).build();

		return companyDto;
	}

}
