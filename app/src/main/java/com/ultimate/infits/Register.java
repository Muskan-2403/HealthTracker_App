package com.ultimate.infits;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
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

    EditText username,password,password_recheck,qual,email,name,mobile,loc,age,gender;
    Button registerBtn;
    DataFromDatabase dataFromDatabase;
    String emailStr,passwordStr,passwordrecheckStr,nameStr,usernameStr,qualStr,mobileStr,locStr,ageStr,genderStr;
    TextView login;
    RadioButton gender_male,gender_female;

    String url = "http://192.168.101.1/register_dietian.php";

    RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_registration);

        username = findViewById(R.id.usernameReg);
        password = findViewById(R.id.passwordReg);
        password_recheck= findViewById(R.id.password_recheckReg);
        qual=findViewById(R.id.qualificationReg);
        email=findViewById(R.id.emailReg);
        name = findViewById(R.id.nameReg);
        mobile = findViewById(R.id.mobileReg);
        loc = findViewById(R.id.locationReg);
        age = findViewById(R.id.ageReg);
        //gender = findViewById(R.id.genderReg);
        emailStr=passwordStr=nameStr=usernameStr=qualStr=mobileStr=locStr=ageStr=genderStr="";
        registerBtn = findViewById(R.id.registerbtn);
        login = findViewById(R.id.memlog);
        RadioButton agree= findViewById(R.id.radioButton);
        gender_male= findViewById(R.id.maleReg);
        gender_female= findViewById(R.id.femaleReg);

        queue = Volley.newRequestQueue(this);
      /*  password.setTransformationMethod(PasswordTransformationMethod.getInstance());
        password_recheck.setTransformationMethod(PasswordTransformationMethod.getInstance());
        password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }
        });
        password_recheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                password_recheck.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }
        });*/
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Register.this,LoginScreen.class);
                startActivity(i);
                finish();
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usernameStr = username.getText().toString().trim();
                emailStr = email.getText().toString().trim();
                passwordStr = password.getText().toString().trim();
                passwordrecheckStr = password_recheck.getText().toString().trim();
                nameStr = name.getText().toString().trim();
                qualStr = qual.getText().toString().trim();
                mobileStr = mobile.getText().toString().trim();
                locStr = loc.getText().toString().trim();
                ageStr = age.getText().toString().trim();
                // genderStr = gender.getText().toString().trim();
                int flag=0;
                if(!agree.isChecked()) {
                    Toast.makeText(getApplicationContext(), "Kindly agree to the terms and conditions", Toast.LENGTH_SHORT).show();
                    flag=1;
                }
                else if(!passwordrecheckStr.equals(passwordStr)) {
                    Toast.makeText(getApplicationContext(), "Passwords donot match", Toast.LENGTH_SHORT).show();
                    flag=1;
                }
                else if((usernameStr.equals(""))|| (usernameStr.equals(" ")) || (passwordStr.equals("")) || (passwordStr.equals(" "))
                        || (emailStr.equals("")) || (emailStr.equals(" ")) || (nameStr.equals("")) || (nameStr.equals(" ")) ||
                        (qualStr.equals("")) || (qualStr.equals(" ")) || (mobileStr.equals("")) || (mobileStr.equals(" "))
                        || (ageStr.equals("")) || (ageStr.equals(" ")) || (locStr.equals("")) || locStr.equals(" ")) {
                    Toast.makeText(getApplicationContext(), "Fill all the details", Toast.LENGTH_SHORT).show();
                    flag=1;
                }else if(!(gender_male.isChecked()) && !(gender_female.isChecked())) {
                    Toast.makeText(getApplicationContext(), "Select one of the genders", Toast.LENGTH_SHORT).show();
                    flag=1;
                }
                if (flag==0)
                {
                    if (gender_male.isChecked())
                        genderStr="M";
                    else
                        genderStr="F";
                    register(usernameStr,passwordStr,emailStr,nameStr,qualStr,mobileStr,ageStr,genderStr,locStr);

                }
            }
        });
    }

    public void register(String usernameStr,String passwordStr,String emailStr,String nameStr,
                         String qualStr,String mobileStr,String ageStr,String genderStr,String locStr) {


        Log.d("RegisterClass","at start");
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, response -> {
            if(response.equals("success")){
                Toast.makeText(Register.this,"successful",Toast.LENGTH_LONG).show();
                dataFromDatabase.flag = true;
                dataFromDatabase.name = usernameStr;
                dataFromDatabase.password = passwordStr;
                dataFromDatabase.email = emailStr;
                dataFromDatabase.name = nameStr;
                dataFromDatabase.qualification = qualStr;
                dataFromDatabase.mobile = mobileStr;
                dataFromDatabase.age = ageStr;
                dataFromDatabase.gender = genderStr;
                dataFromDatabase.location = locStr;
                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
                finish();
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
                Log.i("details", data.get("userID"));
                return data;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
        Log.d("RegisterClass","at end");
    }
}