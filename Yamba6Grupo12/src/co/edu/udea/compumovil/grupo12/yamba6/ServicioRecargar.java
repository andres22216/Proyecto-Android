package co.edu.udea.compumovil.grupo12.yamba6;

import java.util.List;

import android.app.IntentService;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.marakana.android.yamba.clientlib.YambaClient;
import com.marakana.android.yamba.clientlib.YambaClient.Status;
import com.marakana.android.yamba.clientlib.YambaClientException;

public class ServicioRecargar extends IntentService{

private static final String TAG = ServicioRecargar.class.getSimpleName(); 
	
	public ServicioRecargar() {
		super(TAG);
		
	}

	
	
	@Override
	public void onCreate() {
		// es llamado cuando el servicio se crea
		super.onCreate();
		Log.d(TAG, "onCreated");
	}

	

//	// siempre que se llama start service
//	@Override
//	public int onStartCommand(Intent intent, int flags, int startId) {
//		// TODO Auto-generated method stub
//		super.onStartCommand(intent, flags, startId);
//		Log.d(TAG, "onStarted");
//		return START_STICKY; //dice si el servicio se encuentra detenido o corriendo
//	}
//
//	//es usado en los servicios para los servicios bound (el ciclo de vida esta ligado a la actividad)
//	@Override
//	public IBinder onBind(Intent intent) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	protected void onHandleIntent(Intent intent) {
		// TODO Auto-generated method stub
		SharedPreferences prefs = PreferenceManager
					.getDefaultSharedPreferences(this);
		final String username = prefs.getString("nombreusuario","");
		final String password = prefs.getString("contrasena", "");
		
		if(TextUtils.isEmpty(username)||TextUtils.isEmpty(password)){
			Toast.makeText(this,"por favor modifique su nombre de usuario y contraseña", 
					Toast.LENGTH_LONG).show();
			
			return;
		}
		
		Log.d(TAG, "onStarted");
		
		////// creamos una instancia de DbHelper para trabajar sobre la base de datos
		
//		DbHelper dbHelper = new DbHelper(this); //crear una instancia de dbhelper y pasa el contexto
//		SQLiteDatabase db = dbHelper.getWritableDatabase(); 
		
		ContentValues valores = new ContentValues(); //es una estructura de datos de parejas nombre, valor
		
		//////////
		
		YambaClient cloud = new YambaClient(username, password);
		
		try {
			
			int count =0;
			List<Status> timeline = cloud.getTimeline(20);
			
			for(Status status : timeline){
				
				valores.clear();
				valores.put(EstadoContract.Column.ID, status.getId());
				valores.put(EstadoContract.Column.USUARIO, status.getUser());
				valores.put(EstadoContract.Column.MENSAJE, status.getMessage());
				valores.put(EstadoContract.Column.CREATED_AT, status.getCreatedAt().getTime());
				
				//insertamos sin utilizar una declaracion SQL y si encontramos cosas repetidas ignoramos el 
				//conflicto que se genera
				//db.insertWithOnConflict(EstadoContract.tabla, null, valores, SQLiteDatabase.CONFLICT_IGNORE); 
				Uri uri = getContentResolver().insert(EstadoContract.CONTENT_URI, valores);
				
				if(uri!=null){
					
					count++;
					Log.d(TAG, String.format("%s: %s", status.getUser(),status.getMessage()));
					
				}
				
			}
			
			
			if (count > 0) {
				sendBroadcast(new Intent(
				"co.edu.udea.compumovil.grupo12.yamba6.action.NEW_STATUSES")
				.putExtra("count", count)); //
				}
			
			
		} catch (YambaClientException e) {
			Log.e(TAG, "No se pueden traer los datos", e);
			e.printStackTrace();
		}
		
		return;
		
	}
	
	@Override
	public void onDestroy() {
		// es un buen lugar para limpiar las cosas que fueron inicializadas en el onCreate
		super.onDestroy();
		Log.d(TAG, "onDestroyed");
	}

}
