package com.maverick.nanotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;

public class NoteEdit extends AppCompatActivity {

    public static String NOTE_ADDED = "new_note";
    private EditText editView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_edit);
        editView = (EditText)findViewById(R.id.ne_edit);
        String new_note = editView.getText().toString();

        findViewById(R.id.ne_button).setOnClickListener(v->{
            Intent resultIntent = new Intent();
            if(TextUtils.isEmpty(editView.getText())){
                setResult(RESULT_CANCELED);
            }else{
                String note = editView.getText().toString();
                resultIntent.putExtra(NOTE_ADDED,note);
                setResult(RESULT_OK,resultIntent);
            }
        });


    }
}
