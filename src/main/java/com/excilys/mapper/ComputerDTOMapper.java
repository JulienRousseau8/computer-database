package com.excilys.mapper;

import java.sql.SQLException;

import com.excilys.dto.*;
import com.excilys.model.*;
import com.excilys.service.ConvertDate;

public class ComputerDTOMapper {

	public Computer dtoToComputer(ComputerDTO computerDto) throws SQLException {
		Company company = CompanyDTOMapper.dtoToCompany(computerDto.company);
		
		Computer computer = new Computer.ComputerBuilder()
				.setId(Long.parseLong(computerDto.id))
				.setName(computerDto.name)
				.setIntroduced(ConvertDate.convert(computerDto.introduced))
				.setDiscontinued(ConvertDate.convert(computerDto.discontinued))
				.setCompany(company)
				.build();
		return computer;
	}
	
	public ComputerDTO ComputerToDto(Computer computer) {
		CompanyDTO companyDTO = CompanyDTOMapper.companyToDto(computer.company);
		
		ComputerDTO computerDTO = new ComputerDTO.ComputerDTOBuilder()
				.setId(String.valueOf(computer.id))
				.setName(computer.name)
				.setIntroduced(String.valueOf(computer.introduced))
				.setDiscontinued(String.valueOf(computer.discontinued))
				.setCompany(companyDTO)
				.build();
		return computerDTO;
	}
}
