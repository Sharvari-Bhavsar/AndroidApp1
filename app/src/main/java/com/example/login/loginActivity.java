package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import android.widget.Toast;

public class loginActivity extends AppCompatActivity {

    EditText edUsername;
    EditText edPassword;
    int User_id;
    Button Lbtn,Rbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edUsername = findViewById(R.id.editTextLoginUsername);
        edPassword = findViewById(R.id.editTextLoginPassword);
        Lbtn = findViewById(R.id.loginbutton);
        Rbtn = findViewById(R.id.registerbutton);


        Lbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edUsername.getText().toString();
                String password = edPassword.getText().toString();
                Database db = new Database(loginActivity.this);
                if(username.length()==0 || password.length()==0){
                    Toast.makeText(loginActivity.this,"Please fill all details" ,Toast.LENGTH_SHORT).show();



                }
                else{
                    if(db.login(username, password) == 1){
                        new  Database(loginActivity.this).USERdumpCursorInLogCat();

                        Toast.makeText(loginActivity.this,"Login Successful" ,Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(loginActivity.this,status_database_U.class));

                    }else{
                        Toast.makeText(loginActivity.this,"Invalid username and password" ,Toast.LENGTH_SHORT).show();
                    }


                }


            }
        });
        Rbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(loginActivity.this,RegisterActivity.class));


            }
        });







    }
}