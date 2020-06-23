package com.excilys.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.excilys.dto.ComputerDTO;
import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.service.CompanyService;

@Component
public class ComputerDTOMapper {
	CompanyService companyService;
	
	public ComputerDTOMapper(CompanyService companyService) {
		this.companyService = companyService;
	}

	public Computer dtoToComputer(ComputerDTO computerDto){
		Company company = new Company.CompanyBuilder().setId(Long.parseLong(computerDto.getCompanyId())).setName(computerDto.getCompanyName()).build();
		Computer computer = new Computer.ComputerBuilder()
				.setName(computerDto.getName())
				.setIntroduced(DateMapper.stringToDate(computerDto.getIntroduced()))
				.setDiscontinued(DateMapper.stringToDate(computerDto.getDiscontinued()))
				.setCompany(company)
				.build();
		return computer;
	}

	public ComputerDTO computerToDto(Computer computer) {

		ComputerDTO computerDTO = new ComputerDTO.ComputerDTOBuilder().setId(String.valueOf(computer.getId()))
				.setName(computer.getName())
				.setIntroduced(String.valueOf(computer.getIntroduced()))
				.setDiscontinued(String.valueOf(computer.getDiscontinued()))
				.setCompanyId(String.valueOf(computer.getCompany().getId()))
				.setCompanyName(String.valueOf(computer.getCompany().getName()))
				.build();
		return computerDTO;
	}
	
	public List<ComputerDTO> listComputerToDto(List<Computer> computerList){
		List<ComputerDTO> computerDTOList = new ArrayList<ComputerDTO>();
		
		for(int i=0; i<computerList.size(); i++) {
			computerDTOList.add(computerToDto(computerList.get(i)));
		}
		
		return computerDTOList;
	}
	
	public List<Computer> listDtoToComputer(List<ComputerDTO> computerDtoList){
		List<Computer> computerList = new ArrayList<Computer>();
		
		for(int i=0; i<computerList.size(); i++) {
			computerList.add(dtoToComputer(computerDtoList.get(i)));
		}
		
		return computerList;
	}
}
