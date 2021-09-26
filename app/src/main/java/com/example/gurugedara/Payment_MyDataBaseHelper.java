package com.example.gurugedara;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;


public class Payment_MyDataBaseHelper extends SQLiteOpenHelper {


    private Context context;
    public static final String DATABASE_NAME = "Payment_db";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "my_payment";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_REGISTER = "st_registernum";
    public static final String COLUMN_NAME = "st_name";
    public static final String COLUMN_SLIP = "st_slip";
    public static final String COLUMN_BANK = "st_Bank";
    public static final String COLUMN_MONTH = "st_month";


    public Payment_MyDataBaseHelper(@Nullable Context context) {
        super(context,DATABASE_NAME, null , DATABASE_VERSION);
        this.context= context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        String query =
                "CREATE TABLE " + TABLE_NAME +
                        " (" + COLUMN_ID +  " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_REGISTER + " TEXT, " +
                        COLUMN_NAME + " TEXT, " +
                        COLUMN_SLIP + " TEXT, " +
                        COLUMN_BANK + " TEXT, " +
                        COLUMN_MONTH + " TEXT); ";



        db.execSQL(query);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int ii, int i) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);


    }
    public void addPayment(String registernum,String name,String slip,String bank,String month){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_REGISTER,registernum);
        cv.put(COLUMN_NAME,name);
        cv.put(COLUMN_SLIP,slip);
        cv.put(COLUMN_BANK,bank);
        cv.put(COLUMN_MONTH,month);
        long result = db.insert(TABLE_NAME,null,cv);
        if(result == -1){

            Toast.makeText(context,"UNSUCCESS",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context,"ADD SUCCESSFULLY",Toast.LENGTH_SHORT).show();
        }

    }

    Cursor readAllData() {

        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null){

            cursor = db.rawQuery(query,null);


        }
        return cursor;
    }

    void updateData(String row_id,String registernum,String name,String slip,String bank,String month){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_REGISTER,registernum);
        cv.put(COLUMN_NAME,name);
        cv.put(COLUMN_SLIP,slip);
        cv.put(COLUMN_BANK,bank);
        cv.put(COLUMN_MONTH,month);

        long result = db.update(TABLE_NAME, cv , "_id=?",new String[]{row_id});
        if(result == -1){

            Toast.makeText(context,"UPDATE UNSUCCESS",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context,"UPDATE SUCCESSFULLY",Toast.LENGTH_SHORT).show();
        }

    }

}
