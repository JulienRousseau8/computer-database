package com.excilys.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConvertDate {

	private static Logger logger = LoggerFactory.getLogger(ConvertDate.class);

	public static LocalDate convert(String date) {
		boolean format = Validators.verifierDateUtilisateurSaisie(date);
		if (!format) {
			return null;
		} else {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			try {
				LocalDate localDate = LocalDate.parse(date, formatter);
				if(localDate.isAfter(LocalDate.now())) {
					logger.info("Veuillez entrer une date valide");
				}
				return localDate;
			} catch (DateTimeParseException DTexc) {
				logger.info("La date saisie n'est pas bonne");
				DTexc.printStackTrace();
			} catch(Exception e) {
				logger.info("La date saisie n'est pas bonne");
				e.printStackTrace();
			}
			return null;

		}
	}
}