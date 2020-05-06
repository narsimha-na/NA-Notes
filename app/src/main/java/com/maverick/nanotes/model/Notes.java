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

	@ColumnInfo(name = "title")
	private String title;

	@ColumnInfo(name = "desc")
	private String desc;

	@ColumnInfo(name = "imp")
	private boolean imp;

	@ColumnInfo(name = "date")
	private String date;

	@Ignore
	public Notes(String title, String desc, boolean imp, String date) {
		this.title = title;
		this.desc = desc;
		this.imp = imp;
		this.date = date;
	}

	public Notes(int id, String title, String desc, boolean imp, String date) {
		this.id = id;
		this.title = title;
		this.desc = desc;
		this.imp = imp;
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public boolean isImp() {
		return imp;
	}


	public void setImp(boolean imp) {
		this.imp = imp;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}
