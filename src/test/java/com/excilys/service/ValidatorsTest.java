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

import com.excilys.DAO.DAOcompany;
import com.excilys.service.Validators;

@RunWith(MockitoJUnitRunner.class)
public class ValidatorsTest {

	String dateIntro = "2010-04-22";
	String dateArret = "2012-04-23";
	LocalDate dateIn = LocalDate.of(2010, 04, 22);
	LocalDate dateArr = LocalDate.of(2010, 04, 23);

	@Mock
	DAOcompany daoCompany;
	@Mock
	ConvertDate convertDate;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		Mockito.when(ConvertDate.convert(dateArret)).thenReturn(dateArr);
		Mockito.when(ConvertDate.convert(dateIntro)).thenReturn(dateIn);
	}


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
}
