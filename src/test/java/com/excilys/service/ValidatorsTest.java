package com.excilys.service;

import static org.junit.Assert.*;

import org.junit.Test;

import com.excilys.service.Validators;

public class ValidatorsTest {

	@Test
	public void testVerifierDateOrdreTrue() {
		String dateIntro = "2010-04-22";
		String dateArret = "2012-04-22";
		boolean validatorsTrue = Validators.verifierDateOrdre(dateIntro, dateArret);
		assertEquals(true, validatorsTrue);
		
	}

	@Test
	public void testVerifierDateOrdreFalse() {
		String dateIntro = "2010-04-22";
		String dateArret = "2012-04-22";
		boolean validatorsFalse = Validators.verifierDateOrdre(dateArret, dateIntro);
		assertEquals(false, validatorsFalse);
	}
	
	@Test
	public void testVerifierFormatDateBon(){
		String dateOk = "2010-04-22";		
		boolean formatDate = Validators.verifierDateUtilisateurSaisie(dateOk);
		assertEquals(true, formatDate);
	}
	
	@Test
	public void testVerifierFormatDateWrong(){
		String mauvaiseDate = "ffsfgd";		
		boolean formatDate = Validators.verifierDateUtilisateurSaisie(mauvaiseDate);
		assertEquals(false, formatDate);
	}
	
}
