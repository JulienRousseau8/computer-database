package com.excilys.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.DAO.DAOcomputer;
import com.excilys.model.Computer;
import com.excilys.model.Pagination;

public class ComputerService {

	private static Logger logger = LoggerFactory.getLogger(ComputerService.class);
	
	public ArrayList<Computer> getAllComputers() throws SQLException{
		return DAOcomputer.getInstance().getComputers();
	}
	
	public Optional<Computer> getComputerById(int id) throws SQLException {
		Optional<Computer> computer = DAOcomputer.getInstance().getComputerById(id);
		if(computer.isPresent()) {
			System.out.println(computer.get().toString());
		}
		else {
			logger.info("Aucun ordinateur ne correspond à cet ID");
		}
		return DAOcomputer.getInstance().getComputerById(id);
	}
	
	public void createComputer(Computer computer) throws SQLException {
		DAOcomputer.getInstance().createComputer(computer);
	}
	
	public void updateComputer(Computer computer) throws SQLException {
		DAOcomputer.getInstance().updateComputer(computer);
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
