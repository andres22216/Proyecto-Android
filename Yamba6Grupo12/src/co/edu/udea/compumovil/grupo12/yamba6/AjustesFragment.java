package co.edu.udea.compumovil.grupo12.yamba6;

import co.edu.udea.compumovil.grupo12.yamba6.R;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;

public class AjustesFragment extends PreferenceFragment implements OnSharedPreferenceChangeListener{
	
	private SharedPreferences preferencia;

	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.settings);
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		preferencia = PreferenceManager.getDefaultSharedPreferences(getActivity());
		preferencia.registerOnSharedPreferenceChangeListener(this);
	}

	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
			String key) {
		// TODO Auto-generated method stub
		
	}
	
	

	
	
}
