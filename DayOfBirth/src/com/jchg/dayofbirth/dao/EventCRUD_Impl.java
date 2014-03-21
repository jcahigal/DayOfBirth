
package com.jchg.dayofbirth.dao;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Vector;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.jchg.dayofbirth.utils.Dates;

/**
 * @author Juan Carlos Hdez.
 * 
 * Last revision: 1/08/13
 */
public class EventCRUD_Impl extends SQLiteOpenHelper implements EventCRUD {

	// Methods of SQLiteOpenHelper

	public EventCRUD_Impl(Context context) {

		super(context, "events", null, 1);

	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		db.execSQL("CREATE TABLE events (" +

		"id INTEGER PRIMARY KEY AUTOINCREMENT, " +

		"name TEXT, date TEXT, breed TEXT, calId REAL, created TEXT, modified TEXT)");

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Consider that in new version could have to update tables

	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jchg.dayofbirth.bo.EventCRUD#createEvent(com.jchg.dayofbirth.dao.
	 * Event)
	 */
	@Override
	public void createEvent(Event newEvent) {
		SQLiteDatabase db = getWritableDatabase();

		String now = Dates.now();
		String eventDate= Dates.date2String(newEvent.getDate().getTime());
		String statement = "INSERT INTO events VALUES ( null, '" +
				newEvent.getName() + "', '" + eventDate + "', '" + newEvent.getBreed() + "', '" + newEvent.getCalId() +  "' , '" + now + "' , '" + now + "')";
		db.execSQL(statement);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jchg.dayofbirth.bo.EventCRUD#getEvent(int)
	 */
	@Override
	public Event getEvent(int id) {
		SQLiteDatabase db = getWritableDatabase();
		Cursor cursor = db.rawQuery("SELECT * FROM "
				+ "events WHERE id = '" + id + "'", null);
		
		if(cursor.moveToNext()){
			Event event = new Event();
			event.setId(id);
			event.setBreed(cursor.getString(3));
			Calendar cretated = new GregorianCalendar();
			cretated.setTime(Dates.string2Date(cursor.getString(5)));
			event.setCreated(cretated);
			event.setCalId(cursor.getLong(4));
			Calendar date = new GregorianCalendar();
			date.setTime(Dates.string2Date(cursor.getString(2)));
			event.setDate(date);
			Calendar modified = new GregorianCalendar();
			modified.setTime(Dates.string2Date(cursor.getString(6)));
			event.setModified(modified);
			event.setName(cursor.getString(1));
			
			return event;
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jchg.dayofbirth.bo.EventCRUD#getEvents()
	 */
	@Override
	public Vector<Event> getEvents() {
		Vector<Event> events= new Vector<Event>();
		SQLiteDatabase db = getWritableDatabase();
		Cursor cursor = db.rawQuery("SELECT * FROM events", null);
		
		while(cursor.moveToNext()){
			//only future events are interesting
			Calendar date = new GregorianCalendar();
			date.setTime(Dates.string2Date(cursor.getString(2)));
			if(date.getTimeInMillis()>Calendar.getInstance().getTimeInMillis()){
				Event event = new Event();
				event.setDate(date);
				event.setId(cursor.getInt(0));
				event.setBreed(cursor.getString(3));
				event.setCalId(cursor.getLong(4));
				Calendar cretated = new GregorianCalendar();
				cretated.setTime(Dates.string2Date(cursor.getString(5)));
				event.setCreated(cretated);
				Calendar modified = new GregorianCalendar();
				modified.setTime(Dates.string2Date(cursor.getString(6)));
				event.setModified(modified);
				event.setName(cursor.getString(1));
				events.add(event);
			}
		}
		return events;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jchg.dayofbirth.bo.EventCRUD#getEvents(com.jchg.dayofbirth.dao.Event)
	 */
	@Override
	public Vector<Event> getEvents(Event eventInfo) {
		
		Vector<Event> events= new Vector<Event>();
		SQLiteDatabase db = getWritableDatabase();
		Cursor cursor = db.rawQuery("SELECT * FROM events", null);
		
		while(cursor.moveToNext()){
			//only future events are interesting
			Calendar date = new GregorianCalendar();
			date.setTime(Dates.string2Date(cursor.getString(2)));
			if(date.getTimeInMillis()>Calendar.getInstance().getTimeInMillis()){
				Event event = new Event();
				event.setDate(date);
				event.setId(cursor.getInt(0));
				event.setBreed(cursor.getString(3));
				event.setCalId(cursor.getLong(4));
				Calendar cretated = new GregorianCalendar();
				cretated.setTime(Dates.string2Date(cursor.getString(5)));
				event.setCreated(cretated);
				Calendar modified = new GregorianCalendar();
				modified.setTime(Dates.string2Date(cursor.getString(6)));
				event.setModified(modified);
				event.setName(cursor.getString(1));
				events.add(event);
			}
		}
		return events;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jchg.dayofbirth.bo.EventCRUD#deleteEvent(int)
	 */
	@Override
	public boolean deleteEvent(int id) {
		SQLiteDatabase db = getWritableDatabase();

		db.execSQL("DELETE FROM events WHERE id = '"+id+"'");
		
		//it is possible to make a test about the delete and return false
		return true;
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
		SQLiteDatabase db = getWritableDatabase();

		int id = eventUpdated.getId();
		
		//null == -1
		if(id != -1){
			db.execSQL("UPDATE events SET name='"+eventUpdated.getName()+"', breed='"+eventUpdated.getBreed()+"', date='"+eventUpdated.getDate()+"', modified = '"+Dates.now()+"' WHERE id = '"+id+"'");
		}
		
		return true;
	}

}