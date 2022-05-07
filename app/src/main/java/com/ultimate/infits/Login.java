package com.ultimate.infits;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText username,password;
    Button loginBtn;
    Button register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.unameLogin);
        password = findViewById(R.id.passLogin);
        loginBtn = findViewById(R.id.loginbtn);
        register = findViewById(R.id.regBtn);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this,Register.class));
            }
        });
    }

    public void onLogin(View view){
        String uname = username.getText().toString();
        String pass = password.getText().toString();
        String type = "login";

        BackgroundWorker backgroundworker = new BackgroundWorker(this);
        backgroundworker.execute(type,uname,pass);
    }
}