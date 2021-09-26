package com.example.gurugedara;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

class SupportDatabaseHelper extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME = "GuruGedaraSupportService.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "my_support_service";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_ISSUE = "service_issue";
    private static final String COLUMN_PRIORITY = "service_priority";
    private static final String COLUMN_COMMENT = "service_comment";

    SupportDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_ISSUE + " TEXT, " +
                COLUMN_PRIORITY + " TEXT, " +
                COLUMN_COMMENT + " TEXT);";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    void addSupport(String issue, String priority, String comment){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_ISSUE, issue);
        cv.put(COLUMN_PRIORITY, priority);
        cv.put(COLUMN_COMMENT, comment);
        long result = db.insert(TABLE_NAME, null, cv);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Saved Successfully!", Toast.LENGTH_SHORT).show();
        }
    }
}
