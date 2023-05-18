package com.example.login;



import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class status_database_H extends AppCompatActivity {
    EditText Vaccinename, Status,EnteredID_H,Vaccine_ID;
    Button insert, update, delete, view;
    Database  DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_database_h);


        Vaccinename = findViewById(R.id.Vaccinename);
        Status = findViewById(R.id.Status);
        EnteredID_H= findViewById(R.id.userID_FK);
        insert = findViewById(R.id.btnInsert);
        update = findViewById(R.id.btnUpdate);
        delete = findViewById(R.id.btnDelete);
        view = findViewById(R.id.btnView);
        DB = new Database(this);
        insert.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String  VaccinenameTXT =  Vaccinename.getText().toString();
                String StatusTXT = Status.getText().toString();
                String EnteredID_HTXT = EnteredID_H.getText().toString();


                Boolean check_insert_data = DB.insertuserdata(EnteredID_HTXT,VaccinenameTXT, StatusTXT);
                if(check_insert_data==true) {
                    new Database(status_database_H.this).Vaccine_UdumpCursorInLogCat();
                    Toast.makeText(status_database_H.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(status_database_H.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
            }        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Vaccine_IDTXT = Vaccine_ID.getText().toString();
                String VaccinenameTXT = Vaccinename.getText().toString();
                String StatusTXT = Status.getText().toString();
                String EnteredID_HTXT=EnteredID_H.getText().toString();
                Boolean check_update_data = DB.updateuserdata(Vaccine_IDTXT,EnteredID_HTXT,VaccinenameTXT, StatusTXT);
                if(check_update_data==true)
                    Toast.makeText(status_database_H.this, "Entry Updated", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(status_database_H.this, "New Entry Not Updated", Toast.LENGTH_SHORT).show();
            }        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Vaccine_IDTXT = Vaccine_ID.getText().toString();
                Boolean checkudeletedata = DB.deletedata(Vaccine_IDTXT);
                if(checkudeletedata==true)
                    Toast.makeText(status_database_H.this, "Entry Deleted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(status_database_H.this, "Entry Not Deleted", Toast.LENGTH_SHORT).show();



            }        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Cursor res = DB.getdata_H();
                if(res.getCount()==0){
                    Toast.makeText(status_database_H.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Vaccine_ID : " + res.getString(0) + "\n");
                    buffer.append("EnteredID : " + res.getString(1) + "\n");
                    buffer.append("Vaccine_name : " + res.getString(2) + "\n");
                    buffer.append("Status :" + res.getString(3) + "\n\n");

                }

                AlertDialog.Builder builder = new AlertDialog.Builder(status_database_H.this);
                builder.setCancelable(true);
                builder.setTitle("Vaccine Status");
                builder.setMessage(buffer.toString());
                builder.show();
            }        });
    }}

