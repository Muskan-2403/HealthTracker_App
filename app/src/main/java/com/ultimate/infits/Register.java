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

    EditText username,password,qual,email,name,mobile,loc,age,gender;
    Button registerBtn;
    String emailStr,passwordStr,nameStr,usernameStr,qualStr,mobileStr,locStr,ageStr,genderStr;
    Button login;

    String url = "http://192.168.43.91/reg/register_dietian.php";

    String url = "http://192.168.201.1/register_dietian.php";

    RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = findViewById(R.id.usernameReg);
        password = findViewById(R.id.passwordReg);
        qual=findViewById(R.id.qualificationReg);
        email=findViewById(R.id.emailReg);
        name = findViewById(R.id.nameReg);
        mobile = findViewById(R.id.mobileReg);
        loc = findViewById(R.id.locationReg);
        age = findViewById(R.id.ageReg);
        gender = findViewById(R.id.genderReg);
        emailStr=passwordStr=nameStr=usernameStr=qualStr=mobileStr=locStr=ageStr=genderStr="";
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
        usernameStr = username.getText().toString().trim();
        emailStr = email.getText().toString().trim();
        passwordStr = password.getText().toString().trim();
        nameStr = name.getText().toString().trim();
        qualStr = qual.getText().toString().trim();
        mobileStr = mobile.getText().toString().trim();
        locStr = loc.getText().toString().trim();
        ageStr = age.getText().toString().trim();
        genderStr = gender.getText().toString().trim();

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
                data.put("userID",usernameStr);
                data.put("password",passwordStr);
                data.put("name",nameStr);
                data.put("qualification",qualStr);
                data.put("email",emailStr);
                data.put("mobile",mobileStr);
                data.put("location",locStr);
                data.put("age",ageStr);
                data.put("gender",genderStr);
                return data;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }
}