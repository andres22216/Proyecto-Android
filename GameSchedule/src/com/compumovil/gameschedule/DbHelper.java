package com.compumovil.gameschedule;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper  extends SQLiteOpenHelper{

	public static final String baseDatos = "gameSchedule";
	public static final int dbVersion = 1;
	
	public DbHelper (Context context)
	{
		super(context, baseDatos, null, dbVersion);
	}
	
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(ManejadorBaseDatos.creacionTablaUsuario);
		db.execSQL(ManejadorBaseDatos.creacionTablaEvento);
		
			
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}
