package com.excilys.DAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

	public ArrayList<Computer> getComputers() throws SQLException {
		ResultSet allComputerRes;
		Optional<Computer> computer;
		ArrayList<Computer> listComputers = new ArrayList<Computer>();

		try (PreparedStatement st = Connexion.conn.prepareStatement(GETCOMPUTERS)) {
			allComputerRes = st.executeQuery();
			while (allComputerRes.next()) {
				computer = Mapper.computerMapper(allComputerRes);
				listComputers.add(computer.get());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listComputers;
	}

	public Optional<Computer> getComputerById(long id) throws SQLException {
		ResultSet computerRes;
		try (PreparedStatement st = Connexion.conn.prepareStatement(GETCOMPUTERBYID)) {
			st.setLong(1, id);
			computerRes = st.executeQuery();
			if (computerRes.first()) {
				Optional<Computer> computer = Mapper.computerMapper(computerRes);
				return computer;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}

	public void createComputer(Computer computer) throws SQLException {
		try (PreparedStatement st = Connexion.conn.prepareStatement(CREATECOMPUTER);) {
			st.setString(1, computer.getName());
			st.setDate(2, computer.getIntroduced() != null ? Date.valueOf(computer.getIntroduced()) : null);
			st.setDate(3, computer.getDiscontinued() != null ? Date.valueOf(computer.getDiscontinued()) : null);
			Company company = computer.getCompany();
			st.setLong(4, company.id);
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateComputer(Computer computer) throws SQLException {
		try (PreparedStatement st = Connexion.conn.prepareStatement(UPDATECOMPUTER)) {
			st.setString(1, computer.getName());
			st.setDate(2, computer.getIntroduced() != null ? Date.valueOf(computer.getIntroduced()) : null);
			st.setDate(3, computer.getDiscontinued() != null ? Date.valueOf(computer.getDiscontinued()) : null);
			st.setLong(4, computer.company.id);
			st.setLong(5, computer.getId());
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteComputer(long id) throws SQLException {
		try (PreparedStatement st = Connexion.conn.prepareStatement(DELETECOMPUTER)) {
			st.setLong(1, id);
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int countAllComputer() {
		try (PreparedStatement st = Connexion.conn.prepareStatement(COUNTCOMPUTERS)) {
			ResultSet res1 = st.executeQuery();
			if (res1.next()) {
				return res1.getInt("rowcount");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public ArrayList<Computer> getPageComputers(Pagination page) {
		ArrayList<Computer> computerPages = new ArrayList<Computer>();
		Optional<Computer> computer;
		try (PreparedStatement st = Connexion.conn.prepareStatement(GETPAGECOMPUTERS)) {
			st.setInt(1, page.getPageNum() * page.getPageTaille());
			st.setInt(2, page.getPageTaille());
			ResultSet computerResPages = st.executeQuery();
			while (computerResPages.next()) {
				computer = Mapper.computerMapper(computerResPages);
				computerPages.add(computer.get());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return computerPages;
	}

}