package co.edu.udea.compumovil.grupo12.yamba6;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * clase donde se tienen los nombres de las columnas, la tabla de la DB y el nombre
 * de la BD
 * @author telematica
 *
 */

public class EstadoContract {
	
	public static final String dbNombre = "timeline.db";
	public static final int dbVersion = 1;
	public static final String tabla = "estado";
	
	////content provider /////
	
	public static final String AUTHORITY = "co.edu.udea.compumovil.grupo12.yamba6.EstadoProvider";
	public static final Uri CONTENT_URI = Uri.parse("content://"+AUTHORITY+"/"+ tabla);
	public static final int STATUS_ITEM = 1;
	public static final int STATUS_DIR = 2;
	public static final String STATUS_TYPE_ITEM = 
			"vnd.android.cursor.item/vnd.co.edu.udea.compumovil.grupo12.yamba6.provider.status";
	
	public static final String STATUS_TYPE_DIR = 
			"vnd.android.cursor.dir/vnd.co.edu.udea.compumovil.grupo12.yamba6.provider.status";
	
	public static final String DEFAULT_SORT = Column.CREATED_AT + " DESC ";
	
	public class Column {
		
		public static final String ID = BaseColumns._ID;
		public static final String USUARIO = "usuario";
		public static final String MENSAJE = "mensaje";
		public static final String CREATED_AT = "created_at";
		
		
		
		
		
	}
	
	
}
