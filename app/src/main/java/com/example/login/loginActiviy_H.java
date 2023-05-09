package com.example.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class loginActiviy_H extends Activity {
    EditText H_edUsername,H_edPassword;
    Button Lbtn_H,Rbtn_H;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_actvity_h);

        H_edUsername = findViewById(R.id.editTextLoginUsername_H);
        H_edPassword = findViewById(R.id.editTextLoginPassword_H);
        Lbtn_H = findViewById(R.id.loginbutton_H);
        Rbtn_H = findViewById(R.id.registerbutton_H);


        Lbtn_H.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = H_edUsername.getText().toString();
                String Password = H_edPassword.getText().toString();
                Database_H db_H = new Database_H(getApplicationContext(),"Child App2",null,1);
                if(username.length()==0 || Password.length()==0){
                    Toast.makeText(loginActiviy_H.this,"Please fill all details" ,Toast.LENGTH_SHORT).show();



                }
                else{
                    if(db_H.login_H(username,Password)==1){
                        Toast.makeText(loginActiviy_H.this,"Login Successful" ,Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(loginActiviy_H.this,status_database_H.class));
                    }else{
                        Toast.makeText(loginActiviy_H.this,"Invalid username and password" ,Toast.LENGTH_SHORT).show();
                    }


                }


            }
        });
        Rbtn_H.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(loginActiviy_H.this,RegisterActiviy_H.class));


            }
        });







    }
}
