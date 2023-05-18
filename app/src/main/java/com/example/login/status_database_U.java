package com.example.login;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class status_database_U extends AppCompatActivity {
    EditText  EnteredID_U;
    Button insert, update, delete, view;
    Database DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_database_h);


        EnteredID_U= findViewById(R.id.userID_FK);
        insert = findViewById(R.id.btnInsert);
        update = findViewById(R.id.btnUpdate);
        delete = findViewById(R.id.btnDelete);
        view = findViewById(R.id.btnView);
        DB = new Database(this);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String EnteredID_UTXT = EnteredID_U.getText().toString();


                Boolean check_insert_data = DB.insertuserdataU(EnteredID_UTXT);
                if(check_insert_data==true){
                    Toast.makeText(status_database_U.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                    Log.d("OUTPUT", "New Entry Inserted");}
                else
                {Toast.makeText(status_database_U.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
                    Log.d("OUTPUT", "New Entry  Not Inserted");}
            }        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                String EnteredID_UTXT = EnteredID_U.getText().toString();



                    Boolean check_update_data = DB.updateuserdataU(EnteredID_UTXT );
                    if(check_update_data==true){
                        new  Database(status_database_U.this).User_EdumpCursorInLogCat();
                        Toast.makeText(status_database_U.this, "Entry Updated", Toast.LENGTH_SHORT).show();
                        Log.d("OUTPUT", " Entry Updated");}
                    else
                    { Toast.makeText(status_database_U.this, "New Entry Not Updated", Toast.LENGTH_SHORT).show();
                        Log.d("OUTPUT", " Entry not updated");}

                            }
                    });
//        delete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String VaccinenameTXT = Vaccinename.getText().toString();
//                Boolean check_delete_data = DB.deletedata(VaccinenameTXT);
//                if(check_delete_data){
//                    Toast.makeText(status_database_U.this, "Entry Deleted", Toast.LENGTH_SHORT).show();
//                    Log.d("OUTPUT", " Entry deleted");}
//                else{
//                    Toast.makeText(status_database_U.this, "Entry Not Deleted", Toast.LENGTH_SHORT).show();
//                    Log.d("OUTPUT", " Entry not deleted");}
//            }        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new  Database(status_database_U.this).Vaccine_SdumpCursorInLogCat();
                Cursor res = DB.getdata_U();
                if(res.getCount()==0){
                    Toast.makeText(status_database_U.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){

                    buffer.append("EnteredID : " + res.getString(0) + "\n");
                    buffer.append("Vaccine_name : " + res.getString(1) + "\n");
                    buffer.append("Status :" + res.getString(2) + "\n\n");

                }

                AlertDialog.Builder builder = new AlertDialog.Builder(status_database_U.this);
                builder.setCancelable(true);
                builder.setTitle("Vaccine Status");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
    }
}


