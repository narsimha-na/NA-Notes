package com.maverick.nanotes;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.maverick.nanotes.adapter.NoteAdapter;

public class MainActivity extends AppCompatActivity {

	private static final int NEW_NOTE_ACTIVITY_RESULT_CODE = 1;
	private String TAG = this.getClass().getSimpleName();
	private NoteViewModel noteViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.ma_add_note).setOnClickListener(v->{
        	startActivityForResult(new Intent(MainActivity.this,NoteEdit.class),NEW_NOTE_ACTIVITY_RESULT_CODE);
        });

    }

}
