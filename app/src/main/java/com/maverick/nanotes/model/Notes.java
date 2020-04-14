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

	@PrimaryKey
	@NonNull
	private String id;

	@NonNull
	@ColumnInfo(name = "note")
	private String notes;

	@NonNull
	public String getId() {
		return id;
	}

	public void setId(@NonNull String id) {
		this.id = id;
	}

	@NonNull
	public String getNotes() {
		return notes;
	}

	public void setNotes(@NonNull String notes) {
		this.notes = notes;
	}

	public Notes(@NonNull String id, @NonNull String notes) {
		this.id = id;
		this.notes = notes;
	}
}
