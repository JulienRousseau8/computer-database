package com.excilys.mapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class ConvertDate {

	public static LocalDate convert(String date) {
		if(date.isEmpty()) {
			return null;
		}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate localDateTime = LocalDate.parse(date, formatter);
		return localDateTime;
	}

}
