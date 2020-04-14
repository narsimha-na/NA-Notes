package com.maverick.nanotes;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

public class NoteViewModel extends AndroidViewModel {

	private String TAG = this.getClass().getSimpleName();

	public NoteViewModel(@NonNull Application application) {
		super(application);
	}

	@Override
	protected void onCleared() {
		super.onCleared();
		Log.i(TAG, "View Model Destroyed");
	}
}
