package com.excilys.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.excilys.dto.ComputerDTO;
import com.excilys.model.Company;
import com.excilys.model.Computer;

@Component
public class ComputerDTOMapper {

	public ComputerDTOMapper() {
	}

	public Computer dtoToComputer(ComputerDTO computerDto){
		Company company = new Company.CompanyBuilder()
				.setId(Long.parseLong(computerDto.getCompanyId()))
				.setName(computerDto.getCompanyName())
				.build();
		
		Computer computer = new Computer.ComputerBuilder()
				.setId(computerDto.getId() == null ? 0 : Long.parseLong(computerDto.getId()))
				.setName(computerDto.getName())
				.setIntroduced(DateMapper.stringToDate(computerDto.getIntroduced()))
				.setDiscontinued(DateMapper.stringToDate(computerDto.getDiscontinued()))
				.setCompany(company)
				.build();
		return computer;
	}

	public ComputerDTO computerToDto(Computer computer) {
		ComputerDTO computerDTO = new ComputerDTO.ComputerDTOBuilder()
				.setId(String.valueOf(computer.getId()))
				.setName(computer.getName())
				.setIntroduced(String.valueOf(computer.getIntroduced()))
				.setDiscontinued(String.valueOf(computer.getDiscontinued()))
				.setCompanyId(computer.getCompany() != null ? String.valueOf(computer.getCompany().getId()) : null)
				.setCompanyName(computer.getCompany() != null ? String.valueOf(computer.getCompany().getName()) : "null")
				.build();
		return computerDTO;
	}

	public List<ComputerDTO> listComputerToDto(List<Computer> computerList){
		return computerList.stream().map(computer -> computerToDto(computer)).collect(Collectors.toList());
	}

	public List<Computer> listDtoToComputer(List<ComputerDTO> computerDtoList){
		return computerDtoList.stream().map(computer -> dtoToComputer(computer)).collect(Collectors.toList());
	}
}
