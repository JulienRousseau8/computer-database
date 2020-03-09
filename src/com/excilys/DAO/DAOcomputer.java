package com.excilys.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import com.excilys.model.Computer;
import com.excilys.persistence.MySQLConnect;

public class DAOcomputer {

	MySQLConnect mysql = MySQLConnect.getDbCon();
	private final static String getComputers = "SELECT id,name,introduced,discontinued,company_id FROM computer";
	private final static String getComputerById = "SELECT id,name,introduced,discontinued,company_id FROM computer WHERE id=";
	private final static String createComputer = "INSERT INTO computer(name, introduced, discontinued, company_id) VALUES (";
	private final static String deleteComputer = "DELETE FROM computer WHERE id=";
	
	public ArrayList<Computer> getComputers() throws SQLException {
		ArrayList<Computer> listComputers = new ArrayList<Computer>();
		ResultSet AllComputerRes = mysql.query(getComputers);
		while(AllComputerRes.next()) {
			long id = AllComputerRes.getLong("id");
			String name = AllComputerRes.getString("name");
			Date introduced = AllComputerRes.getDate("introduced");
			Date discontinued = AllComputerRes.getDate("discontinued");
			long company_id = AllComputerRes.getLong("company_id");
			
			Computer computer = new Computer(id, name, introduced, discontinued, company_id);
			listComputers.add(computer);
		}
		return listComputers;
	}
	
	public Optional<Computer> getComputerById(int id) throws SQLException {
		ResultSet ComputerRes = mysql.query(getComputerById+id);
		while(ComputerRes.next()) {
			long computerid = ComputerRes.getLong("id");
			String name = ComputerRes.getString("name");
			Date introduced = ComputerRes.getDate("introduced");
			Date discontinued = ComputerRes.getDate("discontinued");
			long company_id = ComputerRes.getLong("company_id");
			
			Computer computer = new Computer(computerid, name, introduced, discontinued, company_id); 
			return Optional.of(computer);
		}
		return Optional.empty();
	}
	
	public boolean createComputer(String name, Date introduced, Date discontinued, long company_id) throws SQLException {
		boolean create = false;
		mysql.insert(createComputer + "\"" + name + "\"" + "\"" + introduced + "\"" + "\"" + discontinued + "\"" + "\"" + company_id + "\"" + ")");
		return create;
	}
	
	public boolean updateComputer(long id, String name, Date Introduced, Date discontinued, long company_id) {
		boolean update = false;
		
		return update;
	}
	
	public boolean deleteComputer(long id) throws SQLException {
		boolean delete = false;
		mysql.insert(deleteComputer+id);
		return delete;
	}
	
}
