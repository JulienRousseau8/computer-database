package com.excilys.mapper;

import java.util.ArrayList;
import java.util.List;

import com.excilys.DAO.DAOcompany;
import com.excilys.dto.ComputerDTO;
import com.excilys.model.Computer;
import com.excilys.service.ConvertDate;

public class ComputerDTOMapper {

	public static Computer dtoToComputer(ComputerDTO computerDto){
		Computer computer = new Computer.ComputerBuilder()
				.setName(computerDto.getName())
				.setIntroduced(ConvertDate.convert(computerDto.getIntroduced()))
				.setDiscontinued(ConvertDate.convert(computerDto.getDiscontinued()))
				.setCompany(DAOcompany.getInstance().getCompanyById(Long.parseLong(computerDto.getCompanyId())).get())
				.build();
		return computer;
	}

	public static ComputerDTO computerToDto(Computer computer) {

		ComputerDTO computerDTO = new ComputerDTO.ComputerDTOBuilder().setId(String.valueOf(computer.getId()))
				.setName(computer.getName())
				.setIntroduced(String.valueOf(computer.getIntroduced()))
				.setDiscontinued(String.valueOf(computer.getDiscontinued()))
				.setCompanyId(String.valueOf(computer.getCompany().getId()))
				.setCompanyName(String.valueOf(computer.getCompany().getName()))
				.build();
		return computerDTO;
	}
	
	public static List<ComputerDTO> listComputerToDto(List<Computer> computerList){
		List<ComputerDTO> computerDTOList = new ArrayList<ComputerDTO>();
		
		for(int i=0; i<computerList.size(); i++) {
			computerDTOList.add(computerToDto(computerList.get(i)));
		}
		
		return computerDTOList;
	}
	
	public static List<Computer> listDtoToComputer(List<ComputerDTO> computerDtoList){
		List<Computer> computerList = new ArrayList<Computer>();
		
		for(int i=0; i<computerList.size(); i++) {
			computerList.add(dtoToComputer(computerDtoList.get(i)));
		}
		
		return computerList;
	}
}
