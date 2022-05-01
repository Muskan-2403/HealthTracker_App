package com.ultimate.infits;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {

    EditText username,password;
    Button loginBtn;
    connectionHelper connectionHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.unameLogin);
        password = findViewById(R.id.passLogin);
        loginBtn = findViewById(R.id.loginbtn);
        connectionHelper = new connectionHelper(this);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uname = username.getText().toString();
                String pass = password.getText().toString();

                if (uname.equals("")||pass.equals("")){
                    Toast.makeText(login.this,"Please enter all the feilds",Toast.LENGTH_SHORT).show();
                }else {
                    Boolean checkuserpass = connectionHelper.checkUsernamePass(uname,pass);
                    if (checkuserpass==true){
                        Toast.makeText(login.this,"Signin successfull!!",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    }else {
                        Toast.makeText(login.this,"invalid credentials",Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });

    }
}