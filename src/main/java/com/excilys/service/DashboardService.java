package com.excilys.service;

import java.util.List;

import com.excilys.DAO.DAOcomputer;
import com.excilys.model.Computer;
import com.excilys.model.Pagination;

public class DashboardService {

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
	
	public List<Computer> getComputersOrdered(Pagination page, String orderBy, int direction){
		if(orderBy.equals("company")) {
			orderBy = "company.name";
			return DAOcomputer.getInstance().getPageComputersOrdered(page, orderBy, direction);
		} else {
			orderBy = "computer." + orderBy;
			return DAOcomputer.getInstance().getPageComputersOrdered(page, orderBy, direction);
		}
		
	}
}
