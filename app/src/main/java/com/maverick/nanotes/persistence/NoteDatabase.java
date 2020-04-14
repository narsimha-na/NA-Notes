package com.maverick.nanotes.persistence;

import android.content.ContentValues;
import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.maverick.nanotes.model.Notes;

@Database(entities = {Notes.class}, version = 1)
public abstract  class NoteDatabase  extends RoomDatabase {

	public abstract NoteDao noteDao();
    public static final String DATABASE_NAME = "notes_db";

    private static volatile NoteDatabase noteDatabase;

    static NoteDatabase getDatabase(final Context context){
    	if(noteDatabase == null){
    		synchronized (NoteDatabase.class){
    			if(noteDatabase == null){
    				noteDatabase = Room.databaseBuilder(context.getApplicationContext(),
						    NoteDatabase.class,
						    "note_database")
						    .build();
			    }
		    }
	    }
    	return noteDatabase;
    }
}
