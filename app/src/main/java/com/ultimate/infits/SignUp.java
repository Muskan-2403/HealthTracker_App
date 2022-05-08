package com.ultimate.infits;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class SignUp extends AppCompatActivity {
    Button signUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        signUp = findViewById(R.id.signUpBtn);
        signUp.setOnClickListener(v->{
//            String type = "login";
//            BackgroundWorker backgroundWorker = new BackgroundWorker(this);
//            backgroundWorker.execute(type, username, password);
        });
    }
}