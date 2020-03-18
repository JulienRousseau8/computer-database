package com.excilys.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.DAO.DAOcomputer;
import com.excilys.dto.ComputerDTO;
import com.excilys.mapper.CompanyDTOMapper;
import com.excilys.mapper.ComputerDTOMapper;
import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.model.Pagination;

public class ComputerService {

	private static Logger logger = LoggerFactory.getLogger(ComputerService.class);
	CompanyService companyService = new CompanyService();

	public ArrayList<Computer> getAllComputers() throws SQLException{
		return DAOcomputer.getInstance().getComputers();
	}

	public Optional<Computer> getComputerById(String id) throws SQLException {
		Optional<Computer> computer = DAOcomputer.getInstance().getComputerById(Long.parseLong(id));
		if(!computer.isPresent()) {

			logger.info("Aucun ordinateur ne correspond à cet ID");
			System.out.println();
		}
		return computer;
	}

	public void createComputer(ComputerDTO computerDto) throws SQLException {
		boolean name = false;
		boolean ordreDate = false;
		boolean comp = false;

		if(computerDto.name.isEmpty()) {
			logger.info("Nom requis");
		} else {
			name = true;
		}

		boolean dateIntroduced = Validators.verifierDateUtilisateurSaisie(computerDto.introduced);
		boolean dateDiscontinued = Validators.verifierDateUtilisateurSaisie(computerDto.discontinued);
		if(dateIntroduced && dateDiscontinued) {
			ordreDate = Validators.verifierDateOrdre(computerDto.introduced, computerDto.discontinued);
			if(!ordreDate) {
				logger.info("Date non valide !");
			}
		} 
		if(computerDto.company.id.isEmpty()) {
			logger.info("Id de la compagnie requis");
		} 
		else if(!Validators.verifierIdCompany(computerDto.company.id)){

		} else comp = true;

		if(name && dateIntroduced && dateDiscontinued && ordreDate && comp) {
			Computer computer = ComputerDTOMapper.dtoToComputer(computerDto);
			DAOcomputer.getInstance().createComputer(computer);
		}
	}

	public void updateComputer(ComputerDTO computerDto) throws SQLException {
		ComputerDTO newComputerDto = new ComputerDTO.ComputerDTOBuilder().build();
		boolean ordreDate = false;
		newComputerDto.setId(computerDto.id);

		Optional<Computer> oldComputer = getComputerById(computerDto.id);
		ComputerDTO oldComputerDto = ComputerDTOMapper.computerToDto(oldComputer.get());

		if(computerDto.name.isEmpty()) {
			newComputerDto.name = oldComputerDto.name;
		}else {
			newComputerDto.setName(computerDto.name);
		}

		if(computerDto.introduced.isEmpty()) {
			newComputerDto.introduced = oldComputerDto.introduced;
		}else {
			newComputerDto.setIntroduced(computerDto.introduced);
		}

		if(computerDto.discontinued.isEmpty()) {
			newComputerDto.discontinued = oldComputerDto.discontinued;
		}else {
			newComputerDto.setIntroduced(computerDto.discontinued);
		}

		boolean dateIntroduced = Validators.verifierDateUtilisateurSaisie(computerDto.introduced);
		boolean dateDiscontinued = Validators.verifierDateUtilisateurSaisie(computerDto.discontinued);
		if(dateIntroduced && dateDiscontinued) {
			ordreDate = Validators.verifierDateOrdre(computerDto.introduced, computerDto.discontinued);
			if(!ordreDate) {
				logger.info("Date non valide !");
			}
		} 

		if(computerDto.company.id.isEmpty()) {
			newComputerDto.setCompany(oldComputerDto.company);
		} 
		else if(!Validators.verifierIdCompany(computerDto.company.id)){

		}else {
			Optional<Company> optionalCompany = companyService.getCompanyById(computerDto.company.id);
			newComputerDto.setCompany(CompanyDTOMapper.companyToDto(optionalCompany.get()));
		}
		System.out.println(newComputerDto.toString());
		if(dateIntroduced && dateDiscontinued && ordreDate) {
			Computer computer = ComputerDTOMapper.dtoToComputer(newComputerDto);
			computer.setId(Long.parseLong(computerDto.id));
			DAOcomputer.getInstance().updateComputer(computer);
		}
	}

	public void deleteComputer(int id) throws SQLException {
		Optional<Computer> computer = DAOcomputer.getInstance().getComputerById(id);
		if(computer.isPresent()) {
			DAOcomputer.getInstance().deleteComputer(id);
			logger.info(computer.get().toString());
			logger.info("Ordinateur supprimé");
		}
		else {
			logger.info("Aucun ordinateur ne correspond à cet ID");
		}
	}

	public int countAllComputer() {
		return DAOcomputer.getInstance().countAllComputer();
	}

	public ArrayList<Computer> getPageComputer(Pagination page){
		return DAOcomputer.getInstance().getPageComputers(page);
	}
}
