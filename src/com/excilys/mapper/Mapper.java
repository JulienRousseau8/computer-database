package com.excilys.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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
			LocalDate introduced = (rs.getTimestamp("introduced") != null 
									? rs.getDate("introduced").toLocalDate() : null);
			LocalDate discontinued = (rs.getTimestamp("discontinued") != null 
									? rs.getDate("discontinued").toLocalDate() : null);
			long company_id = rs.getLong("company_id");
			Optional<Company> company = DAOcompany.getInstance().getCompanyById(company_id);
			Computer computer = new Computer.ComputerBuilder().setId(id).setName(name)
					.setIntroduced(introduced).setDiscontinued(discontinued).setCompany(null).build();
			if (company.isPresent()) {
				computer= new Computer.ComputerBuilder().setId(id).setName(name).setIntroduced(introduced)
						.setDiscontinued(discontinued).setCompany(company.get()).build();
			}	
			listComputers.add(computer);
		}
		return listComputers;
	}

	public static Optional<Computer> computerMapper(ResultSet rs) throws SQLException {
		if(rs.first()) {
			long id = rs.getLong("id");
			String name = rs.getString("name");
			LocalDate introduced = (rs.getDate("introduced") != null 
					? rs.getDate("introduced").toLocalDate() : null);
			LocalDate discontinued = (rs.getDate("discontinued") != null 
					? rs.getDate("discontinued").toLocalDate() : null);
			long company_id = rs.getLong("company_id");
			Optional<Company> company = DAOcompany.getInstance().getCompanyById(company_id);
			Computer computer = new Computer.ComputerBuilder().setId(id).setName(name)
					.setIntroduced(introduced).setDiscontinued(discontinued).setCompany(null).build();
			if (company.isPresent()) {
				computer= new Computer.ComputerBuilder().setId(id).setName(name)
						.setIntroduced(introduced).setDiscontinued(discontinued).setCompany(company.get()).build();
			}	
			return Optional.of(computer);
		}
		return Optional.empty();
	}

//	public static ArrayList<Computer> computerPagesMapper(ResultSet rs) throws SQLException{
//		ArrayList<Computer> computerPages = new ArrayList<Computer>();
//		while(rs.next()) {
//			Computer computer = new Computer.ComputerBuilder()
//			computerPages.add(computer);
//			}
//		return computerPages;
//	}
	
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
