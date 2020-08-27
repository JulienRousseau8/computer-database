package com.excilys.service;

import com.excilys.configuration.HibernateConfig;
import com.excilys.configuration.SpringConfig;
import com.excilys.model.Computer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { SpringConfig.class, HibernateConfig.class})
public class ValidatorsTest extends Mockito{

	String dateIntro = "2010-04-22";
	String dateArret = "2012-04-23";
	LocalDate dateIn = LocalDate.of(2010, 4, 22);
	LocalDate dateArr = LocalDate.of(2012, 4, 23);
	
	@Mock
	Computer computer;

	@Autowired
	Validators validators;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	//TEST AVEC MOCK
	@Test
	public void testVerifierDate() {
		when(computer.getIntroduced()).thenReturn(dateIn);
		when(computer.getDiscontinued()).thenReturn(dateArr);
		assertTrue(validators.verifierDateOrdre(dateIn.toString(), dateArr.toString()));
	}
	
	//TEST SANS MOCK
	@Test
	public void testVerifierDateOrdreTrue() {
		boolean validatorsTrue = validators.verifierDateOrdre(dateIntro, dateArret);
		assertTrue(validatorsTrue);
	}

	@Test
	public void testVerifierDateOrdreFalse() {
		boolean validatorsFalse = validators.verifierDateOrdre(dateArret, dateIntro);
		assertFalse(validatorsFalse);
	}

	@Test
	public void testVerifierFormatDateBon() {
		boolean formatDate = validators.verifierDateUtilisateurSaisie(dateIntro);
		assertTrue(formatDate);
	}

	@Test
	public void testVerifierFormatDateWrong() {
		String mauvaiseDate = "ffsfgd";
		boolean formatDate = validators.verifierDateUtilisateurSaisie(mauvaiseDate);
		assertFalse(formatDate);
	}
	
	@Test
	public void testVerifierFormatDateSlash() {
		String mauvaiseDate = "2010/04/22";
		boolean formatDate = validators.verifierDateUtilisateurSaisie(mauvaiseDate);
		assertFalse(formatDate);
	}
}
