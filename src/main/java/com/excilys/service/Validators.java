package com.excilys.service;

import java.time.LocalDate;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.DAO.DAOcompany;
import com.excilys.model.Company;

public class Validators {

	private static Logger logger = LoggerFactory.getLogger(ComputerService.class);

	public static boolean verifierDateUtilisateurSaisie(String date) {
		if (date.isEmpty()) {
			return true;
		}
		if (date.substring(4, 5).equals("/")) {
			logger.info("Mauvais format de Date");
			return false;
		}
		try {
			String annee = date.substring(0, 4);
			int dateAnnee = Integer.parseInt(annee);
			String mois = date.substring(5, 7);
			int dateMois = Integer.parseInt(mois);
			String jour = date.substring(8, 10);
			int dateJour = Integer.parseInt(jour);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Mauvais format de Date");
			return false;
		}
	}
	
	public static boolean verifierDateOrdre(String dateIntroduction, String dateArret) {
		LocalDate intro = ConvertDate.convert(dateIntroduction);
		LocalDate arret = ConvertDate.convert(dateArret);
		if (dateIntroduction.isEmpty() || dateArret.isEmpty()) {
			return true;
		}
		return arret.isAfter(intro);
	}

	public static boolean verifierIdCompany(String id){
		try {
			long compId = Long.parseLong(id);
			Optional<Company> optionalCompany = DAOcompany.getInstance().getCompanyById(compId);
			if (optionalCompany.isPresent()) {
				return true;
			} else {
				logger.info("Cette entreprise n'existe pas");
				return false;
			}
		} catch (NumberFormatException numEx) {
			logger.info("L'Id entreprise doit être un entier");
			return false;
		}
	}
}
