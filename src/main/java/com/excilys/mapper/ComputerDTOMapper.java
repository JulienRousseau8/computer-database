package com.excilys.mapper;

import java.sql.SQLException;

import com.excilys.dto.CompanyDTO;
import com.excilys.dto.ComputerDTO;
import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.service.ConvertDate;

public class ComputerDTOMapper {

	public static Computer dtoToComputer(ComputerDTO computerDto) throws SQLException {
		Company company = CompanyDTOMapper.dtoToCompany(computerDto.company);
		Computer computer = new Computer.ComputerBuilder().setName(computerDto.name)
				.setIntroduced(ConvertDate.convert(computerDto.introduced))
				.setDiscontinued(ConvertDate.convert(computerDto.discontinued)).setCompany(company).build();
		return computer;
	}

	public static ComputerDTO computerToDto(Computer computer) {
		CompanyDTO companyDTO = CompanyDTOMapper.companyToDto(computer.company);

		ComputerDTO computerDTO = new ComputerDTO.ComputerDTOBuilder().setId(String.valueOf(computer.id))
				.setName(computer.name).setIntroduced(String.valueOf(computer.introduced))
				.setDiscontinued(String.valueOf(computer.discontinued)).setCompany(companyDTO).build();
		return computerDTO;
	}
}
