package com.example.gurugedara;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Add_Announcement extends AppCompatActivity {

    EditText et_topic , et_content;
    Button btn_save_announcement , btn_cancel_announcement ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_announcement);

        et_topic = findViewById(R.id.et_topic);
        et_content = findViewById(R.id.et_content);
        btn_save_announcement = findViewById(R.id.btn_save_announcement);
        btn_cancel_announcement = findViewById(R.id.btn_cancel_announcement);

        btn_save_announcement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AnnouncementDatabaseHelper myDB = new AnnouncementDatabaseHelper(Add_Announcement.this);
                myDB.addAnnouncement(et_topic.getText().toString().trim() ,
                        et_content.getText().toString().trim());
            }
        });
    }
}