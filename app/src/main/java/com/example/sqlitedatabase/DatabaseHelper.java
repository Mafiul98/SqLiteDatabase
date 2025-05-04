package com.example.sqlitedatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper {

    public static String DB_NAME = "my_database";
    public static int DB_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table my_table(id INTEGER primary key autoincrement,name TEXT,mobile TEXT) ");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exists my_table");

    }

    public void insertdata (String name,String mobile){
        SQLiteDatabase database  = this.getWritableDatabase();

        ContentValues conval = new ContentValues();
        conval.put("name",name);
        conval.put("mobile",mobile);
        database.insert("my_table",null,conval);
    }


    public Cursor getAlldata(){

        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("select * from my_table",null);
        return cursor;


    }

    public void deleteone(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("my_table", "id=?",new String[]{String.valueOf(id)});
        db.close();

    }


}
