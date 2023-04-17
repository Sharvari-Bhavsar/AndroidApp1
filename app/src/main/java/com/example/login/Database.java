package com.example.login;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {
    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String qry1 = "CREATE TABLE Parents(Name text,Phone_no text,email text,username text,Password text )";
        sqLiteDatabase.execSQL(qry1);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void register(String Name , String Phone_no ,String email, String username,String Password){
        ContentValues cv = new ContentValues();
        cv.put("Name",Name);
        cv.put("Phone_no",Phone_no);
        cv.put("Email",email);
        cv.put("username",username);
        cv.put("Password",Password);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("Parents",null,cv);
        db.close();

    }
    public int login(String username,String Password){
        int result =0;
        String str[] = new String[2];
        str[0]= username;
        str[1]= Password;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("select* from Parents where username=? and password =?",str);
        if(c.moveToFirst()){
            result=1;

        }
        return result;


    }
}
