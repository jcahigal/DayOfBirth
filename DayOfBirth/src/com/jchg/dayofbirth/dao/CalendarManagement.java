/**
 * 
 */

package com.jchg.dayofbirth.dao;


import java.util.TimeZone;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CalendarContract;

/**
 * @author Juan Carlos Hdez.
 * 
 **         Last revision: 15/08/13
 */
public class CalendarManagement {

	/**
	 * Name of the calendar
	 */
	private static String CALENDAR_NAME = "DayOfBirth";

	/**
	 * Get the calendar where events will be added
	 * 
	 * @param contentResolver
	 * @param appName
	 * @return calendar id
	 */
	public static long getCalendarId(ContentResolver contentResolver, String appName) {
		long calId = -1;
		String[] CALENDARS_PROJECTION = { CalendarContract.Calendars._ID,
				// CalendarContract.Calendars.ACCOUNT_NAME,
				// CalendarContract.Calendars.ACCOUNT_TYPE,
				CalendarContract.Calendars.NAME, };

		Cursor cursor = contentResolver.query(CalendarContract.Calendars.CONTENT_URI, CALENDARS_PROJECTION, null, null, null);
		int columnNameIndex = cursor.getColumnIndex(CalendarContract.Calendars.NAME);
		int columnIdIndex = cursor.getColumnIndex(CalendarContract.Calendars._ID);
		while (cursor.moveToNext()) {
			if (CALENDAR_NAME.equals(cursor.getString(columnNameIndex))) {
				calId = cursor.getLong(columnIdIndex);
				break;
			}

		}

		if (calId == -1) {
			calId = addCalendar(contentResolver, appName);
		}

		return calId;
	}

	/**
	 * Create the calendar
	 * 
	 * @param contentResolver
	 * @param appName
	 * @return calendarId
	 */
	public static long addCalendar(ContentResolver contentResolver, String appName) {
		TimeZone timeZone = TimeZone.getDefault();

		ContentValues contentValues = new ContentValues();

		// TODO v2 use a remote calendar

		// contentValues.put(CalendarContract.Calendars.ACCOUNT_NAME,
		// "DayOfBirth" + "");
		// contentValues.put(CalendarContract.Calendars.ACCOUNT_TYPE,
		// "com.google" ); //CalendarContract.ACCOUNT_TYPE_LOCAL );
		// contentValues.put(CalendarContract.Calendars.ACCOUNT_TYPE,
		// CalendarContract.ACCOUNT_TYPE_LOCAL );
		// contentValues.put(CalendarContract.Calendars.OWNER_ACCOUNT,
		// "programaando@gmail.com");

		contentValues.put(CalendarContract.Calendars.NAME, CALENDAR_NAME);
		contentValues.put(CalendarContract.Calendars.CALENDAR_DISPLAY_NAME, appName);
		contentValues.put(CalendarContract.Calendars.CALENDAR_COLOR, 0xee00ff08);
		contentValues.put(CalendarContract.Calendars.CALENDAR_ACCESS_LEVEL, CalendarContract.Calendars.CAL_ACCESS_CONTRIBUTOR);

		contentValues.put(CalendarContract.Calendars.VISIBLE, 1);
		// contentValues.put(CalendarContract.Calendars.SYNC_EVENTS, 1);
		contentValues.put(CalendarContract.Calendars.SYNC_EVENTS, 0);
		contentValues.put(CalendarContract.Calendars.CALENDAR_TIME_ZONE, timeZone.getID());

		Uri calendarUri = CalendarContract.Calendars.CONTENT_URI;
		//calendarUri =  calendarUri.buildUpon().appendQueryParameter(CalendarContract.CALLER_IS_SYNCADAPTER, "true")
		calendarUri = calendarUri.buildUpon().appendQueryParameter(CalendarContract.CALLER_IS_SYNCADAPTER, "false")
		// .appendQueryParameter(CalendarContract.Calendars.ACCOUNT_NAME,
		// "programaando@gmail.com")
		// .appendQueryParameter(CalendarContract.Calendars.ACCOUNT_TYPE,
		// "com.google") // CalendarContract.ACCOUNT_TYPE_LOCAL
		// .appendQueryParameter(CalendarContract.Calendars.ACCOUNT_TYPE,
		// CalendarContract.ACCOUNT_TYPE_LOCAL )
				.build();

		try {
			Uri result = contentResolver.insert(calendarUri, contentValues);
			return Long.parseLong(result.getLastPathSegment());
		} catch (Exception exception) {
			// Log.e("trace", exception.toString());
		}

		return 1;
	}

}
