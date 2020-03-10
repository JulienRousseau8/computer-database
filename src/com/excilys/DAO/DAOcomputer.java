package com.excilys.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.excilys.mapper.Mapper;
import com.excilys.model.Computer;
import com.excilys.persistence.MySQLConnect;

public class DAOcomputer {

	MySQLConnect mysql = MySQLConnect.getDbCon();
	private final static String getComputers = "SELECT id,name,introduced,discontinued,company_id FROM computer";
	private final static String getComputerById = "SELECT id,name,introduced,discontinued,company_id FROM computer WHERE id=?";
	private final static String createComputer = "INSERT INTO computer (name, introduced, discontinued, company_id) VALUES (?, ?, ?, ?)";
	private final static String deleteComputer = "DELETE FROM computer WHERE id=";
	
	
	public ArrayList<Computer> getComputers() throws SQLException{
		ResultSet allComputerRes;
		ArrayList<Computer> listComputers = new ArrayList<Computer>();
		
		PreparedStatement st = MySQLConnect.conn.prepareStatement(getComputers);
		allComputerRes = st.executeQuery();
		Computer computer = Mapper.computerMapper(allComputerRes);
		listComputers.add(computer);
		return listComputers;
	}
	
	public Computer getComputerById(int id) throws SQLException {
		ResultSet ComputerRes;
		
		PreparedStatement st = MySQLConnect.conn.prepareStatement(getComputerById);
		st.setLong(1, id);
		ComputerRes = st.executeQuery(); 
		Computer computer = Mapper.computerMapper(ComputerRes);
		return computer;
	}
	
	public void createComputer(Computer computer) throws SQLException {
		PreparedStatement st = MySQLConnect.conn.prepareStatement(createComputer);
		st.setString(1, computer.getName());
		st.setTimestamp(2, computer.getIntroduced() != null ? Timestamp.valueOf(computer.getIntroduced()) : null);
		st.setTimestamp(3, computer.getDiscontinued() != null ? Timestamp.valueOf(computer.getDiscontinued()) : null);
		st.setLong(4, computer.getCompany_id());
		st.executeUpdate();
	}
	
	public boolean updateComputer(Computer computer) {
		boolean update = false;
		
		return update;
	}
	
	public boolean deleteComputer(long id) throws SQLException {
		boolean delete = false;
		mysql.insert(deleteComputer+id);
		return delete;
	}
	
}
