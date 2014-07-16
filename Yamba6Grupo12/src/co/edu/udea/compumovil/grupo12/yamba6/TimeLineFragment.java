package co.edu.udea.compumovil.grupo12.yamba6;

import android.app.ListFragment;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.SimpleCursorAdapter.ViewBinder;
import android.widget.TextView;
import android.widget.Toast;

public class TimeLineFragment  extends ListFragment implements LoaderCallbacks<Cursor>{
	
	private static final String TAG = TimeLineFragment.class.getSimpleName();
	private static final String[] FROM = {EstadoContract.Column.USUARIO, EstadoContract.Column.MENSAJE,
		EstadoContract.Column.CREATED_AT};
	private static final int[] TO = {R.id.list_item_text_user, R.id.list_item_text_message, 
		R.id.list_item_text_created_at};
	private static final int LOADER_ID=42;
	private SimpleCursorAdapter mAdapter;
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState){
		
		super.onActivityCreated(savedInstanceState);
		setEmptyText("Loading data...");
		mAdapter = new SimpleCursorAdapter(getActivity(), R.layout.list_item, null,
		FROM, TO, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
		mAdapter.setViewBinder(new TimelineViewBinder()); //
		setListAdapter(mAdapter);
		getLoaderManager().initLoader(LOADER_ID, null, this);
				
	}
	
	///clase interna TimeLineViewBinder
	
	class TimelineViewBinder implements ViewBinder { //
			@Override
			public boolean setViewValue(View view, Cursor cursor,int columnIndex) { //
			
				if (view.getId() != R.id.list_item_text_created_at)
			
					return false;
			// Convert timestamp to relative time
			long timestamp = cursor.getLong(columnIndex); //
			CharSequence relativeTime = DateUtils.getRelativeTimeSpanString(timestamp); //
			((TextView) view).setText(relativeTime); //
			return true; //
			}
			}
	
	//////////// este metodo se puso
	
	public void onListItemClick(ListView l, View v, int position, long id) {
		//
		// Get the details fragment
		DetallesFragment fragment = (DetallesFragment) getFragmentManager()
		.findFragmentById(R.id.fragment_details); //
		// Is details fragment visible?
		if (fragment != null && fragment.isVisible()) { //
		fragment.updateView(id); //
		} else {
		startActivity(new Intent(getActivity(), DetallesActivity.class)
		.putExtra(EstadoContract.Column.ID, id)); //
		}
		}
	
		
	////////////	
	
	
	//cargando los callbacks
	//ejecutando hilos sobre una base que no es la UI
	
	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle args)
	{
		if (id != LOADER_ID)
			return null;
			Log.d(TAG, "onCreateLoader");
			return new CursorLoader(getActivity(), EstadoContract.CONTENT_URI,
			null, null, null, EstadoContract.DEFAULT_SORT);
	}
	
	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
		//lineas de codigo viejas		
		//Log.d(TAG, "onLoadFinished with cursor: " + cursor.getCount());
		//mAdapter.swapCursor(cursor);
		
		//lineas nuevas
		// Get the details fragment
		DetallesFragment fragment = (DetallesFragment) getFragmentManager()
		.findFragmentById(R.id.fragment_details);
		// Is details fragment visible?
		if (fragment != null && fragment.isVisible() && cursor.getCount()
		== 0) {
		fragment.updateView(-1);
		Toast.makeText(getActivity(), "No data",
		Toast.LENGTH_LONG).show();
		}
		Log.d(TAG, "onLoadFinished with cursor: " + cursor.getCount());
		mAdapter.swapCursor(cursor);
		
		
	}
	@Override
	public void onLoaderReset(Loader<Cursor> loader) {
		mAdapter.swapCursor(null);
	}
	

}
