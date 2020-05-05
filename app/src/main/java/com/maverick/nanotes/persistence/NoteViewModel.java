package com.maverick.nanotes.persistence;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.maverick.nanotes.model.Notes;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {
	private NoteRepository respository;
	private LiveData<List<Notes>> allNotes;

	public NoteViewModel(@NonNull Application application) {
		super(application);
		respository = new NoteRepository(application);
		allNotes = respository.getAllNotes();
	}

	public void insert(Notes notes){respository.insert(notes);}

	public void update(Notes notes){respository.update(notes);}

	public void delete(Notes notes){ respository.delete(notes);}

	public void deleteAll(){respository.deleteAllNotes();}

	public LiveData<List<Notes>> getAllNotes(){
		return allNotes;
	}
}
