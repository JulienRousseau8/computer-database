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

	private final String GETCOMPUTERS = "SELECT computer.id, computer.name, computer.introduced, "
			+ "computer.discontinued, computer.company_id, company.name "
			+ "FROM computer LEFT JOIN company ON company.id = company_id";
	private final  String GETCOMPUTERBYID = "SELECT computer.id,computer.name,computer.introduced,computer.discontinued,computer.company_id, company.name "
			+ "FROM computer " + "LEFT JOIN company " + "ON company.id = company_id " + "WHERE computer.id=?";
	private final String CREATECOMPUTER = "INSERT INTO computer (name, introduced, discontinued, company_id) VALUES (?, ?, ?, ?)";
	private final String UPDATECOMPUTER = "UPDATE computer SET  name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE Id = ?";
	private final String DELETECOMPUTER = "DELETE FROM computer WHERE id=?";
	private final String COUNTCOMPUTERS = "SELECT COUNT(id) AS rowcount FROM computer";
	private final String GETPAGECOMPUTERS = "SELECT computer.name, computer.id, computer.introduced, computer.discontinued, computer.company_id, company.name "
			+ "FROM computer AS computer " + "LEFT JOIN company AS company " + "ON company.id = computer.company_id "
			+ "LIMIT ?, ?";

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

		try (PreparedStatement getComputersStatement = Connexion.getDbCon().prepareStatement(GETCOMPUTERS)) {
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
		try (PreparedStatement getComputerByIdStatement = Connexion.getDbCon().prepareStatement(GETCOMPUTERBYID)) {
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
		try (PreparedStatement createComputerStatement = Connexion.getDbCon().prepareStatement(CREATECOMPUTER);) {
			createComputerStatement.setString(1, computer.getName());
			createComputerStatement.setDate(2, computer.getIntroduced() != null ? Date.valueOf(computer.getIntroduced()) : null);
			createComputerStatement.setDate(3, computer.getDiscontinued() != null ? Date.valueOf(computer.getDiscontinued()) : null);
			Company company = computer.getCompany();
			createComputerStatement.setLong(4, company.getId());
			createComputerStatement.executeUpdate();
		} catch (SQLException SQLexception) {
			SQLexception.printStackTrace();
		}
	}

	public void updateComputer(Computer computer){
		try (PreparedStatement updateComputerStatement = Connexion.getDbCon().prepareStatement(UPDATECOMPUTER)) {
			updateComputerStatement.setString(1, computer.getName());
			updateComputerStatement.setDate(2, computer.getIntroduced() != null ? Date.valueOf(computer.getIntroduced()) : null);
			updateComputerStatement.setDate(3, computer.getDiscontinued() != null ? Date.valueOf(computer.getDiscontinued()) : null);
			updateComputerStatement.setLong(4, computer.getCompany().getId());
			updateComputerStatement.setLong(5, computer.getId());
			updateComputerStatement.executeUpdate();
		} catch (SQLException SQLexception) {
			SQLexception.printStackTrace();
		}
	}

	public void deleteComputer(long id){
		try (PreparedStatement deleteComputerStatement = Connexion.getDbCon().prepareStatement(DELETECOMPUTER)) {
			deleteComputerStatement.setLong(1, id);
			deleteComputerStatement.executeUpdate();
		} catch (SQLException SQLexception) {
			SQLexception.printStackTrace();
		}
	}

	public int countAllComputer() {
		try (PreparedStatement countAllComputersStatement = Connexion.getDbCon().prepareStatement(COUNTCOMPUTERS)) {
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
		try (PreparedStatement getPageComputersStatement = Connexion.getDbCon().prepareStatement(GETPAGECOMPUTERS)) {
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
}