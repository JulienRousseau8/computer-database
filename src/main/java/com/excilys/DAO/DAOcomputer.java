package com.excilys.DAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.excilys.mapper.Mapper;
import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.model.Pagination;
import com.excilys.persistence.Connexion;

public class DAOcomputer {

	public static DAOcomputer daoComputer;

	private DAOcomputer() {
	}

	public static DAOcomputer getInstance() {
		if (daoComputer == null) {
			daoComputer = new DAOcomputer();
		}
		return daoComputer;
	}
	
	public List<Computer> getComputers(){
		ResultSet allComputerRes;
		Optional<Computer> computer;
		List<Computer> listComputers = new ArrayList<Computer>();

		try (PreparedStatement getComputersStatement = Connexion.getConn().prepareStatement(SQLRequest.GETCOMPUTERS.getQuery())) {
			allComputerRes = getComputersStatement.executeQuery();
			while (allComputerRes.next()) {
				computer = Mapper.computerMapper(allComputerRes);
				listComputers.add(computer.get());
			}
		} catch (SQLException SQLexception) {
			SQLexception.printStackTrace();
		}
		return listComputers;
	}

	public Optional<Computer> getComputerById(long id){
		ResultSet computerRes;
		try (PreparedStatement getComputerByIdStatement = Connexion.getConn().prepareStatement(SQLRequest.GETCOMPUTERBYID.getQuery())) {
			getComputerByIdStatement.setLong(1, id);
			computerRes = getComputerByIdStatement.executeQuery();
			if (computerRes.next()) {
				Optional<Computer> computer = Mapper.computerMapper(computerRes);
				return computer;
			}
		} catch (SQLException SQLexception) {
			SQLexception.printStackTrace();
		}
		return Optional.empty();
	}

	public void createComputer(Computer computer){
		try (PreparedStatement createComputerStatement = Connexion.getConn().prepareStatement(SQLRequest.CREATECOMPUTER.getQuery());) {
			createComputerStatement.setString(1, computer.getName());
			createComputerStatement.setDate(2, computer.getIntroduced() != null 
					? Date.valueOf(computer.getIntroduced()) : null);
			createComputerStatement.setDate(3, computer.getDiscontinued() != null 
					? Date.valueOf(computer.getDiscontinued()) : null);
			Company company = computer.getCompany();
			createComputerStatement.setLong(4, company.getId());
			createComputerStatement.executeUpdate();
		} catch (SQLException SQLexception) {
			SQLexception.printStackTrace();
		}
	}

	public void updateComputer(Computer computer){
		try (PreparedStatement updateComputerStatement = Connexion.getConn().prepareStatement(SQLRequest.UPDATECOMPUTER.getQuery())) {
			updateComputerStatement.setString(1, computer.getName());
			updateComputerStatement.setDate(2, computer.getIntroduced() != null 
					? Date.valueOf(computer.getIntroduced()) : null);
			updateComputerStatement.setDate(3, computer.getDiscontinued() != null 
					? Date.valueOf(computer.getDiscontinued()) : null);
			updateComputerStatement.setLong(4, computer.getCompany().getId());
			updateComputerStatement.setLong(5, computer.getId());
			updateComputerStatement.executeUpdate();
		} catch (SQLException SQLexception) {
			SQLexception.printStackTrace();
		}
	}

	public void deleteComputer(long id){
		try (PreparedStatement deleteComputerStatement = Connexion.getConn().prepareStatement(SQLRequest.DELETECOMPUTER.getQuery())) {
			deleteComputerStatement.setLong(1, id);
			deleteComputerStatement.executeUpdate();
		} catch (SQLException SQLexception) {
			SQLexception.printStackTrace();
		}
	}

	public int countAllComputer() {
		try (PreparedStatement countAllComputersStatement = Connexion.getConn().prepareStatement(SQLRequest.COUNTCOMPUTERS.getQuery())) {
			ResultSet res1 = countAllComputersStatement.executeQuery();
			if (res1.next()) {
				return res1.getInt("rowcount");
			}
		} catch (SQLException SQLexception) {
			SQLexception.printStackTrace();
		}
		return 0;
	}

