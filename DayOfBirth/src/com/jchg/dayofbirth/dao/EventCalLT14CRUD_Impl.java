/**
 * 
 */

package com.jchg.dayofbirth.dao;

import java.util.TimeZone;
import java.util.Vector;

import android.content.Context;
import android.content.Intent;


/**
 * Class for calendar with API levels equal or more than 14
 * @author Juan Carlos Hdez.
 * 
 *         Last revision: 11/08/13
 */
public class EventCalLT14CRUD_Impl implements EventCalCRUD {

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
		
		startMillis = newEvent.getDate().getTimeInMillis();
		
		Intent intent = new Intent(Intent.ACTION_EDIT);
		intent.setType("vnd.android.cursor.item/event");
		intent.putExtra("beginTime", startMillis);
		intent.putExtra("allDay", true);
		intent.putExtra("endTime", startMillis+3600000);
		intent.putExtra("title", newEvent.getName());
		intent.putExtra("description", newEvent.getBreed());
		TimeZone tz = TimeZone.getDefault();
		intent.putExtra("eventTimezone",tz.getID());
		intent.setAction(Intent.ACTION_INSERT);
		context.startActivity(intent);

		return -1;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jchg.dayofbirth.bo.EventCRUD#getEvent(int)
	 */
	@Override
	public Event getEvent(int id) {
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
