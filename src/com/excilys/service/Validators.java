package com.excilys.service;

import java.time.LocalDate;

import com.excilys.mapper.ConvertDate;

public class Validators {

	
	public static boolean verifierDateUtilisateurSaisie(String date) {
		return true;
	}
	
	public static boolean verifierDateOrdre(String dateIntroduction, String dateArret) {
		LocalDate intro = ConvertDate.convert(dateIntroduction);
		LocalDate arret = ConvertDate.convert(dateArret);
		if(dateIntroduction.isEmpty() || dateArret.isEmpty()) {
			return true;
		}
		if(arret.isBefore(intro)) {
			return false;
		}
		
		return true;
	}
}
