package com.example.gurugedara;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateNote extends AppCompatActivity {

    EditText title_input, description_input;
    Button btn_update, btn_delete;

    String id, title, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_note);

        title_input = findViewById(R.id.title_update);
        description_input = findViewById(R.id.description_update);
        btn_update = findViewById(R.id.update_btn);
        btn_delete = findViewById(R.id.delete_btn);

        //First we call this
        getAndSetIntentData();

        //Set Action bar title after getAndSet Intent Data
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(title);
        }

        btn_update.setOnClickListener(view -> {

            //And only we can call this
            MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateNote.this);
            title = title_input.getText().toString().trim();
            description = description_input.getText().toString().trim();
            myDB.updateData(id, title, description);

        });

        btn_delete.setOnClickListener(view -> conformDialog());
    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("title") &&
                    getIntent().hasExtra("description")){
            //Getting data from Intent
            id = getIntent().getStringExtra("id");
            title = getIntent().getStringExtra("title");
            description = getIntent().getStringExtra("description");

            //Setting Intent data
            title_input.setText(title);
            description_input.setText(description);
            Log.d("stev", title+" "+description);
        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }


        }

    void conformDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + title + " ?");
        builder.setMessage("Are you sure you want to delete " + title + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateNote.this);
                myDB.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }
}