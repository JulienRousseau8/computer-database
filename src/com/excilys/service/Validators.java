package com.excilys.service;

import java.time.LocalDate;

import com.excilys.mapper.ConvertDate;

public class Validators {

	private static boolean dateUtilisateur = true;
	private static boolean ordreDate = true;
	
	public static boolean verifierDateUtilisateurSaisie(String date) {
		return dateUtilisateur;
	}
	
	public static boolean verifierDateOrdre(String dateIntroduction, String dateArret) {
		LocalDate intro = ConvertDate.convert(dateIntroduction);
		LocalDate arret = ConvertDate.convert(dateArret);
		if(arret.isBefore(intro)) {
			ordreDate = false;
			return ordreDate;
		}
		
		return ordreDate;
	}
}
