package com.example.loginpageusingsqlitedb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME= "MyLogin";
    private static final int VERSION=1;
    public DbHelper(Context context) {
        super(context,DATABASE_NAME,null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "create table login (id integer primary key autoincrement , Username text , Email text , Phone text , Password text)";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists login");

    }

    public boolean insert_record(String username,String email, String phone, String password){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("Username",username);
        values.put("Email",email);
        values.put("Phone",phone);
        values.put("Password",password);

        long add=db.insert("login",null,values);
        if (add==-1){
            return false;
        }else {
            return true ;
        }

    }

    public boolean checkEmails(String email){
        SQLiteDatabase db=getWritableDatabase();
        Cursor cursor= db.rawQuery(" select *from login where Email=? ",new String[]{email});
        if (cursor.getCount()>0){
            return true;
        }else {
            return false;
        }
    }
    public boolean checkemailandpassword(String email,String password){
        SQLiteDatabase db=getWritableDatabase();
        Cursor cursor= db.rawQuery(" select *from login where Email=? and Password=? ",new String[]{email,password});
        if (cursor.getCount()>0){
            return true;
        }else {
            return false;
        }
    }
}
