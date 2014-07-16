package co.edu.udea.compumovil.grupo12.yamba6;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

public class EstadoProvider extends ContentProvider{

	private static final String TAG = EstadoProvider.class.getSimpleName();
	private DbHelper dbhelper;
	
	////////definir el tipo MIME
	
	private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);
	static{
		
		sURIMatcher.addURI(EstadoContract.AUTHORITY,  EstadoContract.tabla, EstadoContract.STATUS_DIR);
		sURIMatcher.addURI(EstadoContract.AUTHORITY, EstadoContract.tabla + "/#", EstadoContract.STATUS_ITEM );
		
		
	}
	
	
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		
		String where;
		
		switch(sURIMatcher.match(uri)){
		
		case EstadoContract.STATUS_DIR:
			where = (selection==null) ? "1" :selection;
			break;
			
		case EstadoContract.STATUS_ITEM:
			long id = ContentUris.parseId(uri);
			where = EstadoContract.Column.ID + "=" + id +(TextUtils.isEmpty(selection) ? "" : "and ( "+selection+" )");
			break;
			
		default:
			throw new IllegalArgumentException("illegal uri: "+ uri);
		}
		
		SQLiteDatabase db = dbhelper.getWritableDatabase();
		int ret = db.delete(EstadoContract.tabla, where, selectionArgs);
		
		if(ret > 0){
			
			getContext().getContentResolver().notifyChange(uri, null);
			
			
		}
		
		Log.d(TAG, "deleted records: "+ret);
		return ret;
		
	}

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		switch (sURIMatcher.match(uri)) {
		case EstadoContract.STATUS_DIR:
			Log.d(TAG, "gotType: " + EstadoContract.STATUS_TYPE_DIR);
			return EstadoContract.STATUS_TYPE_DIR;
		case EstadoContract.STATUS_ITEM:
			Log.d(TAG, "gotType: " + EstadoContract.STATUS_TYPE_ITEM);
			return EstadoContract.STATUS_TYPE_ITEM;

		default:
			throw new IllegalArgumentException("Ilegal Uri: " + uri);
		}
		
		
		
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// TODO Auto-generated method stub
		Uri ret = null;
		
		if(sURIMatcher.match(uri)!= EstadoContract.STATUS_DIR){
			
			throw new IllegalArgumentException("Ilegal Uri: " + uri);
			
		}
		
		SQLiteDatabase db = dbhelper.getWritableDatabase();
		long rowId = db.insertWithOnConflict(EstadoContract.tabla, null, values, SQLiteDatabase.CONFLICT_IGNORE);
		
		if(rowId!=-1){
			
			long id = values.getAsLong(EstadoContract.Column.ID);
			ret = ContentUris.withAppendedId(uri, id);
			Log.d(TAG, "inserted uri"+ ret);
			
			getContext().getContentResolver().notifyChange(uri, null);
			
		}
		
		return ret;
	}

	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub
		
		dbhelper = new DbHelper(getContext());
		Log.d(TAG, "onCreated");
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		// TODO Auto-generated method stub
		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
		qb.setTables( EstadoContract.tabla);
		
		switch(sURIMatcher.match(uri)){
		
		case EstadoContract.STATUS_DIR:
			break;
		case EstadoContract.STATUS_ITEM:
			qb.appendWhere(EstadoContract.Column.ID + "="+uri.getLastPathSegment());
			break;
		default:
			throw new IllegalArgumentException("illegal uri: "+uri);
		}
		
		String orderBy = (TextUtils.isEmpty(sortOrder)) ? EstadoContract.DEFAULT_SORT:sortOrder;
		SQLiteDatabase db = dbhelper.getReadableDatabase();
		Cursor cursor = qb.query(db, projection, selection, selectionArgs, null, null, orderBy);
		
		cursor.setNotificationUri(getContext().getContentResolver(), uri);
		
		Log.d(TAG, "Queried records: "+ cursor.getCount());
		
		return cursor;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		String where;
		
		switch(sURIMatcher.match(uri)){
		
		case EstadoContract.STATUS_DIR:
			where = selection;
			break;
			
		case EstadoContract.STATUS_ITEM:
			long id = ContentUris.parseId(uri);
			where = EstadoContract.Column.ID+ "=" + id +(TextUtils.isEmpty(selection)? "" : "and ( "+selection+" )");
			break;
			
		default:
			throw new IllegalArgumentException("illegal uri: "+ uri);
		}
		
		SQLiteDatabase db = dbhelper.getWritableDatabase();
		int ret = db.update(EstadoContract.tabla, values, where, selectionArgs);
		
		if(ret > 0){
			
			getContext().getContentResolver().notifyChange(uri, null);
			
			
		}
		
		Log.d(TAG, "Updated records: "+ret);
		return ret;
	}
	
	

}
