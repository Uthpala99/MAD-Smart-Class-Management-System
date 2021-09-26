package com.example.gurugedara;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddNote extends AppCompatActivity {

    EditText title_add, description_add;
    Button add_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        title_add = findViewById(R.id.title_add);
        description_add = findViewById(R.id.description_add);
        add_btn = findViewById(R.id.add_btn);
        add_btn.setOnClickListener(view -> {
            MyDatabaseHelper myDB = new MyDatabaseHelper(AddNote.this);
            myDB.addNote(title_add.getText().toString().trim(),
                    description_add.getText().toString().trim());

        });

    }
}