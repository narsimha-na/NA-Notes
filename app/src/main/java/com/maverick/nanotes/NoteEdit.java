package com.maverick.nanotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.maverick.nanotes.model.Notes;
import com.maverick.nanotes.persistence.AppExecutors;
import com.maverick.nanotes.persistence.NoteDao;
import com.maverick.nanotes.persistence.NoteDatabase;

import java.lang.ref.WeakReference;
import java.util.List;

public class NoteEdit extends AppCompatActivity {

	public static String NOTE_ADDED = "new_note";
	private EditText editView;
	private static String TAG = "NoteEdit";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_note_edit);
		editView = (EditText) findViewById(R.id.ne_edit);
		String new_note = editView.getText().toString();

		findViewById(R.id.ne_button).setOnClickListener(v -> {
			Intent resultIntent = new Intent();
			if (TextUtils.isEmpty(editView.getText())) {
				Snackbar.make(v, "Text inside the note is Empty", Snackbar.LENGTH_SHORT).show();
			} else {
				try {
					DoInBackground back = new DoInBackground(NoteEdit.this);
					back.execute(editView.getText().toString());
				} catch (NullPointerException e) {
					Log.d(TAG, "onCreate: " + e.getMessage()+editView.getText().toString());
				}
			}
		});


	}

	private static class DoInBackground extends AsyncTask<String,String, Boolean>{


		private WeakReference<NoteEdit> activityReference;

		// only retain a weak reference to the activity
		DoInBackground(NoteEdit context) {
			activityReference = new WeakReference<>(context);
		}

		@Override
		protected Boolean doInBackground(String... note) {
			try{
				NoteDatabase db;
				db = NoteDatabase.getInstance(activityReference.get());
				Notes na = new Notes(note[0], "15/02/2020");
				db.noteDao().insertNotes(na);
				db.close();

				return true;
			}catch (Exception e){
				Log.d(TAG, "doInBackground: "+e.getMessage());
				return false;
			}
		}


		@Override
		protected void onPostExecute(Boolean aBoolean) {
			super.onPostExecute(aBoolean);
			if(aBoolean){
				Toast.makeText(activityReference.get(), "Done", Toast.LENGTH_SHORT).show();
			}else{
				Toast.makeText(activityReference.get(), "Failure", Toast.LENGTH_SHORT).show();
			}
		}
	}
}
