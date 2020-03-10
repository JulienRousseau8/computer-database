package com.excilys.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import com.excilys.DAO.DAOcompany;
import com.excilys.model.*;

public class Mapper {

	static DAOcompany daocompany = new DAOcompany();
	
	public static ArrayList<Computer> computerListeMapper(ResultSet rs) throws SQLException {
		ArrayList<Computer> listComputers = new ArrayList<Computer>();
		while(rs.next()) {
			long id = rs.getLong("id");
			String name = rs.getString("name");
			LocalDateTime introduced = (rs.getTimestamp("introduced") != null 
										? rs.getTimestamp("introduced").toLocalDateTime() : null);
			LocalDateTime discontinued = (rs.getTimestamp("discontinued") != null 
										? rs.getTimestamp("discontinued").toLocalDateTime() : null);
			long company_id = rs.getLong("company_id");
			Company company = new Company();
			company = daocompany.getCompanyById(company_id);
			Computer computer = new Computer(id, name, introduced, discontinued, company);
			listComputers.add(computer);
		}
		return listComputers;
	}
	
	public static Computer computerMapper(ResultSet rs) throws SQLException {
		
		while(rs.first()) {
			long id = rs.getLong("id");
			String name = rs.getString("name");
			LocalDateTime introduced = (rs.getTimestamp("introduced") != null 
										? rs.getTimestamp("introduced").toLocalDateTime() : null);
			LocalDateTime discontinued = (rs.getTimestamp("discontinued") != null 
										? rs.getTimestamp("discontinued").toLocalDateTime() : null);
			long company_id = rs.getLong("company_id");
			Company company = new Company();
			company = daocompany.getCompanyById(company_id);
			Computer computer = new Computer(id, name, introduced, discontinued, company);
			return computer;
		}
		return null;
	}
}
