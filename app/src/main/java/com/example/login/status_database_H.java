package com.example.login;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class status_database_H extends AppCompatActivity {
    EditText Vaccinename, Status;
    Button insert, update, delete, view;
    DBHelper_H DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_database_h);

        Vaccinename = findViewById(R.id.Vaccinename);
        Status = findViewById(R.id.Status);

        insert = findViewById(R.id.btnInsert);
        update = findViewById(R.id.btnUpdate);
        delete = findViewById(R.id.btnDelete);
        view = findViewById(R.id.btnView);
        DB = new DBHelper_H(this);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String  VaccinenameTXT =  Vaccinename.getText().toString();
                String StatusTXT = Status.getText().toString();


                Boolean check_insert_data = DB.insertuserdata( VaccinenameTXT, StatusTXT);
                if(check_insert_data==true){
                    Toast.makeText(status_database_H.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                    Log.d("OUTPUT", "New Entry Inserted");}
                else
                {Toast.makeText(status_database_H.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
                    Log.d("OUTPUT", "New Entry  Not Inserted");}
            }        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String VaccinenameTXT = Vaccinename.getText().toString();
                //String StatusTXT = Status.getText().toString();


                Boolean check_update_data = DB.deletedata(VaccinenameTXT);
                if(check_update_data){
                    Toast.makeText(status_database_H.this, "Entry Updated", Toast.LENGTH_SHORT).show();
                    Log.d("OUTPUT", " Entry Updated");}
                else
                { Toast.makeText(status_database_H.this, "New Entry Not Updated", Toast.LENGTH_SHORT).show();
                    Log.d("OUTPUT", " Entry not updated");}
            }        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String VaccinenameTXT = Vaccinename.getText().toString();
                Boolean check_delete_data = DB.deletedata(VaccinenameTXT);
                if(check_delete_data){
                    Toast.makeText(status_database_H.this, "Entry Deleted", Toast.LENGTH_SHORT).show();
                    Log.d("OUTPUT", " Entry deleted");}
                else{
                    Toast.makeText(status_database_H.this, "Entry Not Deleted", Toast.LENGTH_SHORT).show();
                    Log.d("OUTPUT", " Entry not deleted");}
            }        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getdata();
                if(res.getCount()==0){
                    Toast.makeText(status_database_H.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    Log.d("OUTPUT", "No Entry Exists");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Vaccine_name : "+res.getString(0)+"\n");
                    buffer.append("Status :"+res.getString(1)+"\n\n");

                }

                AlertDialog.Builder builder = new AlertDialog.Builder(status_database_H.this);
                builder.setCancelable(true);
                builder.setTitle("Vaccine Status");
                builder.setMessage(buffer.toString());
                builder.show();
            }        });
    }
}