	public List<Computer> getPageComputers(Pagination page) {
		List<Computer> computerPages = new ArrayList<Computer>();
		Optional<Computer> computer;
		try (PreparedStatement getPageComputersStatement = Connexion.getConn().prepareStatement(SQLRequest.GETPAGECOMPUTERS.getQuery())) {
			getPageComputersStatement.setInt(1, page.getPageNum() * page.getPageTaille());
			getPageComputersStatement.setInt(2, page.getPageTaille());
			ResultSet computerResPages = getPageComputersStatement.executeQuery();
			while (computerResPages.next()) {
				computer = Mapper.computerMapper(computerResPages);
				computerPages.add(computer.get());
			}
			
		} catch (SQLException SQLexception) {
			SQLexception.printStackTrace();
		}
		return computerPages;
	}
	
	public List<Computer> getSearchComputersPage(String recherche, Pagination page){
		List<Computer> computerSearch = new ArrayList<Computer>();
		Optional<Computer> computer;
		try (PreparedStatement getSearchComputersStatement = Connexion.getConn().prepareStatement(SQLRequest.SEARCHCOMPUTERPAGE.getQuery())) {
			getSearchComputersStatement.setString(1, "%" +recherche+ "%");
			getSearchComputersStatement.setString(2, "%" +recherche+ "%");
			getSearchComputersStatement.setInt(3, page.getPageNum() * page.getPageTaille());
			getSearchComputersStatement.setInt(4, page.getPageTaille());
			ResultSet computerResSearch = getSearchComputersStatement.executeQuery();
			while (computerResSearch.next()) {
				computer = Mapper.computerMapper(computerResSearch);
				computerSearch.add(computer.get());
			}
			
		} catch (SQLException SQLexception) {
			SQLexception.printStackTrace();
		}
		return computerSearch;
	}
	
	public List<Computer> getSearchComputers(String recherche){
		List<Computer> computerSearch = new ArrayList<Computer>();
		Optional<Computer> computer;
		try (PreparedStatement getSearchComputersStatement = Connexion.getConn().prepareStatement(SQLRequest.SEARCHCOMPUTER.getQuery())) {
			getSearchComputersStatement.setString(1, "%" +recherche+ "%");
			getSearchComputersStatement.setString(2, "%" +recherche+ "%");
			ResultSet computerResSearch = getSearchComputersStatement.executeQuery();
			while (computerResSearch.next()) {
				computer = Mapper.computerMapper(computerResSearch);
				computerSearch.add(computer.get());
			}
			
		} catch (SQLException SQLexception) {
			SQLexception.printStackTrace();
		}
		return computerSearch;
	}
	
	public List<Computer> getPageComputersOrdered(Pagination page, String orderBy) {
		List<Computer> computerPages = new ArrayList<Computer>();
		Optional<Computer> computer;
		final String GETPAGECOMPUTERORDERBYNAME ="SELECT computer.id, computer.name, computer.introduced , computer.discontinued , company_id, company.name "
				+ "FROM computer LEFT JOIN company ON company_id = company.id ORDER BY " + orderBy + " LIMIT ?,?;";
		try (PreparedStatement getPageComputersStatement = Connexion.getConn().prepareStatement(GETPAGECOMPUTERORDERBYNAME)) {
			getPageComputersStatement.setInt(1, page.getPageNum() * page.getPageTaille());
			getPageComputersStatement.setInt(2, page.getPageTaille());
			System.out.println(getPageComputersStatement);
			ResultSet computerResPages = getPageComputersStatement.executeQuery();
			while (computerResPages.next()) {
				computer = Mapper.computerMapper(computerResPages);
				computerPages.add(computer.get());
			}
			
		} catch (SQLException SQLexception) {
			SQLexception.printStackTrace();
		}
		return computerPages;
	}
}