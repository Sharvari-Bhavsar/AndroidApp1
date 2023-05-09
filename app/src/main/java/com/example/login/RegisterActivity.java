package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    EditText edUsername1,edPassword1,edConfirmPassword,edName,edPhone_no,edEmail;
    Button R1btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);

            edName = findViewById(R.id.editTextRegistrationPersonName);
            edPhone_no = findViewById(R.id.editRTextPhone);
            edEmail = findViewById(R.id.editTextRTextEmailAddress);
            edUsername1 = findViewById(R.id.editTextRegistrationUsername);
            edPassword1 = findViewById(R.id.editTextRegistrationPassword);
            edConfirmPassword = findViewById(R.id.editTextConfirmPassword);
            R1btn = findViewById(R.id.register1button);


            R1btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String username = edUsername1.getText().toString();
                    String password = edPassword1.getText().toString();
                    String confirmPassword = edConfirmPassword.getText().toString();
                    String name = edName.getText().toString();
                    String phone_no = edPhone_no.getText().toString();
                    String Email = edEmail.getText().toString();
                    Database db = new Database(RegisterActivity.this,"Child App",null,1);

                    if(username.length()==0 || password.length()==0 ||name.length()==0 || phone_no.length()==0 ||Email.length()==0){
                        Toast.makeText(RegisterActivity.this,"Please fill all details" ,Toast.LENGTH_SHORT).show();
                    }
                    else if(!password.equals(confirmPassword)){
                        Toast.makeText(RegisterActivity.this,"Password and ConfirmPassword are not same" ,Toast.LENGTH_SHORT).show();

                    }
                    else{
                        db.register(name,phone_no,Email,username,password);
                        Toast.makeText(RegisterActivity.this,"Registration Successful" ,Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegisterActivity.this,loginActivity.class));
                    }

                }
            });

        }
}