package com.excilys.mapper;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.excilys.dto.CompanyDTO;
import com.excilys.dto.ComputerDTO;
import com.excilys.model.Company;
import com.excilys.model.Computer;

public class ComputerDTOMapperTest {
	
	ComputerDTOMapper mapper = new ComputerDTOMapper();
	
	CompanyDTO companyDTO = new CompanyDTO.Builder()
			.setId("1")
			.setName("Apple Inc.")
			.build();
	
	Company company = new Company.Builder()
			.setId((long) 1)
			.setName("Apple Inc.").build();

	ComputerDTO computerDto = new ComputerDTO.Builder()
			.setId("17")
			.setName("Apple III Plus")
			.setIntroduced("1983-12-01")
			.setDiscontinued("1984-04-01")
			.setCompanyId(companyDTO.getId())
			.setCompanyName(companyDTO.getName())
			.build();
	
	Computer computer = new Computer.Builder()
			.setId((long) 17)
			.setName("Apple III Plus")
			.setIntroduced(LocalDate.of(1983, 12, 01))
			.setDiscontinued(LocalDate.of(1984, 04, 01))
			.setCompany(company)
			.build();
	
	List<ComputerDTO> computersDtoList = new ArrayList<ComputerDTO>();
	List<Computer> computersList = new ArrayList<Computer>();
	
	@Test
	public void testDtoToComputer() throws SQLException {
		Computer computerRes = mapper.dtoToComputer(computerDto);
		assertEquals(computer, computerRes);
	}

	@Test
	public void testComputerToDto() {
		ComputerDTO computerRes = mapper.computerToDto(computer);
		assertEquals(computerDto, computerRes);
	}

	@Test
	public void listDtoToComputer() {
		computersDtoList.add(computerDto);
		computersList.add(computer);
		List<Computer> listCompanies = mapper.listDtoToComputer(computersDtoList);
		assertEquals(computersList, listCompanies);
	}
	
	@Test
	public void listCompanyToDto() {
		computersDtoList.add(computerDto);
		computersList.add(computer);
		List<ComputerDTO> listDTOComputers = mapper.listComputerToDto(computersList);
		assertEquals(computersDtoList, listDTOComputers);
	}
}
