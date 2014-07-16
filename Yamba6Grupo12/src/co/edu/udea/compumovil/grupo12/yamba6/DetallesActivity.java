package co.edu.udea.compumovil.grupo12.yamba6;

import android.app.Activity;
import android.os.Bundle;

public class DetallesActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Check if this activity was created before
		if (savedInstanceState == null) { //
				// Create a fragment
				DetallesFragment fragment = new DetallesFragment(); //
				getFragmentManager().beginTransaction().add(android.R.id.content, fragment,
				fragment.getClass().getSimpleName()).commit(); //
		}
	}
}
