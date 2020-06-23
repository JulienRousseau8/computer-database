package com.excilys.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.jdbc.core.RowMapper;

import com.excilys.model.Company;
import com.excilys.model.Computer;;

public class ComputerRSMapper implements RowMapper<Computer>{

	public static Optional<Computer> computerMapper(ResultSet ComputerResultSet) throws SQLException {
		long computerId = ComputerResultSet.getLong("computer.id");
		String computerName = ComputerResultSet.getString("computer.name");
		LocalDate introduced = (ComputerResultSet.getTimestamp("computer.introduced") != null
				? ComputerResultSet.getDate("computer.introduced").toLocalDate()
				: null);
		LocalDate discontinued = (ComputerResultSet.getTimestamp("computer.discontinued") != null
				? ComputerResultSet.getDate("computer.discontinued").toLocalDate()
				: null);
		long companyId = ComputerResultSet.getLong("company_id");
		String companyName = ComputerResultSet.getString("company.name");

		Company company = new Company.CompanyBuilder().setId(companyId).setName(companyName).build();
		Computer computer = new Computer.ComputerBuilder().setId(computerId).setName(computerName)
				.setIntroduced(introduced).setDiscontinued(discontinued).setCompany(company).build();
		return Optional.ofNullable(computer);
	}

	@Override
	public Computer mapRow(ResultSet rs, int rowNum) throws SQLException {
		return computerMapper(rs).get();
	}
	


}
