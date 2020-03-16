package com.excilys.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import com.excilys.model.*;

public class Mapper {

	public static ArrayList<Computer> computerListeMapper(ResultSet rs) throws SQLException {
		ArrayList<Computer> listComputers = new ArrayList<Computer>();
		while(rs.next()) {
			long computerId = rs.getLong("computer.id");
			String computerName = rs.getString("computer.name");
			LocalDate introduced = (rs.getTimestamp("computer.introduced") != null 
										? rs.getDate("computer.introduced").toLocalDate() : null);
			LocalDate discontinued = (rs.getTimestamp("computer.discontinued") != null 
										? rs.getDate("computer.discontinued").toLocalDate() : null);
			long companyId = rs.getLong("company_id");
			String companyName = rs.getString("name");

			Company company = new Company.CompanyBuilder().setId(companyId).setName(companyName).build();
			Computer computer= new Computer.ComputerBuilder().setId(computerId).setName(computerName).setIntroduced(introduced)
				.setDiscontinued(discontinued).setCompany(company).build();
			listComputers.add(computer);
			
		}
		return listComputers;
	}
	
	public static Optional<Computer> computerMapper(ResultSet rs) throws SQLException {
		if(rs.first()) {
			long computerId = rs.getLong("computer.id");
			String computerName = rs.getString("computer.name");
			LocalDate introduced = (rs.getTimestamp("computer.introduced") != null 
										? rs.getDate("computer.introduced").toLocalDate() : null);
			LocalDate discontinued = (rs.getTimestamp("computer.discontinued") != null 
										? rs.getDate("computer.discontinued").toLocalDate() : null);
			long companyId = rs.getLong("company_id");
			String companyName = rs.getString("company.name");

			Company company = new Company.CompanyBuilder().setId(companyId).setName(companyName).build();
			Computer computer= new Computer.ComputerBuilder().setId(computerId).setName(computerName).setIntroduced(introduced)
				.setDiscontinued(discontinued).setCompany(company).build();
			return Optional.of(computer);
		}
		return Optional.empty();
	}
	
	public static ArrayList<Company> companyListeMapper(ResultSet rs) throws SQLException {
		ArrayList<Company> listCompanies = new ArrayList<Company>();
		while(rs.next()) {
			long id = rs.getLong("id");
			String name = rs.getString("name");

			Company company = new Company.CompanyBuilder().setId(id).setName(name).build();
			listCompanies.add(company);
		}
		return listCompanies;
	}

	public static Optional<Company> companyMapper(ResultSet rs) throws SQLException {
		if(rs.first()) {
			long companyid = rs.getLong("id");
			String name = rs.getString("name");;

			Company company = new Company.CompanyBuilder().setId(companyid).setName(name).build(); 
			return Optional.of(company);
		}
		return Optional.empty();
	}
}
