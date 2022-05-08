package com.ultimate.infits;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        Button login = findViewById(R.id.login_btn);
        EditText username = findViewById(R.id.username);
        EditText password = findViewById(R.id.password);
        TextView name = findViewById(R.id.name);
        login.setOnClickListener(v->{
            new LoginWorker(this,name).execute(username.getText().toString(),password.getText().toString());
            new FetchData().execute();
        });
    }
}