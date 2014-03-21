/**
 * 
 */
package com.jchg.dayofbirth.dao;

import java.util.Calendar;

import com.jchg.dayofbirth.utils.Dates;


/**
 * @author Juan Carlos Hdez.
 * 
 * Last revision: 11/07/13
 */
public class Event {
	
	/**
	 * internal Id of the event
	 */
	private int id;
	
	/**
	 * the name of the pregnant
	 */
	private String name;
	
	/**
	 * the date when it or she was pregnant
	 */
	private Calendar date;
	
	/**
	 * possible breed
	 */
	public enum Breed {Human,Dog,Cat,Horse,Cow,Sheep,Goat,Pig};
	
	/**
	 * the breed of it or she
	 */
	private String breed;
	
	/**
	 * the date when the event was created
	 */
	private Calendar created;
	
	/**
	 * last date when the event was modified
	 */
	private Calendar modified;
	
	/**
	 * id generate by android for this event into the calendar
	 */
	private long calId;
	
	/**
	 * separator between name and id
	 */
	private final String NAME_ID_SEPARATOR = "/";

	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}


	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	
	/**
	 * @return the date
	 */
	public Calendar getDate() {
		return date;
	}

	
	/**
	 * @param date the date to set
	 */
	public void setDate(Calendar date) {
		this.date = date;
	}

	
	/**
	 * @return the breed
	 */
	public String getBreed() {
		return breed;
	}

	
	/**
	 * @param breed the breed to set
	 */
	public void setBreed(String breed) {
		this.breed = breed;
	}

	
	/**
	 * @return the created
	 */
	public Calendar getCreated() {
		return created;
	}

	
	/**
	 * @param created the created to set
	 */
	public void setCreated(Calendar created) {
		this.created = created;
	}

	
	/**
	 * @return the modified
	 */
	public Calendar getModified() {
		return modified;
	}

	
	/**
	 * @param modified the modified to set
	 */
	public void setModified(Calendar modified) {
		this.modified = modified;
	}
	
		
	/**
	 * @return the calId
	 */
	public long getCalId() {
		return calId;
	}


	
	/**
	 * @param calId the calId to set
	 */
	public void setCalId(long calId) {
		this.calId = calId;
	}


	/**
	 * generate the showed info to user
	 */
	public String showerInfo(){
		StringBuffer sb=new StringBuffer(name);
		sb=sb.append(NAME_ID_SEPARATOR).append(id).append(" (").append(breed);
		sb=sb.append(")\n").append(Dates.dateFormat.format(date.getTime()));
		return sb.toString();
	}
	
	
	public String toString(){
		StringBuffer sb=new StringBuffer("nombre: ");
		sb=sb.append(name).append(" (id: ").append(id).append("), raza: ").append(breed);
		sb=sb.append(", date: ").append(Dates.dateFormat.format(date.getTime())).append(", created: ").append(Dates.dateFormat.format(created.getTime())).append(", modified: ").append(Dates.dateFormat.format(modified.getTime()));
		return sb.toString();
	}
	
}
