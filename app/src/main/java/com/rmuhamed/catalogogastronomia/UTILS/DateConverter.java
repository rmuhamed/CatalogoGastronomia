package com.rmuhamed.catalogogastronomia.UTILS;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter {

	public static final String convertDate(Date date, String format){
		String result = null;
		
		if(date!=null && format!=null){
			SimpleDateFormat dateFormat = new SimpleDateFormat(format);
			result = dateFormat.format(date);
		}
		
		return result;
	}
	
	public static final Date parseString(String dateStr, String format){
		Date result = null;
		
		if( (dateStr!=null && !dateStr.equals("")) && (format!=null && !format.equals("")) ){
			SimpleDateFormat dateFormat = new SimpleDateFormat(format);
			try {
				result = dateFormat.parse(dateStr);
			} catch (ParseException e) {
				result=null;
			}
		}
		
		return result;
	}
	
	public static final String convertDateTime(Date date){
		String result = null;
		if(date!=null){
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
			result = format.format(date);
		}

		return result;
	}
}
