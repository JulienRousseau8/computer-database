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
		newComputerDto.setId(computerDto.getId());

		Optional<Computer> oldComputer = getComputerById(computerDto.getId());
		ComputerDTO oldComputerDto = ComputerDTOMapper.computerToDto(oldComputer.get());

		updateName(computerDto, oldComputerDto, newComputerDto);
		updateIntroduced(computerDto, oldComputerDto, newComputerDto);
		updateDiscontinued(computerDto, oldComputerDto, newComputerDto);
		boolean date = verifierDate(computerDto);
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

	public int countAllComputer() {
		return DAOcomputer.getInstance().countAllComputer();
	}

	public List<Computer> getPageComputer(Pagination page) {
		return DAOcomputer.getInstance().getPageComputers(page);
	}
	
	public List<Computer> getSearchComputersPage(String recherche, Pagination page){
		return DAOcomputer.getInstance().getSearchComputersPage(recherche, page);
	}
	
	public List<Computer> getSearchComputers(String recherche){
		return DAOcomputer.getInstance().getSearchComputers(recherche);
	}
	
	public List<Computer> getComputersOrdered(Pagination page, String orderBy){
		if(orderBy.equals("company")) {
			orderBy = "company.name";
			return DAOcomputer.getInstance().getPageComputersOrdered(page, orderBy);
		} else {
			orderBy = "computer." + orderBy;
			return DAOcomputer.getInstance().getPageComputersOrdered(page, orderBy);
		}
		
	}
	
	private boolean verifierDate(ComputerDTO computerDto) {
		boolean ordreDate = false;
		boolean dateIntroduced = Validators.verifierDateUtilisateurSaisie(computerDto.getIntroduced());
		boolean dateDiscontinued = Validators.verifierDateUtilisateurSaisie(computerDto.getDiscontinued());
		if (dateIntroduced && dateDiscontinued) {
			ordreDate = Validators.verifierDateOrdre(computerDto.getIntroduced(), computerDto.getDiscontinued());
			if (!ordreDate) {
				logger.info("Date non valide !");
			}
		}
		if (dateIntroduced && dateDiscontinued && ordreDate) {
			return true;
		} else return false;
	}
	
	private boolean verifierCompany(ComputerDTO computerDto) {
		boolean comp = true;
		if (computerDto.getCompanyId().isEmpty()) {
			comp = false;
			logger.info("Id de la compagnie requis");
		} else if (!Validators.verifierIdCompany(computerDto.getCompanyId())) {
			comp = false;
		}
		return comp;
	}
	
	private boolean verifierNom(ComputerDTO computerDto) {
		boolean name = true;
		if (computerDto.getName().isEmpty()) {
			name = false;
			logger.info("Nom requis");
		}
		return name;
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
