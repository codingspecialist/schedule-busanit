package com.bitc.practiceProgress.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.junit.Test;

public class CurrentMonthTest {
	
	@Test
	public void 현재월() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("YYYY");
		String classDate = formater.format(cal.getTime());
		
		System.out.println(classDate);
	}
}
