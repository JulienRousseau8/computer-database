package com.excilys.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import com.excilys.model.*;

public class Mapper {

	
	public static Computer computerMapper(ResultSet rs) throws SQLException {
		
		while(rs.next()) {
			long id = rs.getLong("id");
			String name = rs.getString("name");
			LocalDateTime introduced = (rs.getTimestamp("introduced") != null 
										? rs.getTimestamp("introduced").toLocalDateTime() : null);
			LocalDateTime discontinued = (rs.getTimestamp("discontinued") != null 
										? rs.getTimestamp("discontinued").toLocalDateTime() : null);
			long company_id = rs.getLong("company_id");
			Computer computer = new Computer(id, name, introduced, discontinued, company_id);
			return computer;
		}
		return null;
	}
}
