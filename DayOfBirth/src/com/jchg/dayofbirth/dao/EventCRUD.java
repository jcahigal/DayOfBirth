/**
 * 
 */
package com.jchg.dayofbirth.dao;

import java.util.Vector;


/**
 * @author Juan Carlos Hdez.
 * 
 * Last revision: 11/07/13
 */
public interface EventCRUD {
	
	/**
	 * create new event
	 * @param newEvent info
	 */
	public void createEvent(Event newEvent);
	
	/**
	 * get event
	 * @param id
	 * @return the event or null
	 */
	public Event getEvent(int id);
	
	/**
	 * get all events
	 * @return event list or null
	 */
	public Vector<Event> getEvents();
	
	/**
	 * Get event using info. of the event like a filter
	 * @param eventInfo
	 * @return event list or null
	 */
	public Vector<Event> getEvents(Event eventInfo);
	
	/**
	 * delete a event
	 * @param id
	 * @return true if there is any event deleted
	 */
	public boolean deleteEvent(int id);
	
	/**
	 * update a event
	 * @param id
	 * @return true if there is any event updated
	 */
	public boolean updateEvent(Event eventUpdated);

}
