package com.example.shlokassignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Sqlhelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Database";
    private static final String TABLE_CONTACTS = "datas";
    private static final String KEY_ID = "id";
    private static final String lable1 = "label1";
    private static final String lable2 = "label2";
    private static final String lable3 = "labe3";
    private static final String lable4 = "label3";


    public Sqlhelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);


    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + lable1 + " TEXT,"
                + lable2 + " TEXT," + lable3 + " TEXT,"
                + lable4 + " TEXT" + ")";
        sqLiteDatabase.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_CONTACTS);
         onCreate(sqLiteDatabase);
    }


    boolean add(String label, String labe2, String labe3, String labe4)
    {
        boolean createSuccessful = false;


        ContentValues values = new ContentValues();
        values.put(lable1,label);
        values.put(lable2, labe2);
        values.put(lable3, labe3);
        values.put(lable4,labe4);
        SQLiteDatabase db = this.getWritableDatabase();
        createSuccessful=db.insert(TABLE_CONTACTS, null, values)>0;
        db.close();
        System.out.println(createSuccessful+"dvvdfvdv");
        return createSuccessful;


    }
    public Cursor fetch() {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = new String[] { lable1, lable2, lable3,lable4 };
        Cursor cursor = db.query(TABLE_CONTACTS, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }



}
