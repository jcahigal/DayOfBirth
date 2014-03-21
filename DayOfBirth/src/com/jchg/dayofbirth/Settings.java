/**
 * 
 */

package com.jchg.dayofbirth;

import android.app.Activity;
import android.os.Bundle;

/**
 * @author Juan Carlos Hdez.
 * 
 *         Last revision: 13/03/14
 */

public class Settings extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		getFragmentManager().beginTransaction().replace(android.R.id.content, new SettingsFragment()).commit();
	}

}
