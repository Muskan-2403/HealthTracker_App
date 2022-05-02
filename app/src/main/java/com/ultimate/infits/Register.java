package com.ultimate.infits;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    EditText username,password;
    Button registerBtn;
    connectionHelper connectionHelper;
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = findViewById(R.id.usernameReg);
        password = findViewById(R.id.passwordReg);
        registerBtn = findViewById(R.id.registerbtn);
        login = findViewById(R.id.memlog);
        connectionHelper = new connectionHelper(this);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uname = username.getText().toString();
                String pass = password.getText().toString();

                if (uname.equals("")||pass.equals("")){
                    Toast.makeText(Register.this,"Please enter all the feilds",Toast.LENGTH_SHORT).show();
                }else {
                    Boolean checkusernamePass = connectionHelper.checkUsernamePass(uname,pass);
                    if (checkusernamePass==false){
                        Boolean insert = connectionHelper.insertData(uname,pass);
                        if (insert==true){
                            Toast.makeText(Register.this,"registered successfully!!",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(intent);
                        }else {
                            Toast.makeText(Register.this,"registration failed",Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(Register.this,"user already exists. Please login",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Login.class));
            }
        });
    }
}