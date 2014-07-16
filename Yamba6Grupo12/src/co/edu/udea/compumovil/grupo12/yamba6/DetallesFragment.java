package co.edu.udea.compumovil.grupo12.yamba6;

import android.app.Fragment;
import android.content.ContentUris;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DetallesFragment extends Fragment{

	
	private TextView textUser, textMessage, textCreatedAt;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.list_item, null, false); //
		textUser = (TextView) view.findViewById(R.id.list_item_text_user);
		textMessage = (TextView) view.findViewById(
		R.id.list_item_text_message);
		textCreatedAt = (TextView) view.findViewById(R.id.list_item_text_created_at);
		return view;
		}
	@Override
	public void onResume() {
		super.onResume();
		long id = getActivity().getIntent().getLongExtra(
		EstadoContract.Column.ID, -1); //
		updateView(id);
	
	}
	
	public void updateView(long id) { //
		if (id == -1) {
		textUser.setText("");
		textMessage.setText("");
		textCreatedAt.setText("");
		return;
		}
		Uri uri = ContentUris.withAppendedId(EstadoContract.CONTENT_URI, id);
		Cursor cursor = getActivity().getContentResolver().query(uri, null,
		null, null, null);
		if (!cursor.moveToFirst())
		return;
		String user = cursor.getString(cursor.getColumnIndex(EstadoContract.Column.USUARIO));
		String message = cursor.getString(cursor.getColumnIndex(EstadoContract.Column.MENSAJE));
		long createdAt = cursor.getLong(cursor.getColumnIndex(EstadoContract.Column.CREATED_AT));
		textUser.setText(user);
		textMessage.setText(message);
		textCreatedAt.setText(DateUtils.getRelativeTimeSpanString(createdAt));
		}
	
}
