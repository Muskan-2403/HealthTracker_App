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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginScreen extends AppCompatActivity {

    TextView reg, fpass;
    Button login;
    DataFromDatabase dataFromDatabase;
    String url = "http://192.168.158.1/login_dietian.php";
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
                if (!response.equals("failure")){
                    Log.d("LoginClass","success");
                    Log.d("response LoginClass",response);

                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        JSONObject object = jsonArray.getJSONObject(0);
                        DataFromDatabase.flag=true;
                        DataFromDatabase.dietitianuserID= object.getString("dietitianuserID");
                        DataFromDatabase.name=object.getString("name");
                        DataFromDatabase.password = object.getString("password");
                        DataFromDatabase.qualification=object.getString("qualification");
                        DataFromDatabase.email=object.getString("email");
                        DataFromDatabase.mobile=object.getString("mobile");
                        DataFromDatabase.profilePhoto=object.getString("profilePhoto");
                        DataFromDatabase.location=object.getString("location");
                        DataFromDatabase.age= object.getString("age");
                        DataFromDatabase.gender = object.getString("gender");
                        DataFromDatabase.about_me = object.getString("about_me");
                        DataFromDatabase.experience = object.getString("experience");
                        Log.d("Login Screen","Dietician user id = "+dataFromDatabase.dietitianuserID);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    Toast.makeText(this, "Login success", Toast.LENGTH_SHORT).show();
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

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),Register.class);
                startActivity(i);
            }
        });

        fpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),ForgotPassword.class);
                startActivity(i);
            }
        });
    }

   }