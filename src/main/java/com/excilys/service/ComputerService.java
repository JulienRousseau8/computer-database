package com.excilys.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.excilys.DAO.DAOcomputer;
import com.excilys.dto.ComputerDTO;
import com.excilys.mapper.ComputerDTOMapper;
import com.excilys.model.Computer;

@Service
public class ComputerService {

	private static Logger logger = LoggerFactory.getLogger(ComputerService.class);
	CompanyService companyService;
	Validators validators = new Validators(companyService);
	ComputerDTOMapper mapper = new ComputerDTOMapper();
	DAOcomputer daoComputer;
	
	public ComputerService(DAOcomputer daoComputer) {
		this.daoComputer = daoComputer;
	}
	
	public List<Computer> getAllComputers(){
		return daoComputer.getComputers();
	}
	
	public List<Computer> getComputersByCompanyId(int id){
		return daoComputer.getComputersByCompanyId(id);
	}

	public Optional<Computer> getComputerById(String id){
		Optional<Computer> computer = daoComputer.getComputerById(Long.parseLong(id));
		if (!computer.isPresent()) {
			logger.info("Aucun ordinateur ne correspond à cet ID");
			System.out.println();
		}
		return computer;
	}

	public void createComputer(ComputerDTO computerDto){
		boolean name = validators.verifierNom(computerDto);
		boolean date = validators.verifierDate(computerDto);
		boolean comp = validators.verifierCompany(computerDto);
		if (name && date && comp) {
			Computer computer = mapper.dtoToComputer(computerDto);
			daoComputer.createComputer(computer);
			logger.info(computer.toString());
		}
	}
	
	public void updateComputer(ComputerDTO computerDto){
		ComputerDTO newComputerDto = new ComputerDTO.ComputerDTOBuilder().build();
		newComputerDto.setId(computerDto.getId());

		Optional<Computer> oldComputer = getComputerById(computerDto.getId());
		ComputerDTO oldComputerDto = mapper.computerToDto(oldComputer.get());

		updateName(computerDto, oldComputerDto, newComputerDto);
		updateIntroduced(computerDto, oldComputerDto, newComputerDto);
		updateDiscontinued(computerDto, oldComputerDto, newComputerDto);
		boolean date = validators.verifierDate(computerDto);
		updateCompany(computerDto, oldComputerDto, newComputerDto);

		logger.info(newComputerDto.toString());
		
		if (date) {
			Computer computer = mapper.dtoToComputer(newComputerDto);
			computer.setId(Long.parseLong(computerDto.getId()));
			daoComputer.updateComputer(computer);
		}
	}
	
	public void deleteComputer(long id){
		Optional<Computer> computer = daoComputer.getComputerById(id);
		if (computer.isPresent()) {
			daoComputer.deleteComputer(id);
			logger.info(computer.get().toString());
			logger.info("Ordinateur supprimé");
		} else {
			logger.info("Aucun ordinateur ne correspond à cet ID");
		}
	}
	
	private void updateName(ComputerDTO computerDto, ComputerDTO oldComputerDto, ComputerDTO newComputerDto) {
		if (computerDto.getName().isEmpty()) {
			oldComputerDto.setName(newComputerDto.getName());
		} else {
			newComputerDto.setName(computerDto.getName());
		}
	}
	
	private void updateIntroduced(ComputerDTO computerDto, ComputerDTO oldComputerDto, ComputerDTO newComputerDto) {
		if (computerDto.getIntroduced().isEmpty()) {
			oldComputerDto.setIntroduced(newComputerDto.getIntroduced());
		} else {
			newComputerDto.setIntroduced(computerDto.getIntroduced());
		}
	}
	
	private void updateDiscontinued(ComputerDTO computerDto, ComputerDTO oldComputerDto, ComputerDTO newComputerDto) {
		if (computerDto.getDiscontinued().isEmpty()) {
			oldComputerDto.setDiscontinued(newComputerDto.getDiscontinued());
		} else 
			newComputerDto.setDiscontinued(computerDto.getDiscontinued());
	}
	
	private void updateCompany(ComputerDTO computerDto, ComputerDTO oldComputerDto, ComputerDTO newComputerDto) {
		if (computerDto.getCompanyId().isEmpty()) {
			newComputerDto.setCompanyId(oldComputerDto.getCompanyId());
			newComputerDto.setCompanyName(oldComputerDto.getCompanyName());
		} else {
			newComputerDto.setCompanyId(computerDto.getCompanyId());
			newComputerDto.setCompanyName(computerDto.getCompanyName());	
		}
	}
}
