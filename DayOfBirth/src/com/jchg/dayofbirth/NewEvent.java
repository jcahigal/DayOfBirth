
package com.jchg.dayofbirth;

import java.util.Calendar;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.jchg.dayofbirth.dao.Event;
import com.jchg.dayofbirth.dao.EventCRUD;
import com.jchg.dayofbirth.dao.EventCRUD_Impl;
import com.jchg.dayofbirth.dao.EventCalCRUD;
import com.jchg.dayofbirth.dao.EventCalCRUD_Impl;
import com.jchg.dayofbirth.dao.EventCalLT14CRUD_Impl;
import com.jchg.dayofbirth.utils.FinalCountDown;

/**
 * @author Juan Carlos Hdez.
 * 
 *         Last revision: 11/07/13
 */
public class NewEvent extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_event);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		super.onCreateOptionsMenu(menu);

		return true;

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);
		return true;
	}

	/**
	 * create a new event
	 * @param view 
	 */
	public void runCreateEvent(View view) {

		EditText nameIdET = (EditText) findViewById(R.id.nameId);
		String nameIdETValue = nameIdET.getText().toString();

		RadioGroup breedRG = (RadioGroup) findViewById(R.id.breed);

		int selectedBreed = breedRG.getCheckedRadioButtonId();
		RadioButton breedRB = (RadioButton) findViewById(selectedBreed);
		String breedRBValue = breedRB.getText().toString();

		DatePicker breedDP = (DatePicker) findViewById(R.id.dateSelect);
		int month = breedDP.getMonth();
		int year = breedDP.getYear();
		int day = breedDP.getDayOfMonth();

		StringBuffer created_text = new StringBuffer(getString(R.string.created_text));
		created_text = created_text.append(" ").append(nameIdETValue);
		created_text = created_text.append(" (").append(breedRBValue).append("), ");
		created_text = created_text.append(day).append("/").append(month).append("/").append(year);
		
		// store info
		Event newE = new Event();
		newE.setName(nameIdETValue);
		newE.setBreed(breedRBValue);
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month, day);
		newE.setDate(FinalCountDown.getDayOfBirth(selectedBreed, calendar));

		EventCalCRUD eventCalCRUD = null;

		if (Build.VERSION.SDK_INT >= 14) {
			eventCalCRUD = new EventCalCRUD_Impl();
		} else
			eventCalCRUD = new EventCalLT14CRUD_Impl();

		long calEventId = eventCalCRUD.createEvent(newE, this);
		newE.setCalId(calEventId);

		EventCRUD eventCRUD = new EventCRUD_Impl(this);

		eventCRUD.createEvent(newE);

		// additional notifications
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

		boolean isNotifEnable = prefs.getBoolean("notifEnable", true);
		int notifDays = 0;

		if (isNotifEnable) {
			Event notif = newE;
			notifDays = Integer.valueOf(prefs.getString("notifDays", "7"));
			notif.setName(notif.getName() + this.getString(R.string.notifDays_text) + notifDays);
			Calendar notifDate = FinalCountDown.getDayOfBirth(selectedBreed, calendar, notifDays);
			notif.setDate(notifDate);
			eventCalCRUD.createEvent(notif, this);
		}

		Toast.makeText(this, created_text.toString(), Toast.LENGTH_LONG).show();

	}

}
