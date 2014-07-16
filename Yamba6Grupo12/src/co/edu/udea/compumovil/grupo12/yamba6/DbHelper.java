package co.edu.udea.compumovil.grupo12.yamba6;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbHelper extends SQLiteOpenHelper{
	
	private static final String TAG = DbHelper.class.getSimpleName();
	
	public DbHelper(Context context) {
		super(context, EstadoContract.dbNombre, null, EstadoContract.dbVersion);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String sql = String.format("create table %s (%s int primary key, %s text, %s text, %s int)",
				EstadoContract.tabla,
				EstadoContract.Column.ID,
				EstadoContract.Column.USUARIO,
				EstadoContract.Column.MENSAJE,
				EstadoContract.Column.CREATED_AT);
		
		Log.d(TAG, "onCreate with SQL: "+ sql);
		db.execSQL(sql);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("drop table if exists " + EstadoContract.tabla);
		onCreate(db);
		
		
	}

	
	
	

}
