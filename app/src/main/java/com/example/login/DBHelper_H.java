//package com.example.login;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//
//public class DBHelper_H extends SQLiteOpenHelper {
//    public DBHelper_H(Context context) {
//        super(context, "Vaccinedata.db", null, 1);
//    }
//    @Override
//    public void onCreate(SQLiteDatabase DB) {
//        DB.execSQL("create Table Vaccinedetails(Vaccinename TEXT primary key, Status TEXT)");
//    }
//    @Override
//    public void onUpgrade(SQLiteDatabase DB, int i, int ii) {
//        DB.execSQL("drop Table if exists Vaccinedetails");
//    }
//    public Boolean insertuserdata(String Vaccinename, String Status)
//    {
//        SQLiteDatabase DB = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("Vaccinename", Vaccinename);
//        contentValues.put("Status", Status);
//
//        long result=DB.insert("Vaccinedetails", null, contentValues);
//        if(result==-1){
//            return false;
//        }else{
//            return true;
//        }
//    }
//    public Boolean updateuserdata(String Vaccinename, String Status)
//    {
//        SQLiteDatabase DB = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("Status", Status);
//
//        Cursor cursor = DB.rawQuery("Select * from Vaccinedetails where Vaccinename = ?", new String[]{Vaccinename});
//        if (cursor.getCount() > 0) {
//            long result = DB.update("Vaccinedetails", contentValues, "Vaccinename=?", new String[]{Vaccinename});
//            if (result == -1) {
//                return false;
//            } else {
//                return true;
//            }
//        } else {
//            return false;
//        }
//    }
//    public Boolean deletedata (String Vaccinename)
//    {
//        SQLiteDatabase DB = this.getWritableDatabase();
//        Cursor cursor = DB.rawQuery("Select * from Vaccinedetails where Vaccinename = ?", new String[]{Vaccinename});
//        if (cursor.getCount() > 0) {
//            long result = DB.delete("Vaccinedetails", "Vaccinename=?", new String[]{Vaccinename});
//            if (result == -1) {
//                return false;
//            } else {
//                return true;
//            }
//        } else {
//            return false;
//        }
//    }
//
//    public Cursor getdata ()
//    {
//        SQLiteDatabase DB = this.getWritableDatabase();
//        Cursor cursor = DB.rawQuery("Select * from Vaccinedetails ", null);
//        return cursor;
//    }
//}
