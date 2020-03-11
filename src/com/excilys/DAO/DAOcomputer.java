package com.excilys.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.excilys.mapper.Mapper;
import com.excilys.model.*;
import com.excilys.persistence.MySQLConnect;

public class DAOcomputer {

	public static DAOcomputer daoComputer;
	
	private final static String getComputers = "SELECT id,name,introduced,discontinued,company_id FROM computer";
	private final static String getComputerById = "SELECT id,name,introduced,discontinued,company_id FROM computer WHERE id=?";
	private final static String createComputer = "INSERT INTO computer (name, introduced, discontinued, company_id) VALUES (?, ?, ?, ?)";
	private final static String updateComputer = "UPDATE computer SET  name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE Id = ?";
	private final static String deleteComputer = "DELETE FROM computer WHERE id=?";

	private DAOcomputer() {
	}
	
	public static DAOcomputer getInstance() {
		if (daoComputer == null) {
			daoComputer = new DAOcomputer();
		}
		return daoComputer;
	}
	
	public ArrayList<Computer> getComputers() throws SQLException{
		ResultSet allComputerRes;
		ArrayList<Computer> listComputers = new ArrayList<Computer>();

		try (PreparedStatement st = MySQLConnect.conn.prepareStatement(getComputers)){
			allComputerRes = st.executeQuery();
			listComputers = Mapper.computerListeMapper(allComputerRes);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listComputers;
	}

	public Computer getComputerById(long id) throws SQLException {
		ResultSet ComputerRes;
		try (PreparedStatement st = MySQLConnect.conn.prepareStatement(getComputerById)){
			st.setLong(1, id);
			ComputerRes = st.executeQuery(); 
			Computer computer = Mapper.computerMapper(ComputerRes);
			return computer;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void createComputer(Computer computer) throws SQLException {
		try (PreparedStatement st = MySQLConnect.conn.prepareStatement(createComputer);){
			st.setString(1, computer.getName());
			st.setTimestamp(2, computer.getIntroduced() != null 
					? Timestamp.valueOf(computer.getIntroduced()) : null);
			st.setTimestamp(3, computer.getDiscontinued() != null 
					? Timestamp.valueOf(computer.getDiscontinued()) : null);
			Company company = computer.getCompany();
			st.setLong(4, company.id);
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateComputer(Computer computer) throws SQLException {
		try (PreparedStatement st = MySQLConnect.conn.prepareStatement(updateComputer)){
			st.setString(1, computer.getName());
			st.setTimestamp(2, computer.getIntroduced() != null 
					? Timestamp.valueOf(computer.getIntroduced()) : null);
			st.setTimestamp(3, computer.getDiscontinued() != null 
					? Timestamp.valueOf(computer.getDiscontinued()) : null);
			Company company = computer.getCompany();
			st.setLong(4, company.id);
			st.setLong(5, computer.id);
			st.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteComputer(long id) throws SQLException {
		try (PreparedStatement st = MySQLConnect.conn.prepareStatement(deleteComputer)){
			st.setLong(1, id);
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
