package com.ultimate.infits;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class LoginScreen extends AppCompatActivity {

    String url = "http://192.168.43.91/infits/login.php";
    String userID;
    String passwordStr;
    RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        Button login = findViewById(R.id.login_btn);
        EditText username = findViewById(R.id.username);
        EditText password = findViewById(R.id.password);
        TextView name = findViewById(R.id.name);
        queue = Volley.newRequestQueue(this);
        login.setOnClickListener(v->{
            userID = username.getText().toString();
            passwordStr = password.getText().toString();
            Log.d("LoginClass","before");
            StringRequest stringRequest = new StringRequest(Request.Method.POST,url,response -> {
                if (response.equals("success")){
                    Log.d("LoginClass","s");
                    Toast.makeText(this, "Login success", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginScreen.this,MainActivity.class));
                }
                else if (response.equals("failure")){
                    Log.d("LoginClass","s");
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
}