package com.excilys.service;

import java.time.LocalDate;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.dto.ComputerDTO;
import com.excilys.mapper.DateMapper;
import com.excilys.model.Company;

public class Validators {

	private static Logger logger = LoggerFactory.getLogger(ComputerService.class);
	CompanyService companyService;

	public Validators(CompanyService companyService) {
		this.companyService = companyService;
	}

	public boolean verifierDateUtilisateurSaisie(String date) {
		if (date == null || date.isEmpty()) {
			return false;
		}
		if (date.substring(4, 5).equals("/")) {
			logger.info("Mauvais format de Date");
			return false;
		}

		try {
			String annee = date.substring(0, 4);
			Integer.parseInt(annee);

			String mois = date.substring(5, 7);
			Integer.parseInt(mois);

			String jour = date.substring(8, 10);
			Integer.parseInt(jour);

			return true;
		} catch (NumberFormatException NFexc) {
			NFexc.printStackTrace();
			logger.info("Mauvais format de Date");
			return false;
		}
	}

	public boolean verifierDateOrdre(String dateIntroduction, String dateArret) {
		LocalDate intro = DateMapper.stringToDate(dateIntroduction);
		LocalDate arret = DateMapper.stringToDate(dateArret);
		boolean dateIntroNow = verifierDateNow(dateIntroduction);
		boolean dateArretNow = verifierDateNow(dateArret);
		if (dateIntroduction.isEmpty() || dateArret.isEmpty()) {
			return true;
		}
		else if (dateIntroNow && dateArretNow) {
			return arret.isAfter(intro);
		}
		else {
			logger.info("La date doit inférieur à la date d'aujourd'hui");
			return false;
		}
	}

	public boolean verifierIdCompany(String id){
		try {
			Optional<Company> optionalCompany = companyService.getCompanyById(id);
			if (optionalCompany.isPresent()) {
				return true;
			} else {
				logger.info("Cette entreprise n'existe pas");
				return false;
			}
		} catch (NumberFormatException NFexc) {
			NFexc.printStackTrace();
			logger.info("L'Id entreprise doit être un entier");
			return false;
		}
	}
	
	public boolean verifierDateNow(String date) {
		LocalDate localDate = DateMapper.stringToDate(date);
		return localDate.isBefore(LocalDate.now());
	}
	
	public boolean verifierDate(ComputerDTO computerDto) {
		boolean ordreDate = false;
		boolean dateIntroduced = verifierDateUtilisateurSaisie(computerDto.getIntroduced());
		boolean dateDiscontinued = verifierDateUtilisateurSaisie(computerDto.getDiscontinued());
		if (dateIntroduced && dateDiscontinued) {
			ordreDate = verifierDateOrdre(computerDto.getIntroduced(), computerDto.getDiscontinued());
			if (!ordreDate) {
				logger.info("Dates non valident !");
			}
		}
		if (dateIntroduced && dateDiscontinued && ordreDate) {
			return true;
		} else return false;
	}
	
	public boolean verifierCompany(ComputerDTO computerDto) {
		boolean comp = true;
		if (computerDto.getCompanyId().isEmpty()) {
			comp = false;
			logger.info("Id de la compagnie requis");
		} else if (!verifierIdCompany(computerDto.getCompanyId())) {
			comp = false;
		}
		return comp;
	}
	
	public boolean verifierNom(ComputerDTO computerDto) {
		boolean name = true;
		if (computerDto.getName().isEmpty()) {
			name = false;
			logger.info("Nom requis");
		}
		return name;
	}
}
