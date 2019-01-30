package com.motoleague.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	public static Date getDateFromString(String dateStr, String format) {
		DateFormat dateFormat = new SimpleDateFormat(format);
		if (dateStr != null ) {
			try {
				return dateFormat.parse(dateStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}
}
