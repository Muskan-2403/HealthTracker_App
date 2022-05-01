package com.ultimate.infits;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class register extends AppCompatActivity {

    EditText username,password;
    Button registerBtn;
    connectionHelper connectionHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = findViewById(R.id.usernameReg);
        password = findViewById(R.id.passwordReg);
        registerBtn = findViewById(R.id.registerbtn);
        connectionHelper = new connectionHelper(this);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uname = username.getText().toString();
                String pass = password.getText().toString();

                if (uname.equals("")||pass.equals("")){
                    Toast.makeText(register.this,"Please enter all the feilds",Toast.LENGTH_SHORT).show();
                }else {
                    Boolean checkpass = connectionHelper.checkUsernamePass(uname,pass);
                    if (checkpass==false){
                        Boolean insert = connectionHelper.insertData(uname,pass);
                        if (insert==true){
                            Toast.makeText(register.this,"registered successfully!!",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(intent);
                        }else {
                            Toast.makeText(register.this,"registration failed",Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(register.this,"user already exists. Please login",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}