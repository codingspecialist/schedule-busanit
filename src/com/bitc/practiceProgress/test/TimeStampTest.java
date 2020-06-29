package com.bitc.practiceProgress.test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeStampTest {
	public static void main(String[] args) {
		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
		String classDate = formater.format(cal.getTime());
		
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		
		System.out.println((Date)ts);
		
		Date time = (Date)ts;
		
		System.out.println(formater.format(ts));

	}
}
