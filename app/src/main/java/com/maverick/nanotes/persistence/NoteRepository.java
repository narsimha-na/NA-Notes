package com.maverick.nanotes.persistence;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.maverick.nanotes.model.Notes;

import java.util.List;

public class NoteRepository {

	private NoteDao noteDao;
	private LiveData<List<Notes>> allNotes;

	public NoteRepository(Application application){
		NoteDatabase db = NoteDatabase.getInstance(application);
		noteDao = db.noteDao();
		allNotes = noteDao.loadNotes();
	}

	public void insert(Notes notes){
		new InsertNoteAsyncTask(noteDao).execute(notes);
	}

	public void update(Notes notes){
		new UpdateNoteAsyncTask(noteDao).execute(notes);
	}

	public void delete(Notes notes){
		new DeleteNoteAsyncTask(noteDao).execute(notes);
	}

	public void deleteAllNotes(){
		new DeleteAllNoteAsyncTask(noteDao).execute();
	}

	public LiveData<List<Notes>> getAllNotes(){
		return allNotes;
	}

	private static class InsertNoteAsyncTask extends AsyncTask<Notes,Void,Void>{
		private NoteDao noteDao;
		private  InsertNoteAsyncTask(NoteDao noteDao) {
			this.noteDao = noteDao;
		}
		@Override
		protected Void doInBackground(Notes... notes) {
			noteDao.insertNotes(notes[0]);
			return null;
		}
	}

	private static class UpdateNoteAsyncTask extends AsyncTask<Notes,Void,Void>{
		private NoteDao noteDao;

		public UpdateNoteAsyncTask(NoteDao noteDao) {
			this.noteDao = noteDao;
		}

		@Override
		protected Void doInBackground(Notes... notes) {
			noteDao.updateNote(notes[0]);
			return null;
		}
	}

	private static class DeleteNoteAsyncTask extends AsyncTask<Notes,Void,Void>{
		private NoteDao noteDao;

		public DeleteNoteAsyncTask(NoteDao noteDao) {
			this.noteDao = noteDao;
		}

		@Override
		protected Void doInBackground(Notes... notes) {
			return null;
		}
	}

	private static class DeleteAllNoteAsyncTask extends AsyncTask<Notes,Void,Void>{
		private NoteDao noteDao;

		public DeleteAllNoteAsyncTask(NoteDao noteDao) {
			this.noteDao = noteDao;
		}

		@Override
		protected Void doInBackground(Notes... notes) {
			noteDao.deleteAllNotes();
			return null;
		}
	}


}
