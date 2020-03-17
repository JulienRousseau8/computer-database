package com.excilys.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

import com.excilys.model.*;

public class Mapper {

	public static Optional<Computer> computerMapper(ResultSet rs) throws SQLException {
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
		return Optional.ofNullable(computer);
	}

	public static Optional<Company> companyMapper(ResultSet rs) throws SQLException {
		long companyid = rs.getLong("id");
		String name = rs.getString("name");;
		Company company = new Company.CompanyBuilder().setId(companyid).setName(name).build(); 
		return Optional.ofNullable(company);
	}
}
