package com.excilys.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.excilys.DAO.DAOcomputer;
import com.excilys.model.Computer;
import com.excilys.model.Pagination;

@Service
public class DashboardService {
	DAOcomputer daoComputer;
	
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
