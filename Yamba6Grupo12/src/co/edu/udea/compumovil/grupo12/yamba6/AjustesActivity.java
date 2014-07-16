package co.edu.udea.compumovil.grupo12.yamba6;

import android.app.Activity;
import android.os.Bundle;

public class AjustesActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		//verifica si esta actividad fue creada antes
		if(savedInstanceState == null){
			
			//creamos un fragmento
			
			AjustesFragment fragment = new AjustesFragment();
			getFragmentManager().beginTransaction()
			.add(android.R.id.content, fragment, fragment.getClass().getSimpleName()).commit();
			
		}
	};
	
	

}
