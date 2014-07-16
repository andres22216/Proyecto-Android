package co.edu.udea.compumovil.grupo12.yamba6;


import co.edu.udea.compumovil.grupo12.yamba6.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class EstadoActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_estado);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.principal, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		switch (item.getItemId()) {
		case R.id.action_settings:
			startActivity(new Intent(this,AjustesActivity.class));
			return true;
			
		case R.id.action_subir:
			startActivity(new Intent("com.edu.udea.compumovil.grupo12.yamba6"));
			//startActivity(new Intent(this,EstadoActivity.class));
			return true;
			
			

		default:
			return false;
		}
	}


		
	}