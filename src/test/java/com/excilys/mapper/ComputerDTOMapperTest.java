package com.excilys.mapper;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.excilys.dto.CompanyDTO;
import com.excilys.dto.ComputerDTO;
import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.persistence.Connexion;

@RunWith(MockitoJUnitRunner.class)
public class ComputerDTOMapperTest {

	
	String date1 = "2010-04-22";
	String date2 = "2010-04-23";

	CompanyDTO companyDTO = new CompanyDTO.CompanyDTOBuilder().setId("10").setName("Digital Equipment Corporation")
			.build();

	Company company = new Company.CompanyBuilder().setId((long) 10).setName("Digital Equipment Corporation").build();

	ComputerDTO computerDto = new ComputerDTO.ComputerDTOBuilder().setId("10").setName("Ordinateur")
			.setIntroduced(date1).setDiscontinued(date2)
			.setCompanyId(String.valueOf(company.getId()))
			.build();

	Computer computer = new Computer.ComputerBuilder().setId((long) 10).setName("Ordinateur")
			.setIntroduced(LocalDate.of(2010, 04, 22)).setDiscontinued(LocalDate.of(2010, 04, 23)).setCompany(company)
			.build();

	@Before
	public void setUp() {
		Connexion.getDbCon();
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testDtoToComputer() throws SQLException {
		Computer computerRes = ComputerDTOMapper.dtoToComputer(computerDto);

		assertEquals(computer.getName(), computerRes.getName());
		assertEquals(computer.getIntroduced(), computerRes.getIntroduced());
		assertEquals(computer.getDiscontinued(), computerRes.getDiscontinued());
		assertEquals(computer.getCompany().getId(), computerRes.getCompany().getId());
		assertEquals(computer.getCompany().getName(), computerRes.getCompany().getName());
	}

	@Test
	public void testComputerToDto() {
		ComputerDTO computerRes = ComputerDTOMapper.computerToDto(computer);

		assertEquals(computerDto.getId(), computerRes.getId());
		assertEquals(computerDto.getName(), computerRes.getName());
		assertEquals(computerDto.getIntroduced(), computerRes.getIntroduced());
		assertEquals(computerDto.getDiscontinued(), computerRes.getDiscontinued());
		assertEquals(computerDto.getCompanyId(), computerRes.getCompanyId());
	}

}
