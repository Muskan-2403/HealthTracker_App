package com.ultimate.infits;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class ResetPassword extends AppCompatActivity {

    ImageButton b1;
    Button reset;
    TextInputLayout old_pwd,new_pwd,new_pwd1;
    String old_pwd_str,new_pwd_str,new_pwd1_str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        b1=(ImageButton) findViewById(R.id.newpwd_back);
        reset= findViewById(R.id.button3);
        old_pwd= findViewById(R.id.textcurrentpwd);
        new_pwd=findViewById(R.id.textnewpwd);
        new_pwd1=findViewById(R.id.textnewpwd1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResetPassword.super.onBackPressed();
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                old_pwd_str = old_pwd.getEditText().getText().toString();
                new_pwd_str = new_pwd.getEditText().getText().toString();
                new_pwd1_str = new_pwd1.getEditText().getText().toString();
             //store to db

            }
        });
    }
}