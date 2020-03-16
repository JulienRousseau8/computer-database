package com.excilys.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

import com.excilys.DAO.DAOcomputer;
import com.excilys.model.Computer;
import com.excilys.model.Pagination;

public class ComputerService {

	public Optional<Computer> getComputerById(int id) throws SQLException {
		return DAOcomputer.getInstance().getComputerById(id);
	}
	
	public void createComputer(Computer computer) throws SQLException {
		DAOcomputer.getInstance().createComputer(computer);
	}
	
	public void updateComputer(Computer computer) throws SQLException {
		DAOcomputer.getInstance().updateComputer(computer);
	}
	
	public void deleteComputer(int id) throws SQLException {
		DAOcomputer.getInstance().deleteComputer(id);
	}
	
	public int countAllComputer() {
		return DAOcomputer.getInstance().countAllComputer();
	}
	
	public ArrayList<Computer> getPageComputer(Pagination page){
		return DAOcomputer.getInstance().getPageComputers(page);
	}
}
