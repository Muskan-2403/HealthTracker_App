package com.ultimate.infits;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputLayout;

import java.util.HashMap;
import java.util.Map;

public class LoginScreen extends AppCompatActivity {

    TextView reg, fpass;
    Button login;
    DataFromDatabase dataFromDatabase;
    String url = "http://192.168.127.1/login_dietian.php";
    String userID;
    String passwordStr;
    RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        reg = (TextView) findViewById(R.id.reg);
        fpass = (TextView) findViewById(R.id.fpass);
        login = (Button) findViewById(R.id.logbtn);


        queue = Volley.newRequestQueue(this);
        login.setOnClickListener(v->{
            TextInputLayout username= findViewById(R.id.textInputLayoutUsername);
            TextInputLayout password= findViewById(R.id.textInputLayoutPassword);
            userID = username.getEditText().getText().toString().trim();
            passwordStr = password.getEditText().getText().toString().trim();

             /* EditText username = findViewById(R.id.username);
        EditText password = findViewById(R.id.password);
        TextView name = findViewById(R.id.name);
        userID = username.getText().toString();
            passwordStr = password.getText().toString();*/

            Log.d("LoginClass","before");
            StringRequest stringRequest = new StringRequest(Request.Method.POST,url,response -> {
                if (response.equals("success")){
                    Log.d("LoginClass","success");
                    Toast.makeText(this, "Login success", Toast.LENGTH_SHORT).show();
                    dataFromDatabase.flag = true;
                    dataFromDatabase.name = userID;
                    Log.d("userID",dataFromDatabase.name);
                    startActivity(new Intent(LoginScreen.this,MainActivity.class));
                }
                else if (response.equals("failure")){
                    Log.d("LoginClass","failure");
                    Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT).show();
                }
            },error -> {
                Toast.makeText(LoginScreen.this,error.toString().trim(),Toast.LENGTH_SHORT).show();}){
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> data = new HashMap<>();
                    data.put("userID",userID);
                    data.put("password",passwordStr);
                    return data;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
            Log.d("LoginClass","at end");
        });
    }

    public void register(View view) {
        startActivity(new Intent(LoginScreen.this,Register.class));
    }
}