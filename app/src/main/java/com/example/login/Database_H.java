package com.example.login;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database_H extends SQLiteOpenHelper {
    public Database_H(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String qry1 = "CREATE TABLE Hospital2(HospitalName text,Phone_no text,username text,Password text,City text,State text)";
        sqLiteDatabase.execSQL(qry1);

    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void register_H(String HospitalName , String Phone_no, String username,String Password,String City ,String State){

        ContentValues cv = new ContentValues();
        cv.put("HospitalName",HospitalName);
        cv.put("Phone_no",Phone_no);
        cv.put("username",username);
        cv.put("Password",Password);
        cv.put("City",City);
        cv.put("State",State);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("Hospital2",null,cv);
        db.close();

    }

    public int login_H(String username,String Password){
        int result =0;
        String str1[] = new String[2];
        str1[0]= username;
        str1[1]= Password;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("select* from Hospital2 where username=? and Password =?",str1);
        if(c.moveToFirst()){
            result=1;

        }
        return result;


    }
}

