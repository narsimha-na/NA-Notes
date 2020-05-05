package com.maverick.nanotes.persistence;

import android.app.Application;
import android.content.ContentValues;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.maverick.nanotes.model.Notes;
import com.maverick.nanotes.utils.Constants;

@Database(entities = {Notes.class},version = 1,exportSchema = true)
public abstract  class NoteDatabase  extends RoomDatabase {
	private static NoteDatabase sInstance;

	public static NoteDatabase getInstance(Context context) {
		if (sInstance == null) {
				sInstance = Room.databaseBuilder(context.getApplicationContext(),
						NoteDatabase.class,Constants.DATABASE_NAME)
						.fallbackToDestructiveMigration()
						.build();
		}
		return sInstance;
	}

	public abstract NoteDao noteDao();

	private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback(){
		@Override
		public void onCreate(@NonNull SupportSQLiteDatabase db) {
			super.onCreate(db);
			new PopulateDb(sInstance).execute();
		}
	};

	private static class PopulateDb extends AsyncTask<Void,Void,Void>{

		private NoteDao noteDao;

		public PopulateDb(NoteDatabase noteDb) {
			noteDao = noteDb.noteDao();
		}

		@Override
		protected Void doInBackground(Void... voids) {
			noteDao.insertNotes(new Notes("hi","12-20-9"));
			noteDao.insertNotes(new Notes("addddi","12-202-19"));
			return null;
		}
	}
}
