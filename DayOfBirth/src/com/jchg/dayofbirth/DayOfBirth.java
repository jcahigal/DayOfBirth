
package com.jchg.dayofbirth;

import java.util.HashMap;
import java.util.Vector;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Toast;

import com.jchg.dayofbirth.dao.Event;
import com.jchg.dayofbirth.dao.EventCRUD;
import com.jchg.dayofbirth.dao.EventCRUD_Impl;

/**
 * @author Juan Carlos Hdez.
 * Main application class
 *         Last revision: 11/07/13
 */
public class DayOfBirth extends Activity {

	private OnClickListener l = null;

	private HashMap<Integer, String> eventsDetails = new HashMap<Integer, String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.day_of_birth);

		l = new OnClickListener() {

			public void onClick(View view) {
				runEventDetails(view.getId());
			}

		};
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.menu, menu);

		return true;

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.about:
			runAbout(null);
			break;

		case R.id.config:
			runSettings(null);
			break;
		}

		return true;
	}

	@Override
	protected void onResume() {

		super.onResume();

		// refresh all events
		getAllEvents();

	}

	/**
	 * launch "about" information
	 * @param view 
	 */
	public void runAbout(View view) {

		Intent intent = new Intent(this, About.class);
		// intent.putExtra("info_Extra", "valor object");
		startActivity(intent);

	}

	/**
	 * launch settings
	 * @param view 
	 */
	public void runSettings(View view) {

		Intent intent = new Intent(this, Settings.class);
		startActivity(intent);

	}

	/**
	 * launch new event activity
	 * @param view 
	 */
	public void runNewEvent(View view) {

		Intent intent = new Intent(this, NewEvent.class);
		startActivity(intent);

	}

	/**
	 * launch information of an event
	 * @param view 
	 */
	public void runEventDetails(int event) {
		String eventDetails = eventsDetails.get(event);

		final Toast details = Toast.makeText(this, eventDetails.toString(), Toast.LENGTH_LONG);

		details.show();

		new CountDownTimer(6000, 1000) {

			public void onTick(long millisUntilFinished) {
				details.show();
			}

			public void onFinish() {
				// details.show();
			}

		}.start();

	}

	/**
	 * get all events list
	 */
	public void getAllEvents() {
		EventCRUD eventCRUD = new EventCRUD_Impl(this);
		Vector<Event> allEvents = eventCRUD.getEvents();
		LinearLayout ll = (LinearLayout) findViewById(R.id.main_ll);
		LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

		for (Event e : allEvents) {
			int eventId = e.getId();
			if (findViewById(eventId) == null) {
				Button btn = new Button(this);
				btn.setId(eventId);
				eventsDetails.put(eventId, e.toString());
				btn.setText(e.showerInfo());
				btn.setOnClickListener(l);
				ll.addView(btn, lp);
			}
		}

	}
}
