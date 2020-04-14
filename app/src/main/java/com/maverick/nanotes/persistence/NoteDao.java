package com.maverick.nanotes.persistence;


import androidx.room.Dao;
import androidx.room.Insert;

import com.maverick.nanotes.model.Notes;

@Dao
public interface NoteDao {

	@Insert
	void insert(Notes note);
}
