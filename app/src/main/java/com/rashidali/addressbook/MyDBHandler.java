package com.rashidali.addressbook;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;


class MyDBHandler extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Addressbook.db";
    public static final String TABLE_NAME = "Person_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "Name";
    public static final String COL_3 = "Address";
    public static final String COL_4 = "Cell";

    public MyDBHandler(Context context) {

        super(context, DATABASE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Name TEXT, Address TEXT, Cell TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIXTS "+ TABLE_NAME);
        onCreate(db);
    }
    public boolean insertData(String name, String address, String cell){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_2,name);
        cv.put(COL_3,address);
        cv.put(COL_4, cell);
        long result = db.insert(TABLE_NAME, null, cv);
        if (result==-1)
            return false;
        else
            return true;
    }
    public Integer deleteContact(String name){
        SQLiteDatabase db = this.getWritableDatabase();
       int result;
        result = db.delete(TABLE_NAME, "Name = ?", new String[]{name});
        if (result==1)
            return 1;
        else
            return 0;
    }
    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor pointer = db.rawQuery("select * from "+ TABLE_NAME, null);
        return pointer;
    }





}
