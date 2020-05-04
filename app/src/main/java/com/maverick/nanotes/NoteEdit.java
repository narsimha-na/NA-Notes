package com.maverick.nanotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;
import com.maverick.nanotes.model.Notes;
import com.maverick.nanotes.persistence.AppExecutors;
import com.maverick.nanotes.persistence.NoteDao;
import com.maverick.nanotes.persistence.NoteDatabase;

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
					saveInDB(editView.getText().toString());
				} catch (NullPointerException e) {
					Log.d(TAG, "onCreate: " + e.getMessage());
					saveInDB(editView.getText().toString());
				}
			}
		});


	}

	private void saveInDB(String note) {

		AppExecutors.getInstance().diskIO().execute(() -> {
            NoteDatabase db;
            db = NoteDatabase.getInstance(NoteEdit.this);
            db.noteDao().insertNotes(new Notes(note, "15/02/2020"));

            finish();
        });

	}
}
