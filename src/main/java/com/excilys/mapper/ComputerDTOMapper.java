package com.excilys.mapper;

import java.sql.SQLException;

import com.excilys.DAO.DAOcompany;
import com.excilys.dto.ComputerDTO;
import com.excilys.model.Computer;
import com.excilys.service.ConvertDate;

public class ComputerDTOMapper {

	public static Computer dtoToComputer(ComputerDTO computerDto) throws SQLException {
		Computer computer = new Computer.ComputerBuilder().setName(computerDto.name)
				.setIntroduced(ConvertDate.convert(computerDto.introduced))
				.setDiscontinued(ConvertDate.convert(computerDto.discontinued))
				.setCompany(DAOcompany.getInstance().getCompanyById(Long.parseLong(computerDto.companyId)).get())
				.build();
		return computer;
	}

	public static ComputerDTO computerToDto(Computer computer) {

		ComputerDTO computerDTO = new ComputerDTO.ComputerDTOBuilder().setId(String.valueOf(computer.id))
				.setName(computer.name).setIntroduced(String.valueOf(computer.introduced))
				.setDiscontinued(String.valueOf(computer.discontinued))
				.setCompanyId(String.valueOf(computer.company.id))
				.build();
		return computerDTO;
	}
}
