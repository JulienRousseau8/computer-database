package com.excilys.service;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.excilys.model.Computer;
import com.excilys.service.Validators;

@RunWith(MockitoJUnitRunner.class)
public class ValidatorsTest extends Mockito{

	String dateIntro = "2010-04-22";
	String dateArret = "2012-04-23";
	LocalDate dateIn = LocalDate.of(2010, 04, 22);
	LocalDate dateArr = LocalDate.of(2012, 04, 23);
	
//	@Mock
//	ConvertDate convertDate;
//	
//	//ConvertDate cD = mock(ConvertDate.class);
//	@Mock
//	Validators validators;
	
	@Mock
	Computer computer;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	//TEST AVEC MOCK
	@Test
	public void testVerifierDate() {
		when(computer.getIntroduced()).thenReturn(dateIn);
		when(computer.getDiscontinued()).thenReturn(dateArr);
		assertTrue(Validators.verifierDateOrdre(dateIn.toString(), dateArr.toString()));
	}
	
	//TEST SANS MOCK
	@Test
	public void testVerifierDateOrdreTrue() {
		boolean validatorsTrue = Validators.verifierDateOrdre(dateIntro, dateArret);
		assertEquals(true, validatorsTrue);
	}

	@Test
	public void testVerifierDateOrdreFalse() {
		boolean validatorsFalse = Validators.verifierDateOrdre(dateArret, dateIntro);
		assertEquals(false, validatorsFalse);
	}

	@Test
	public void testVerifierFormatDateBon() {
		boolean formatDate = Validators.verifierDateUtilisateurSaisie(dateIntro);
		assertEquals(true, formatDate);
	}

	@Test
	public void testVerifierFormatDateWrong() {
		String mauvaiseDate = "ffsfgd";
		boolean formatDate = Validators.verifierDateUtilisateurSaisie(mauvaiseDate);
		assertEquals(false, formatDate);
	}
	
	@Test
	public void testVerifierFormatDateSlash() {
		String mauvaiseDate = "2010/04/22";
		boolean formatDate = Validators.verifierDateUtilisateurSaisie(mauvaiseDate);
		assertEquals(false, formatDate);
	}
}
