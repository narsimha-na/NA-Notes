package com.maverick.nanotes.persistence;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.maverick.nanotes.model.Notes;

import java.util.List;

@Dao
public interface NoteDao {

	@Query("SELECT * FROM notes")
	List<Notes> loadNotes();

	@Insert
	void insertNotes(Notes ...notes);

	@Delete
	void deleteNotes(Notes notes);


	@Query("SELECT * FROM notes WHERE id = :id")
	Notes loadPersonById(int id);

}
