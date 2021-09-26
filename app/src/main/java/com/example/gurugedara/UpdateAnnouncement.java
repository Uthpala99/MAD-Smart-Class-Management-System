package com.example.gurugedara;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateAnnouncement extends AppCompatActivity {

    EditText et_topic , et_content ;
    Button btn_update_announcement , btn_delete_announcement;
    String id , topic , content ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_announcement);

        et_topic = findViewById(R.id.update_et_topic);
        et_content = findViewById(R.id.update_et_content);
        btn_update_announcement = findViewById(R.id.btn_update_announcement);
        btn_delete_announcement = findViewById(R.id.btn_delete_announcement);

        //First we call this
        getAndSetIntentData();

        //set actionbar title after getAndSetIntentData method
        ActionBar ab = getSupportActionBar();
        if(ab != null ) {
            ab.setTitle(topic);
        }

        btn_update_announcement.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                //And only then we call this
                AnnouncementDatabaseHelper myDB = new AnnouncementDatabaseHelper(UpdateAnnouncement.this);
                topic = et_topic.getText().toString().trim() ;
                content = et_content.getText().toString().trim();

                myDB.updateData(id , topic , content);
            }
        });

        btn_delete_announcement.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });

    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("topic") && getIntent().hasExtra("content")){

            //getting data from intent
            id = getIntent().getStringExtra("id");
            topic = getIntent().getStringExtra("topic");
            content = getIntent().getStringExtra("content");

            //setting intent data
            et_topic.setText(topic);
            et_content.setText(content);

        }else{
            Toast.makeText(this , "No Announcements" , Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + topic + " ?");
        builder.setMessage("Are you sure you want to delete " + topic + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                AnnouncementDatabaseHelper myDB = new AnnouncementDatabaseHelper(UpdateAnnouncement.this);
                myDB.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}