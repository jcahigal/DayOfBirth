/**
 * 
 */

package com.jchg.dayofbirth.utils;

import android.annotation.SuppressLint;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Juan Carlos Hdez.
 * 
 */
public class Dates {

	/**
	 * Formatter for dates
	 */
	@SuppressLint("SimpleDateFormat")
	public final static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

	/**
	 * It returns the current date as string
	 * 
	 * @return date
	 */
	public static String now() {
		return dateFormat.format(new Date());
	}

	/**
	 * transform from string to date
	 * 
	 * @param stringDate
	 * @return date
	 */
	public static Date string2Date(String stringDate) {

		try {
			return dateFormat.parse(stringDate);
		} catch (ParseException e) {
			return null;
		}

	}
	
	/**
	 * transform from date to string
	 * 
	 * @param stringDate
	 * @return date in string format
	 */
	public static String date2String(Date date) {
		return dateFormat.format(date);
	}
}
