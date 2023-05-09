package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActiviy_H extends AppCompatActivity {


    EditText edUsername1, edPassword1, edConfirmPassword, edHospitalName, edH_Phone_no, edCity, edState;
    Button R1btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_activiy_h);

        edHospitalName = findViewById(R.id.editTextRegistrationPersonName);
        edH_Phone_no = findViewById(R.id.editRTextPhone);
        edCity = findViewById(R.id.editTextRTextCityAddress);
        edState = findViewById(R.id.editTextStateName);
        edUsername1 = findViewById(R.id.editTextRegistrationUsername);
        edPassword1 = findViewById(R.id.editTextRegistrationPassword);
        edConfirmPassword = findViewById(R.id.editTextConfirmPassword);
        R1btn = findViewById(R.id.register1button);


        R1btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = edUsername1.getText().toString();
                String Password = edPassword1.getText().toString();
                String confirmPassword = edConfirmPassword.getText().toString();
                String Hospitalname = edHospitalName.getText().toString();
                String phone_no = edH_Phone_no.getText().toString();
                String City = edCity.getText().toString();
                String State = edState.getText().toString();

                Database_H db_H = new Database_H(RegisterActiviy_H.this, "Child App2", null, 1);

                if (username.length() == 0 || Password.length() == 0 || Hospitalname.length() == 0 || phone_no.length() == 0 || City.length() == 0 || State.length() == 0) {
                    Toast.makeText(RegisterActiviy_H.this, "Please fill all details", Toast.LENGTH_SHORT).show();
                } else if (!Password.equals(confirmPassword)) {
                    Toast.makeText(RegisterActiviy_H.this, "Password and ConfirmPassword are not same", Toast.LENGTH_SHORT).show();

                } else {
                    db_H.register_H(Hospitalname, phone_no, username, Password, City, State);
                    Toast.makeText(RegisterActiviy_H.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterActiviy_H.this, loginActiviy_H.class));
                }

            }
        });

    }
}