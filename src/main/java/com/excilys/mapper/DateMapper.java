package com.excilys.mapper;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateMapper {

	private static Logger logger = LoggerFactory.getLogger(DateMapper.class);

	public static LocalDate stringToDate(String date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		try {
			LocalDate localDate = LocalDate.parse(date, formatter);
			return localDate;

		} catch (DateTimeParseException DTPexc) {
			logger.info("La date saisie n'est pas bonne");
			DTPexc.printStackTrace();
		} catch (DateTimeException DTexc) {
			logger.info("La date saisie n'est pas bonne");
			DTexc.printStackTrace();
		} catch (Exception e) {
			logger.info("La date saisie n'est pas bonne");
			e.printStackTrace();
		}
		return null;
	}
}
