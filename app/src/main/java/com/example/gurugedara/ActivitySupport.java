package com.example.gurugedara;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.util.Objects;

import static com.example.gurugedara.R.color.teal_700;

public class ActivitySupport extends AppCompatActivity {

    EditText issue_input, priority_input, comment_input;
    Button save_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);

        issue_input = findViewById(R.id.issue_input);
        priority_input = findViewById(R.id.priority_input);
        comment_input = findViewById(R.id.comment_input);
        save_button = findViewById(R.id.save_button);
        save_button.setOnClickListener(view -> {
            SupportDatabaseHelper myDB = new SupportDatabaseHelper(ActivitySupport.this);
            myDB.addSupport(issue_input.getText().toString().trim(),
                    priority_input.getText().toString().trim(),
                    comment_input.getText().toString().trim());

        });
    }
}