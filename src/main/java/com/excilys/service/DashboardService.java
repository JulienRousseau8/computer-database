package com.excilys.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.excilys.DAO.DAOcomputer;
import com.excilys.dto.ComputerDTO;
import com.excilys.mapper.ComputerDTOMapper;
import com.excilys.model.Computer;
import com.excilys.model.Pagination;

@Service
public class DashboardService {
	private static Logger logger = LoggerFactory.getLogger(DashboardService.class);
	
	DAOcomputer daoComputer;
	CompanyService companyService;
	ComputerDTOMapper mapper = new ComputerDTOMapper(companyService);
	Validators validators = new Validators(companyService);
	
	public DashboardService(DAOcomputer daoComputer) {
		this.daoComputer = daoComputer;
	}

	public int countAllComputer() {
		return daoComputer.countAllComputer();
	}

	public List<Computer> getPageComputer(Pagination page) {
		return daoComputer.getPageComputers(page);
	}
	
	public List<Computer> getSearchComputersPage(String recherche, Pagination page){
		return daoComputer.getSearchComputersPage(recherche, page);
	}
	
	public List<Computer> getSearchComputers(String recherche){
		return daoComputer.getSearchComputers(recherche);
	}
	
	public void createComputer(ComputerDTO computerDto){
		boolean name = validators.verifierNom(computerDto);
		boolean date = validators.verifierDate(computerDto);
		if (name && date) {
			Computer computer = mapper.dtoToComputer(computerDto);
			daoComputer.createComputer(computer);
			logger.info(computer.toString());
		}
	}
	
	public List<Computer> getComputersOrdered(Pagination page, String orderBy, int direction){
		if(orderBy.equals("company")) {
			orderBy = "company.name";
			return daoComputer.getPageComputersOrdered(page, orderBy, direction);
		} else {
			orderBy = "computer." + orderBy;
			return daoComputer.getPageComputersOrdered(page, orderBy, direction);
		}
	}
}
