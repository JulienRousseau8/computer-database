package com.excilys.dao;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.DAO.DAOcomputer;
import com.excilys.configuration.HibernateConfig;
import com.excilys.configuration.SpringConfig;
import com.excilys.model.Company;
import com.excilys.model.Computer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringConfig.class, HibernateConfig.class})
@Transactional
public class DAOComputerTest {

	@Autowired
	DAOcomputer daoComputer;
	
	Company company = new Company.CompanyBuilder()
			.setId((long)1)
			.setName("Apple Inc.")
			.build();
	
	Computer computer = new Computer.ComputerBuilder()
			.setCompany(company)
			.setName("MacBook Pro")
			.setId((long)6)
			.setIntroduced(LocalDate.of(2006, 01, 10))
			.build();
	
	Computer updatecomputer = new Computer.ComputerBuilder()
			.setCompany(company)
			.setName("MacBook Pro update")
			.setId((long)6)
			.setIntroduced(LocalDate.of(2006, 01, 10))
			.build();
	
	Computer newComputer = new Computer.ComputerBuilder()
			.setCompany(company)
			.setName("Test")
			.setIntroduced(LocalDate.of(2006, 01, 10))
			.build();
	
	@Test
	public void getById5Present() {
		assertTrue(daoComputer.getComputerById(5).isPresent());
	}
	
	@Test
	public void getById5Empty() {
		assertFalse(daoComputer.getComputerById(5).get().toString().isEmpty());
	}

	@Test
	public void getAllCompanies() {
		assertEquals(daoComputer.getComputers().size(), 20);
	}
	
	@Test
	public void getById() {
		assertEquals(computer, daoComputer.getComputerById(6).get());
	}

	@Test
	public void getByIDFalse() {
		Computer falseComputer = new Computer.ComputerBuilder()
				.setCompany(company)
				.setName("Apple III")
				.setId((long)13)
				.build();
		assertNotEquals(falseComputer, daoComputer.getComputerById(12).get());;
	}
	
	@Test
	public void countAllComputerTrue() {
		assertEquals(20, daoComputer.countAllComputer());
	}
	@Test
	public void countAllComputerFalse() {
		assertNotEquals(21, daoComputer.countAllComputer());
	}
	
	@Test
	public void createComputer() {
		daoComputer.createComputer(newComputer);
		assertEquals(newComputer,daoComputer.getComputerById(21).get());
	}
	
	@Test
	public void updateComputer() {
		daoComputer.updateComputer(updatecomputer);
		assertEquals(updatecomputer, daoComputer.getComputerById(6).get());
	}
}
