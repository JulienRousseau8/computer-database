package com.excilys.service;

import java.time.LocalDate;
import java.util.GregorianCalendar;
import java.util.logging.Logger;

import com.excilys.service.ConvertDate;

public class Validators {

	private final static Logger LOGGER = Logger.getLogger(Validators.class.getName());
	
	public static boolean verifierDateUtilisateurSaisie(String date) {
		try
		{
			String annee = date.substring(0,4);
			int dateAnnee = Integer.parseInt(annee);
			String mois = date.substring(5,7);
			int dateMois = Integer.parseInt(mois);
			String jour = date.substring(8,10);
			int dateJour = Integer.parseInt(jour);

			GregorianCalendar gc = new GregorianCalendar(dateAnnee, dateMois, dateJour);
			gc.setLenient(false);
			return true;
		}
		catch (Exception e) {
			LOGGER.info("Mauvais format");
		}
		return false;
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
