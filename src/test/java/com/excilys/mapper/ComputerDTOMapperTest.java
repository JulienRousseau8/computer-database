package com.excilys.mapper;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.excilys.DAO.DAOcompany;
import com.excilys.dto.CompanyDTO;
import com.excilys.dto.ComputerDTO;
import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.persistence.H2Connect;
import com.excilys.persistence.MySQLConnect;

@RunWith(MockitoJUnitRunner.class)
public class ComputerDTOMapperTest {

	
	String date1 = "2010-04-22";
	String date2 = "2010-04-23";

	CompanyDTO companyDTO = new CompanyDTO.CompanyDTOBuilder().setId("10").setName("Digital Equipment Corporation")
			.build();

	Company company = new Company.CompanyBuilder().setId((long) 10).setName("Digital Equipment Corporation").build();

	ComputerDTO computerDto = new ComputerDTO.ComputerDTOBuilder().setId("10").setName("Ordinateur")
			.setIntroduced(date1).setDiscontinued(date2).setCompany(companyDTO).build();

	Computer computer = new Computer.ComputerBuilder().setId((long) 10).setName("Ordinateur")
			.setIntroduced(LocalDate.of(2010, 04, 22)).setDiscontinued(LocalDate.of(2010, 04, 23)).setCompany(company)
			.build();

	@Before
	public void setUp() {
		MySQLConnect.getDbCon();
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testDtoToComputer() throws SQLException {
		Computer computerRes = ComputerDTOMapper.dtoToComputer(computerDto);

		assertEquals(computer.name, computerRes.name);
		assertEquals(computer.introduced, computerRes.introduced);
		assertEquals(computer.discontinued, computerRes.discontinued);
		assertEquals(computer.company.id, computerRes.company.id);
		assertEquals(computer.company.name, computerRes.company.name);
	}

	@Test
	public void testComputerToDto() {
		ComputerDTO computerRes = ComputerDTOMapper.computerToDto(computer);

		assertEquals(computerDto.id, computerRes.id);
		assertEquals(computerDto.name, computerRes.name);
		assertEquals(computerDto.introduced, computerRes.introduced);
		assertEquals(computerDto.discontinued, computerRes.discontinued);
		assertEquals(computerDto.company.id, computerRes.company.id);
		assertEquals(computerDto.company.name, computerRes.company.name);
	}

}
