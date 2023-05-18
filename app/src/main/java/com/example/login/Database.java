package com.example.login;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class Database extends SQLiteOpenHelper {

    public Database(Context context) {


        super(context, "Vccinedata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String qry1 = "CREATE TABLE Parents(User_id INTEGER PRIMARY KEY  ,Name text,Phone_no text,email text,username text,Password text,DOB INTEGER )";
        sqLiteDatabase.execSQL(qry1);
        String qry2 = "CREATE TABLE Vaccinedetails2 (Vaccine_ID INTEGER PRIMARY KEY  ,EnteredID_H int,Vaccinename text,Status text /*,User_id INTEGER, FOREIGN KEY (User_id) REFERENCES Parents (User_id)*/ )";
        sqLiteDatabase.execSQL(qry2);
        String qry3 = "CREATE TABLE USER_ENTER (Entered_id_user INTEGER)";
        sqLiteDatabase.execSQL(qry3);




    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop Table if exists Parents");
        sqLiteDatabase.execSQL("drop Table if exists Vaccinedetails2");
        sqLiteDatabase.execSQL("drop Table if exists USER_ENTER");

    }

    public void register(String Name , String Phone_no ,String email, String username,String Password,String DOB ){
        ContentValues cv = new ContentValues();
        cv.put("Name",Name);
        cv.put("Phone_no",Phone_no);
        cv.put("Email",email);
        cv.put("username",username);
        cv.put("Password",Password);
        cv.put("DOB",DOB);

        SQLiteDatabase db = getWritableDatabase();
        db.insert("Parents",null,cv);
        db.close();

    }
    public int login(String username,String Password){
        int result =0;
        String[] str = new String[2];
        str[0]= username;
        str[1]= Password;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("select* from Parents where username=? and password =?",str);
        if(c.moveToFirst()){
            result=1;


        }

        return result;


    }

    public Boolean insertuserdata(String EnteredID_H,String Vaccinename, String Status)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("Vaccinename", Vaccinename);
        contentValues.put("Status", Status);
        contentValues.put("EnteredID_H", EnteredID_H);
        long result=DB.insert("Vaccinedetails2", null, contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }

    public Boolean updateuserdata(String  Vaccine_ID,String EnteredID_H, String Vaccinename, String Status)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Vaccinename", Vaccinename);
        contentValues.put("Status", Status);
        contentValues.put("EnteredID_H", EnteredID_H);
        Cursor cursor = DB.rawQuery("Select * from Vaccinedetails2 where Vaccine_ID = ?", new String[]{Vaccine_ID});
        if (cursor.getCount() > 0) {
            long result = DB.update("Vaccinedetails2", contentValues, "Vaccine_ID=?", new String[]{Vaccine_ID});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }


    public Boolean deletedata (String Vaccine_ID)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Vaccinedetails2 where Vaccine_ID = ?", new String[]{Vaccine_ID});
        if (cursor.getCount() > 0) {
            long result = DB.delete("Vaccinedetails2", "Vaccine_ID=?", new String[]{Vaccine_ID});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }


    public Cursor getdata_H ()
    {
        SQLiteDatabase DB = this.getWritableDatabase();

        Cursor cursor = DB.rawQuery("Select * from Vaccinedetails2", null);
        return cursor;
    }
    public Cursor getdata_U ()
    {
        SQLiteDatabase DB = this.getWritableDatabase();

        Cursor cursor = DB.rawQuery("Select  EnteredID_H,Vaccinename ,Status  from Vaccinedetails2, USER_ENTER WHERE Vaccinedetails2.EnteredID_H == USER_ENTER.Entered_id_user ", null);
        return cursor;
    }
    // very imp method to show table in logcat
    public  void USERdumpCursorInLogCat() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from Parents", null);
        DatabaseUtils.dumpCursor(cursor);
        cursor.close();}
    public  void Vaccine_UdumpCursorInLogCat() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from Vaccinedetails2", null);
        DatabaseUtils.dumpCursor(cursor);
        cursor.close();
    }
    public  void Vaccine_SdumpCursorInLogCat() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from Vaccinedetails2,USER_ENTER  WHERE Vaccinedetails2.EnteredID_H = USER_ENTER.Entered_id_user", null);
        DatabaseUtils.dumpCursor(cursor);
        cursor.close();
    }
    public  void User_EdumpCursorInLogCat() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from USER_ENTER ", null);
        DatabaseUtils.dumpCursor(cursor);
        cursor.close();
    }
    public Boolean insertuserdataU(String Entered_id_user)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Entered_id_user", Entered_id_user);
        long result=DB.insert("USER_ENTER", null, contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }
    public Boolean updateuserdataU(String Entered_id_user)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("Entered_id_user", Entered_id_user);
        Cursor cursor = DB.rawQuery("Select * from USER_ENTER ",null);
        if (cursor.getCount() > 0) {
            long result = DB.update("USER_ENTER", contentValues, null,null);
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }


}

