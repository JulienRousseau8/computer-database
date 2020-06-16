package com.excilys.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.DAO.DAOcomputer;
import com.excilys.dto.ComputerDTO;
import com.excilys.mapper.ComputerDTOMapper;
import com.excilys.model.Computer;

public class ComputerService {

	private static Logger logger = LoggerFactory.getLogger(ComputerService.class);

	public List<Computer> getAllComputers(){
		return DAOcomputer.getInstance().getComputers();
	}

	public Optional<Computer> getComputerById(String id){
		Optional<Computer> computer = DAOcomputer.getInstance().getComputerById(Long.parseLong(id));
		if (!computer.isPresent()) {
			logger.info("Aucun ordinateur ne correspond à cet ID");
			System.out.println();
		}
		return computer;
	}

	public void createComputer(ComputerDTO computerDto){
		boolean name = Validators.verifierNom(computerDto);
		boolean date = Validators.verifierDate(computerDto);
		boolean comp = Validators.verifierCompany(computerDto);

		if (name && date && comp) {
			Computer computer = ComputerDTOMapper.dtoToComputer(computerDto);
			DAOcomputer.getInstance().createComputer(computer);
			logger.info(computer.toString());
		}
	}
	
	public void updateComputer(ComputerDTO computerDto){
		ComputerDTO newComputerDto = new ComputerDTO.ComputerDTOBuilder().build();
		newComputerDto.setId(computerDto.getId());

		Optional<Computer> oldComputer = getComputerById(computerDto.getId());
		ComputerDTO oldComputerDto = ComputerDTOMapper.computerToDto(oldComputer.get());

		updateName(computerDto, oldComputerDto, newComputerDto);
		updateIntroduced(computerDto, oldComputerDto, newComputerDto);
		updateDiscontinued(computerDto, oldComputerDto, newComputerDto);
		boolean date = Validators.verifierDate(computerDto);
		updateCompany(computerDto, oldComputerDto, newComputerDto);

		logger.info(newComputerDto.toString());
		
		if (date) {
			Computer computer = ComputerDTOMapper.dtoToComputer(newComputerDto);
			computer.setId(Long.parseLong(computerDto.getId()));
			DAOcomputer.getInstance().updateComputer(computer);
		}
	}
	
	public void deleteComputer(long id){
		Optional<Computer> computer = DAOcomputer.getInstance().getComputerById(id);
		if (computer.isPresent()) {
			DAOcomputer.getInstance().deleteComputer(id);
			logger.info(computer.get().toString());
			logger.info("Ordinateur supprimé");
		} else {
			logger.info("Aucun ordinateur ne correspond à cet ID");
		}
	}
	
	private void updateName(ComputerDTO computerDto, ComputerDTO oldComputerDto, ComputerDTO newComputerDto) {
		if (computerDto.getName().isEmpty()) {
			String name = newComputerDto.getName();
			oldComputerDto.setName(name);
		} else {
			newComputerDto.setName(computerDto.getName());
		}
	}
	
	private void updateIntroduced(ComputerDTO computerDto, ComputerDTO oldComputerDto, ComputerDTO newComputerDto) {
		if (computerDto.getIntroduced().isEmpty()) {
			String introduced = newComputerDto.getIntroduced();
			oldComputerDto.setIntroduced(introduced);
		} else {
			newComputerDto.setIntroduced(computerDto.getIntroduced());
		}
	}
	
	private void updateDiscontinued(ComputerDTO computerDto, ComputerDTO oldComputerDto, ComputerDTO newComputerDto) {
		if (computerDto.getDiscontinued().isEmpty()) {
			String discontinued = newComputerDto.getDiscontinued();
			oldComputerDto.setDiscontinued(discontinued);
		} else 
			newComputerDto.setDiscontinued(computerDto.getDiscontinued());
	}
	
	private void updateCompany(ComputerDTO computerDto, ComputerDTO oldComputerDto, ComputerDTO newComputerDto) {
		if (computerDto.getCompanyId().isEmpty()) {
			newComputerDto.setCompanyId(oldComputerDto.getCompanyId());
			newComputerDto.setCompanyName(oldComputerDto.getCompanyName());
		} else if (!Validators.verifierIdCompany(computerDto.getCompanyId())) {
			return;
		} else {
			newComputerDto.setCompanyId(computerDto.getCompanyId());
			newComputerDto.setCompanyName(computerDto.getCompanyName());
			
		}
	}
}
