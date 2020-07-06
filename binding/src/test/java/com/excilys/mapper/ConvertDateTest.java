package com.excilys.mapper;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Test;

public class ConvertDateTest {

	@Test
	public void testConvertDateTrue() {
		String date = "2010-04-22";
		LocalDate local = DateMapper.stringToDate(date).get();
		LocalDate localDate = LocalDate.of(2010, 04, 22);
		assertEquals(localDate, local);
	}

	@Test
	public void testConvertDateEmpty() {
		String date = "";
		LocalDate local = DateMapper.stringToDate(date).get();
		assertEquals(null, local);
	}

	@Test
	public void testConvertDateFormat() {
		String date = "dsfefe";
		LocalDate local = DateMapper.stringToDate(date).get();
		assertEquals(null, local);
	}

	@Test
	public void testConvertDateSlash() {
		String date = "2010/04/22";
		LocalDate local = DateMapper.stringToDate(date).get();
		assertEquals(null, local);
	}
}
