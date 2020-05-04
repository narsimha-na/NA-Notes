package com.maverick.nanotes.persistence;

import android.app.Application;
import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.maverick.nanotes.model.Notes;
@Database(entities = {Notes.class},version = 1,exportSchema = true)
public abstract  class NoteDatabase  extends RoomDatabase {

	private static final String LOG_TAG = Application.class.getSimpleName();
	private static final Object LOCK = new Object();
	private static final String DB_NAME = "notes";
	private static NoteDatabase noteDatabase;

	public static NoteDatabase getInstance(Context context){
		if(noteDatabase != null){
			synchronized (LOCK){
				Log.d(LOG_TAG, "Creating a new Database Instance");
				noteDatabase = Room.databaseBuilder(context.getApplicationContext(),
						NoteDatabase.class,
						NoteDatabase.DB_NAME)
						.build();
			}
		}
		return noteDatabase;
	}


	public  abstract NoteDao noteDao();

}
