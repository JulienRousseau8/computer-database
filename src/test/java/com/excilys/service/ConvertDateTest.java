package com.excilys.service;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

import com.excilys.service.ConvertDate;

public class ConvertDateTest {

	@Test
	public void testConvertDateTrue() {
		String date = "2010-04-22";
		LocalDate local = ConvertDate.convert(date);
		LocalDate localDate = LocalDate.of(2010, 04, 22);
		assertEquals(localDate,local);
	}
	
	@Test
	public void testConvertDateEmpty() {
		String date = "";
		LocalDate local = ConvertDate.convert(date);
		assertEquals(null,local);
	}
	
	@Test
	public void testConvertDateFormat() {
		String date = "dsfefe";
		LocalDate local = ConvertDate.convert(date);
		assertEquals(null,local);
	}

}
