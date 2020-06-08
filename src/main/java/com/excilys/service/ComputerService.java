package com.excilys.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.DAO.DAOcomputer;
import com.excilys.dto.ComputerDTO;
import com.excilys.mapper.ComputerDTOMapper;
import com.excilys.model.Computer;
import com.excilys.model.Pagination;

public class ComputerService {

	private static Logger logger = LoggerFactory.getLogger(ComputerService.class);
	CompanyService companyService = new CompanyService();

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
		boolean name = verifierNom(computerDto);
		
		boolean date = verifierDate(computerDto);

		boolean comp = verifierCompany(computerDto);

		if (name && date && comp) {
			Computer computer = ComputerDTOMapper.dtoToComputer(computerDto);
			DAOcomputer.getInstance().createComputer(computer);
		}
	}
	
	public void updateComputer(ComputerDTO computerDto){
		ComputerDTO newComputerDto = new ComputerDTO.ComputerDTOBuilder().build();
		newComputerDto.setId(computerDto.id);

		Optional<Computer> oldComputer = getComputerById(computerDto.id);
		ComputerDTO oldComputerDto = ComputerDTOMapper.computerToDto(oldComputer.get());

		updateName(computerDto, oldComputerDto, newComputerDto);
		updateIntroduced(computerDto, oldComputerDto, newComputerDto);
		updateDiscontinued(computerDto, oldComputerDto, newComputerDto);
		boolean date = verifierDate(computerDto);
		updateCompany(computerDto, oldComputerDto, newComputerDto);

		logger.info(newComputerDto.toString());
		if (date) {
			Computer computer = ComputerDTOMapper.dtoToComputer(newComputerDto);
			computer.setId(Long.parseLong(computerDto.id));
			DAOcomputer.getInstance().updateComputer(computer);
		}
	}
	
	public void deleteComputer(int id){
		Optional<Computer> computer = DAOcomputer.getInstance().getComputerById(id);
		if (computer.isPresent()) {
			DAOcomputer.getInstance().deleteComputer(id);
			logger.info(computer.get().toString());
			logger.info("Ordinateur supprimé");
		} else {
			logger.info("Aucun ordinateur ne correspond à cet ID");
		}
	}

	public int countAllComputer() {
		return DAOcomputer.getInstance().countAllComputer();
	}

	public List<Computer> getPageComputer(Pagination page) {
		return DAOcomputer.getInstance().getPageComputers(page);
	}
	
	private boolean verifierDate(ComputerDTO computerDto) {
		boolean ordreDate = false;
		boolean dateIntroduced = Validators.verifierDateUtilisateurSaisie(computerDto.introduced);
		boolean dateDiscontinued = Validators.verifierDateUtilisateurSaisie(computerDto.discontinued);
		if (dateIntroduced && dateDiscontinued) {
			ordreDate = Validators.verifierDateOrdre(computerDto.introduced, computerDto.discontinued);
			if (!ordreDate) {
				logger.info("Date non valide !");
			}
		}
		if (dateIntroduced && dateDiscontinued && ordreDate) {
			return true;
		} else return false;
	}
	
	private boolean verifierCompany(ComputerDTO computerDto) {
		boolean comp = false;
		if (computerDto.companyId.isEmpty()) {
			logger.info("Id de la compagnie requis");
		} else if (!Validators.verifierIdCompany(computerDto.companyId)) {
		} else {
			comp = true;
		}
		return comp;
	}
	
	private boolean verifierNom(ComputerDTO computerDto) {
		boolean name = false;
		if (computerDto.name.isEmpty()) {
			logger.info("Nom requis");
		} else {
			name = true;
		}
		return name;
	}
	
	private void updateName(ComputerDTO computerDto, ComputerDTO oldComputerDto, ComputerDTO newComputerDto) {
		if (computerDto.name.isEmpty()) {
			newComputerDto.name = oldComputerDto.name;
		} else {
			newComputerDto.setName(computerDto.name);
		}
	}
	
	private void updateIntroduced(ComputerDTO computerDto, ComputerDTO oldComputerDto, ComputerDTO newComputerDto) {
		if (computerDto.introduced.isEmpty()) {
			newComputerDto.introduced = oldComputerDto.introduced;
		} else {
			newComputerDto.setIntroduced(computerDto.introduced);
		}
	}
	
	private void updateDiscontinued(ComputerDTO computerDto, ComputerDTO oldComputerDto, ComputerDTO newComputerDto) {
		if (computerDto.discontinued.isEmpty()) {
			newComputerDto.discontinued = oldComputerDto.discontinued;
		} else {
			newComputerDto.setIntroduced(computerDto.discontinued);
		}
	}
	
	private void updateCompany(ComputerDTO computerDto, ComputerDTO oldComputerDto, ComputerDTO newComputerDto) {
		if (computerDto.companyId.isEmpty()) {
			newComputerDto.setCompanyId(oldComputerDto.companyId);
		} else if (!Validators.verifierIdCompany(computerDto.companyId)) {
		} else {
			newComputerDto.setCompanyId(computerDto.companyId);
		}
	}
}
