package com.rmuhamed.catalogogastronomia.UTILS;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DataConversionHelper {
	
	public final static String SYNC_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
	public final static String DB_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public final static String DB_DATE_FORMAT = "yyyy-MM-dd";
	public final static String SYNC_TIME_FORMAT = "HH:mm";
	
	
	public static String convertDateToString(Date date, String dateFormat){
		return convertDateToString(date, dateFormat, null);
	}
	
	public static String convertDateToString(Date date, String dateFormat, String defaultValue){
		String result = defaultValue;
		if(date!=null){
			DateFormat format = new SimpleDateFormat(dateFormat);
			result = format.format(date);
		}
		return result;
	}
	
	public static String convertDateTimeDbToString(Date date){
		String result = null;
		if(date!=null){
			DateFormat format = new SimpleDateFormat(DB_DATE_TIME_FORMAT);
			result = format.format(date);
		}
		return result;
	}
	
	public static String convertDateDbToString(Date date){
		String result = null;
		if(date!=null){
			DateFormat format = new SimpleDateFormat(DB_DATE_FORMAT);
			result = format.format(date);
		}
		return result;
	}
	
	public static String convertDateSyncToString(Date date){
		String result = null;
		if(date!=null){
			DateFormat format = new SimpleDateFormat(SYNC_DATE_FORMAT);
			result = format.format(date);
		}
		return result;
	}
	
	public static Date convertStringToDate(String date, String dateFormat){
		Date result = null;
		if(date!=null && date.trim().length()>0){
			DateFormat format = new SimpleDateFormat(dateFormat);
			try {
				result = format.parse(date);
			} catch (Exception e) {
				result = null;
			}
		}
		return result;
	}
	
	public static Date convertStringSyncToDate(String date){
		Date result = null;
		if(date!=null && date.trim().length()>0){
			DateFormat format = new SimpleDateFormat(SYNC_DATE_FORMAT);
			try {
				result = format.parse(date);
			} catch (Exception e) {
				result = null;
			}
		}
		return result;
	}
	
	public static Date convertStringToDateTimeDatabase(String date){
		Date result = null;
		if(date!=null && date.trim().length()>0){
			DateFormat format = new SimpleDateFormat(DB_DATE_TIME_FORMAT);
			try {
				result = format.parse(date);
			} catch (Exception e) {
				result = null;
			}
		}
		return result;
	}
	
	public static String convertCharDbToString(char character) {
		return String.valueOf(character);
	}
	
	public static char convertStringToCharDatabase(String string) {
		return string.charAt(0);
	}

	public static Date convertStringToDateDatabase(String date){
		Date result = null;
		if(date!=null && date.trim().length()>0){
			DateFormat format = new SimpleDateFormat(DB_DATE_FORMAT);
			try {
				result = format.parse(date);
			} catch (Exception e) {
				result = null;
			}
		}
		return result;
	}

	public static int convertBooleanToInteger(boolean bool){
		return bool ? 1 : 0;
	}
	
	public static Integer convertBooleanToInteger(Boolean bool){
		return bool.booleanValue() ? new Integer(1) : new Integer(0);
	}
	
	public static boolean convertIntegerToBoolean(int value){
		return (value==1);
	}
	
	public static Boolean convertStringToBoolean(String bool){
		Boolean result = null;
		if(bool!=null){
			result = new Boolean(bool.toLowerCase().equals("true"));
		}
		return result;
	}
	
	public static String convertBooleanToString(boolean bool){
		return bool ? "true" : "false";
	}
	
	public static String convertDoubleToString(double value){
		return String.valueOf(value);
	}
	
	public static String convertDoubleToString(Double value){
		String result = "";
		if(value!=null){
			result = String.valueOf(value);
		}
		return result;
	}
	
	public static String convertDoubleToStringWithTwoDecimals(double value){
		Double d = value;
		if (!d.isNaN()){
			DecimalFormat df = new DecimalFormat("#.00");
			//To set you symbols as needed:
			DecimalFormatSymbols custom = new DecimalFormatSymbols();
			custom.setDecimalSeparator('.');
			df.setDecimalFormatSymbols(custom);
			return df.format(value);			
		}else{
			return "";
		}
	}
	
	public static String convertFloatToString(float value){
		return String.valueOf(value);
	}
	
	public static String convertFloatToStringWithThreeDecimals(float value){
		DecimalFormat df = new DecimalFormat("#.###");
		//To set you symbols as needed:
		DecimalFormatSymbols custom = new DecimalFormatSymbols();
		custom.setDecimalSeparator('.');
		df.setDecimalFormatSymbols(custom);
		
		Double aFloatConverted = DataConversionHelper.convertFloatToDouble(value);
		
		return df.format(aFloatConverted);
	}
	
	public static String convertFloatToStringWithTwoDecimals(float value){
		DecimalFormat df = new DecimalFormat("#.00");
		//To set you symbols as needed:
		DecimalFormatSymbols custom = new DecimalFormatSymbols();
		custom.setDecimalSeparator('.');
		df.setDecimalFormatSymbols(custom);
		
		Double aFloatConverted = DataConversionHelper.convertFloatToDouble(value);
		
		return df.format(aFloatConverted);
	}
	
	public static String convertFloatObjectToStringWithTwoDecimals(Float value){
		String converstion = "";
		if(value!=null){
			DecimalFormat df = new DecimalFormat("#.00");
			//To set you symbols as needed:
			DecimalFormatSymbols custom = new DecimalFormatSymbols();
			custom.setDecimalSeparator('.');
			df.setDecimalFormatSymbols(custom);
			
			Double aFloatConverted = DataConversionHelper.convertFloatToDouble(value);
			converstion = df.format(aFloatConverted);
		}
		
		return converstion;
	}
	
	/**
	 * Convert a String formatted as dd/MMMM/yyyy to calendar
	 * @author rmuhamed - Quares IT Solutions
	 * @param dateAsString
	 * @return calendar if parsing was successful
	 * @throws ParseException 
	 */
	public static Calendar convertStringToCalendar(String dateAsString) throws ParseException {
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date = (Date) formatter.parse(dateAsString);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);		
		
		return calendar;
	}

	public static float convertDoubleToFloat(double aDouble) {
		return Double.valueOf(aDouble).floatValue();
	}

	public static String convertIntegerToString(int anInt) {
		return String.valueOf(anInt);
	}

	public static String convertIntegerToString(Integer anInt) {
		String value = "";
		if(anInt!=null)
			value = String.valueOf(anInt);
		return value;
	}

	public static int convertStringToInteger(String string) {
		if (string.trim().equals(""))
			return 0;
		else
			return Integer.valueOf(string).intValue();
	}

	public static long convertStringToLong(String string) {
		if (string.trim().equals(""))
			return 0;
		else
			return Long.valueOf(string).longValue();
	}

	public static String convertFloatToStringInteger(float aFloatValue) {
		int result = Float.valueOf(aFloatValue).intValue();
		return String.valueOf(result);
	}

	public static float convertStringToFloat(String string) {
		if (string.trim().equals(""))
			return 0;
		else
			return Float.valueOf(string).floatValue();
	}

	public static String convertFloatToStringWithoutDecimals(float aFloatValue) {
		return String.valueOf((int) aFloatValue);
	}

	public static Double convertFloatToDouble(float value) {
		return Float.valueOf(value).doubleValue();
	}
	
	public static String convertMinutesToStringHourMinutes(int t){
		int hours = t / 60; //since both are ints, you get an int
		int minutes = t % 60;
		String out = String.format("%d:%02d", hours, minutes);
		return out;
	}
}
