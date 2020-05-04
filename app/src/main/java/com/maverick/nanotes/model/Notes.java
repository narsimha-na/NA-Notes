package com.maverick.nanotes.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes")
public class Notes {

	@PrimaryKey(autoGenerate = true)
	private int id;

	@ColumnInfo(name = "note")
	private String note;

	@ColumnInfo(name = "date")
	private String date;

	@Ignore
	public Notes(String note, String date) {
		this.note = note;
		this.date = date;
	}

	public Notes(int id, String note, String date) {
		this.id = id;
		this.note = note;
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}
