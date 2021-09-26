package com.example.gurugedara;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class Announcement extends AppCompatActivity {

    RecyclerView announcement_recyclerView ;
    Button btn_add_announcement ;

    AnnouncementDatabaseHelper myDB ;
    ArrayList<String> announcement_id , announcement_topic , announcement_content ;
    AnnouncementCustomAdapter announcementCustomAdapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement);

        announcement_recyclerView = findViewById(R.id.announcement_recyclerView);
        btn_add_announcement = findViewById(R.id.btn_add_announcement);
        btn_add_announcement.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Announcement.this , Add_Announcement.class);
                startActivity(intent);
            }
        });

        myDB = new AnnouncementDatabaseHelper(Announcement.this);
        announcement_id = new ArrayList<>();
        announcement_topic = new ArrayList<>();
        announcement_content= new ArrayList<>();

        storeDataInArrays();

        announcementCustomAdapter = new AnnouncementCustomAdapter(Announcement.this , this , announcement_id , announcement_topic , announcement_content);
        announcement_recyclerView.setAdapter(announcementCustomAdapter);
        announcement_recyclerView.setLayoutManager(new LinearLayoutManager(Announcement.this));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();

        }    }

    void storeDataInArrays(){
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0 ){
            Toast.makeText(this, "No Announcements" , Toast.LENGTH_SHORT).show();
        }else{
            while(cursor.moveToNext()){
                announcement_id.add(cursor.getString(0));
                announcement_topic.add(cursor.getString(1));
                announcement_content.add(cursor.getString(2));

            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.announcement_menu , menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.delete_all_announcements){
            confirmDialog();


        }        return super.onOptionsItemSelected(item);
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete All?");
        builder.setMessage("Are you sure you want to delete all data?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                AnnouncementDatabaseHelper myDB = new AnnouncementDatabaseHelper(Announcement.this);
                myDB .deleteAllData();

                // Refresh Activity
                Intent intent = new Intent(Announcement.this , Announcement.class);
                startActivity(intent);
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