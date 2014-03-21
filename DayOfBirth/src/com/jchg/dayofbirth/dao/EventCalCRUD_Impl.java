/**
 * 
 */

package com.jchg.dayofbirth.dao;

import java.util.TimeZone;
import java.util.Vector;

import com.jchg.dayofbirth.R;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.provider.CalendarContract.Events;


/**
 * Class for calendar with API levels equal or more than 14
 * @author Juan Carlos Hdez.
 * 
 *         Last revision: 11/08/13
 */
public class EventCalCRUD_Impl implements EventCalCRUD {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jchg.dayofbirth.bo.EventCRUD#createEvent(com.jchg.dayofbirth.dao.
	 * Event)
	 */
	@Override
	public long createEvent(Event newEvent, Context context) {
		long startMillis = 0;
		
		
		ContentResolver cr = context.getContentResolver();
		
		startMillis = newEvent.getDate().getTimeInMillis();
		
		ContentValues values = new ContentValues();
		values.put(Events.DTSTART, startMillis);
		values.put(Events.DTEND, startMillis+3600000);
		values.put(Events.ALL_DAY, 1);

		values.put(Events.TITLE, newEvent.getName());
		values.put(Events.DESCRIPTION, newEvent.getBreed());
		//values.put(Events.CALENDAR_ID, calendarID);
		values.put(Events.CALENDAR_ID, CalendarManagement.getCalendarId(cr, context.getString(R.string.app_name))); 
		values.put(Events.HAS_ALARM, 0); // 0 for false, 1 for true
		TimeZone tz = TimeZone.getDefault();
		values.put(Events.EVENT_TIMEZONE, tz.getID());
		Uri uri = cr.insert(Events.CONTENT_URI, values);

		// TODO v2, several calendars
		//get the event ID that is the last element in the Uri
		long eventID = Long.parseLong(uri.getLastPathSegment());

		return eventID;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jchg.dayofbirth.bo.EventCRUD#getEvent(int)
	 */
	@Override
	public Event getEvent(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jchg.dayofbirth.bo.EventCRUD#getEvents()
	 */
	@Override
	public Vector<Event> getEvents() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jchg.dayofbirth.bo.EventCRUD#getEvents(com.jchg.dayofbirth.dao.Event)
	 */
	@Override
	public Vector<Event> getEvents(Event eventInfo) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jchg.dayofbirth.bo.EventCRUD#deleteEvent(int)
	 */
	@Override
	public boolean deleteEvent(int id) {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jchg.dayofbirth.bo.EventCRUD#updateEvent(com.jchg.dayofbirth.dao.
	 * Event)
	 */
	@Override
	public boolean updateEvent(Event eventUpdated) {
		return false;
	}
	
	
}
