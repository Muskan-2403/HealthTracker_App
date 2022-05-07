package com.ultimate.infits;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.StringReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    EditText username,password,qual,email,mobile,loc,age,gender;
    Button registerBtn;
    String emailStr,pass,name;
    Button login;
    String url = "http://192.168.43.91/login/register.php";
    RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = findViewById(R.id.usernameReg);
        password = findViewById(R.id.passwordReg);
        qual=findViewById(R.id.qualificationReg);
        email=findViewById(R.id.emailReg);
        emailStr=pass="";
        registerBtn = findViewById(R.id.registerbtn);
        login = findViewById(R.id.memlog);

        queue = Volley.newRequestQueue(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    public void register(View view) {
        name = username.getText().toString();
        emailStr = email.getText().toString();
        pass = password.getText().toString();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, response -> {
            if(response.equals("success")){
                Toast.makeText(Register.this,"successful",Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(Register.this,"failure",Toast.LENGTH_SHORT).show();
            }
        }, error -> Toast.makeText(Register.this,error.toString().trim(),Toast.LENGTH_SHORT).show()){
            @Override
            protected Map<String,String> getParams() throws AuthFailureError{
                Map<String,String> data = new HashMap<>();
                data.put("name",name);
                data.put("email",emailStr);
                data.put("password",pass);
                return data;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }
}