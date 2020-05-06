package com.maverick.nanotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.maverick.nanotes.model.Notes;
import com.maverick.nanotes.persistence.AppExecutors;
import com.maverick.nanotes.persistence.NoteDao;
import com.maverick.nanotes.persistence.NoteDatabase;
import com.maverick.nanotes.utils.Constants;

import java.lang.ref.WeakReference;
import java.util.List;

public class NoteEdit extends AppCompatActivity {

	public static String NOTE_ADDED = "new_note";
	private EditText editTitle,editDesc;
	private CheckBox checkBox;
	private LinearLayout view;
	private static String TAG = "NoteEdit";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_note_edit);

		view = findViewById(R.id.parent_view);
		editTitle = findViewById(R.id.ne_edit_title);
		editDesc = findViewById(R.id.ne_edit_desc);
		checkBox = findViewById(R.id.ne_check_box);

		getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back_white);

		Intent intent = getIntent();
		if(intent != null){
			if(intent.hasExtra(Constants.ID)){
				setTitle("Edit Note");
				editTitle.setText(intent.getStringExtra(Constants.TITLE));
				editDesc.setText(intent.getStringExtra(Constants.DESC));
				checkBox.setChecked(intent.getBooleanExtra(Constants.IMP,false));
			}else{
				setTitle("Add Note");
			}
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.menu.menu_edit_notes,menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(@NonNull MenuItem item) {
		if (item.getItemId() == R.id.menu_edit_notes_save) {
			saveNotes();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private void saveNotes() {
		String title = editTitle.getText().toString();
		String desc = editDesc.getText().toString();
		boolean imp = false;
		imp = checkBox.isChecked();

		if(title.trim().isEmpty() || desc.trim().isEmpty()){
			Snackbar.make(view,"Please enter the title and description",Snackbar.LENGTH_SHORT).show();
			return;
		}

		Intent data = new Intent();
		data.putExtra(Constants.TITLE,title);
		data.putExtra(Constants.DESC,desc);
		data.putExtra(Constants.IMP,imp);

		int id = getIntent().getIntExtra(Constants.ID,-1);
		if(id != -1){
			data.putExtra(Constants.ID,id);
		}
		setResult(RESULT_OK,data);
		finish();

	}
}
