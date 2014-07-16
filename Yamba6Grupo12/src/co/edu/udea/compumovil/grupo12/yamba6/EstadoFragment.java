package co.edu.udea.compumovil.grupo12.yamba6;

import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import co.edu.udea.compumovil.grupo12.yamba6.R;

import com.marakana.android.yamba.clientlib.YambaClient;
import com.marakana.android.yamba.clientlib.YambaClientException;



public class EstadoFragment extends Fragment implements OnClickListener {

	private static final String TAG = "EstadoFragmento";
	private EditText editarEstado;
	private Button subir;
	private TextView tContador;
	private int defaultTColor;
	SharedPreferences prefs;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		
		View view = inflater.inflate(R.layout.fragment_estado, container, false);
		
		editarEstado = (EditText)view.findViewById(R.id.Ttexto);
		subir = (Button)view.findViewById(R.id.bSubir);
		tContador = (TextView)view.findViewById(R.id.tContador);
		
		subir.setOnClickListener(this);
		defaultTColor = tContador.getTextColors().getDefaultColor();
		tContador.setTextColor(Color.GREEN);
		editarEstado.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void afterTextChanged(Editable s) {
				int contador=140 -editarEstado.length();
				tContador.setText(Integer.toString(contador));
				
				if(contador >10 && contador<140)
				{
					tContador.setTextColor(Color.GREEN);
				}
				else if(contador > 0 && contador <=10)
				{
					tContador.setTextColor(Color.BLUE);
				}
				else if(contador <=0)
				{
					tContador.setTextColor(Color.RED);
				}
			}
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}

		});
		
		
		return view;
		
	}
	
	@Override
	public void onClick(View v) {
		
		String status = editarEstado.getText().toString();
		Log.d(TAG, "onClicked with status: "+ status);
		
		new PostTask().execute(status);
	}
	
	private final class PostTask extends AsyncTask<String, Void, String>{
			
			@Override
			protected String doInBackground (String... params){
				
		
		try {
			SharedPreferences prefs = PreferenceManager
					.getDefaultSharedPreferences(getActivity()); 
					String username = prefs.getString("nombreusuario", ""); 
					String password = prefs.getString("contrasena", "");
					
					if (TextUtils.isEmpty(username) ||
							TextUtils.isEmpty(password)) { 
							getActivity().startActivity(
							new Intent(getActivity(), AjustesActivity.class));
							return "Por favor actualice su nombre de usuario y contraseña";
							}
					
					YambaClient cloud = new YambaClient(username, password);
					
					cloud.postStatus(params[0]);
					return "Actualización Exitosa";
					
			} catch (YambaClientException e) {
			e.printStackTrace();
			return "No se puede actualizar tu estado";
		}
		
		
	}
	//se ejecuta en el hilo ppal cuando doInBackground finaliza
		@Override
		protected void onPostExecute (String result){
			super.onPostExecute(result);
			
			Toast.makeText(EstadoFragment.this.getActivity(), result, Toast.LENGTH_LONG).show();
		}
	}
}

