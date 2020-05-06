package com.maverick.nanotes;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.maverick.nanotes.adapter.NoteAdapter;
import com.maverick.nanotes.model.Notes;
import com.maverick.nanotes.persistence.NoteViewModel;
import com.maverick.nanotes.utils.Constants;

import java.util.List;

import static com.maverick.nanotes.utils.Constants.NEW_NOTE_ACTIVITY_RESULT_CODE;

public class MainActivity extends AppCompatActivity {

	private String TAG = this.getClass().getSimpleName();
	private NoteViewModel mNoteViewModel;
	private RecyclerView mRecyclerView;
	private RelativeLayout view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        view = findViewById(R.id.main_parent_layout);
        mRecyclerView = findViewById(R.id.ma_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));
        mRecyclerView.setHasFixedSize(true);
        NoteAdapter adapter = new NoteAdapter();
        mRecyclerView.setAdapter(adapter);

        //ViewModel and LiveData
        mNoteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
        mNoteViewModel.getAllNotes().observe(this, new Observer<List<Notes>>() {
	        @Override
	        public void onChanged(List<Notes> notes) {
		        adapter.submitList(notes);
	        }
        });

        adapter.setOnItemClickListener(note -> {
        	Intent intent = new Intent(MainActivity.this,NoteEdit.class);
        	intent.putExtra(Constants.ID,note.getId());
        	intent.putExtra(Constants.TITLE,note.getTitle());
        	intent.putExtra(Constants.DESC,note.getDesc());
        	intent.putExtra(Constants.IMP,note.isImp());
        	intent.putExtra(Constants.DATE,note.getDate());
        	startActivityForResult(intent,Constants.EDIT_NOTE_ACTIVITY_RESULT_CODE);
        });

        // Swipe Item Delete
	    new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
			    ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
		    @Override
		    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
			    return 0;
		    }

		    @Override
		    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
			    return false;
		    }

		    @Override
		    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
		    	mNoteViewModel.delete(adapter.getNoteAt(viewHolder.getAdapterPosition()));
		    	Snackbar.make(view,"Note Deleted ",Snackbar.LENGTH_SHORT).show();
		    }
	    }).attachToRecyclerView(mRecyclerView);

        // This is used to open the Note Edit Activity
        findViewById(R.id.ma_add_note).setOnClickListener(v->{
        	startActivityForResult(new Intent(MainActivity.this,NoteEdit.class),NEW_NOTE_ACTIVITY_RESULT_CODE);
        });

    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.menu.menu_main,menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    	switch (item.getItemId()){
		    case R.id.main_menu_delete_all:
		    	mNoteViewModel.deleteAll();
		    	Snackbar.make(view,"All notes Deleted",Snackbar.LENGTH_SHORT).show();
		    	return true;
		    default:
		    	return super.onOptionsItemSelected(item);
	    }
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		long millis=System.currentTimeMillis();
		java.sql.Date date=new java.sql.Date(millis);

		if(requestCode == NEW_NOTE_ACTIVITY_RESULT_CODE && resultCode == RESULT_OK){
			mNoteViewModel.insert(new Notes(
					data.getStringExtra(Constants.TITLE),
					data.getStringExtra(Constants.DESC),
					data.getBooleanExtra(Constants.IMP,false),
					date.toString()
			));
			Snackbar.make(view,"Note Saved Successful",Snackbar.LENGTH_SHORT).show();
		}else{
			Snackbar.make(view,"Note not Saved ",Snackbar.LENGTH_SHORT).show();
		}
	}
}
