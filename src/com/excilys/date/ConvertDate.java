package com.excilys.date;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ConvertDate {

	public static LocalDateTime convert(String date) {
		date = date + " 00:00:00";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime localDateTime = LocalDateTime.parse(date, formatter);
		return localDateTime;
	}
	
}
