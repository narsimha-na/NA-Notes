package com.maverick.nanotes.persistence;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.maverick.nanotes.model.Notes;

import java.util.List;

@Dao
public interface NoteDao {

	@Query("SELECT * FROM notes")
	LiveData<List<Notes>> loadNotes();

	@Insert
	void insertNotes(Notes notes);

	@Delete
	void deleteNotes(Notes notes);

	@Query("DELETE FROM notes")
	void deleteAllNotes();

	@Update
	void updateNote(Notes notes);



}
