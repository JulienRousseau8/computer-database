package com.excilys.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ConvertDate {

	public static LocalDate convert(String date) {
		boolean format = Validators.verifierDateUtilisateurSaisie(date);
		if (date.isEmpty()) {
			return null;
		} else if (!format) {
			return null;
		} else {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate localDate = LocalDate.parse(date, formatter);
			return localDate;
		}
	}

}
