/**
 * 
 */
package com.jchg.dayofbirth.utils;

import java.util.Calendar;

import com.jchg.dayofbirth.R;


/**
 * @author Juan Carlos Hdez.
 * 
 * Last revision: 1/08/13
 */
public class FinalCountDown {
	
	/**
	 * Time (days) of gestation of the different breeds
	 *
	        put("Human", 270);
	        put("Dog", 62);
	        put("Cat", 60);
	        put("Horse", 340);
	        put("Cow", 280);
	        put("Sheep", 150);
	        put("Goat", 148);
	        put("Pig", 115);

*/
	
	/**
	 * Get the day of the birth consider the input parameters
	 * @param breedId 
	 * @param date date of the fencundation
	 * @return date of the birth
	 */
	public static Calendar getDayOfBirth(int breedId, Calendar date){
		Calendar bithDay=(Calendar)date.clone();
		if(date!=null){
			int fencundationDay = date.get(Calendar.DAY_OF_YEAR);
			int breedGestationDays = getFecundationDays(breedId);
			int totalDays = fencundationDay + breedGestationDays;
			if(totalDays > 365)
			{
				totalDays = totalDays - 365;
				bithDay.set(Calendar.YEAR,(date.get(Calendar.YEAR)+1));
			}
			bithDay.set(Calendar.DAY_OF_YEAR, totalDays);
			
		}
		
		return bithDay;
		
	}
	
	/**
	 * Get the day of the birth consider the input parameters
	 * @param breedId 
	 * @param date date of the fencundation
	 * @param previosDays number of previous days to the birth 
	 * @return date of the birth
	 */
	public static Calendar getDayOfBirth(int breedId, Calendar date, int previosDays){
		
		Calendar bithDay=(Calendar)date.clone();
		if(date!=null){
			int fencundationDay = date.get(Calendar.DAY_OF_YEAR);
			int breedGestationDays = getFecundationDays(breedId);
			int totalDays = fencundationDay + breedGestationDays - previosDays;
			if(totalDays > 365)
			{
				totalDays = totalDays - 365;
				bithDay.set(Calendar.YEAR,(date.get(Calendar.YEAR)+1));
			}
			bithDay.set(Calendar.DAY_OF_YEAR, totalDays);
			
		}
		
		return bithDay;
		
	}

	/**
	 * Get the number of days of fecundation of the breed 
	 * @param breed selected id 
	 * @return e number of days
	 */
	private static int getFecundationDays(int id)
	{
		int days=0;
		// Check which radio button was clicked
	    switch(id) {
	        case R.id.breed_human:
	            days=270;
	            break;
	        case R.id.breed_dog:
	            days=62;
	            break;
	        case R.id.breed_cat:
	            days=60;
	            break;
	        case R.id.breed_horse:
	            days=340;
	            break;
	        case R.id.breed_cow:
	            days=280;
	            break;
	        case R.id.breed_sheep:
	            days=150;
	            break;
	        case R.id.breed_goat:
	            days=148;
	            break;
	        case R.id.breed_pig:
	            days=115;
	            break;
	       
	    }
		return days;
	}

}
