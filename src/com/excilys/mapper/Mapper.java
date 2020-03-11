package com.excilys.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import com.excilys.DAO.DAOcompany;
import com.excilys.model.*;

public class Mapper {
	
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
			Optional<Company> company = DAOcompany.getInstance().getCompanyById(company_id);
			Computer computer = new Computer(id, name, introduced, discontinued, null);
			if (company.isPresent()) {
				computer= new Computer(id, name, introduced, discontinued, company.get());
			}	
			listComputers.add(computer);
		}
		return listComputers;
	}
	
	public static Computer computerMapper(ResultSet rs) throws SQLException {
		
		if(rs.first()) {
			long id = rs.getLong("id");
			String name = rs.getString("name");
			LocalDateTime introduced = (rs.getTimestamp("introduced") != null 
										? rs.getTimestamp("introduced").toLocalDateTime() : null);
			LocalDateTime discontinued = (rs.getTimestamp("discontinued") != null 
										? rs.getTimestamp("discontinued").toLocalDateTime() : null);
			long company_id = rs.getLong("company_id");
			Optional<Company> company = DAOcompany.getInstance().getCompanyById(company_id);
			Computer computer = new Computer(id, name, introduced, discontinued, null);
			if (company.isPresent()) {
				computer= new Computer(id, name, introduced, discontinued, company.get());
			}	
			return computer;
		}
		return null;
	}
}
