package com.ultimate.infits;

import android.app.DownloadManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ClientDetails extends AppCompatActivity  {

    TextView clientidtv,clientemailtv,gendertv,agetv,mobiletv,plantv,startdatetv,enddatetv;
    ImageButton diet_chart_btn,chat_btn,metrics_btn,tracker_btn,health_details_btn;
    ImageView menu,clientPic;
    DataFromDatabase dataFromDatabase;
    String url = "http://192.168.9.1/clientDetails.php";
    String clientID,startdate,enddate;
    RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_details);


//        clientID = getIntent().getStringExtra("clientID");
//        dataFromDatabase.clientuserID = clientID;
        startdate = getIntent().getStringExtra("startDate");
        enddate = getIntent().getStringExtra("endDate");

//        Log.d("ClientDetails",clientID);
        menu=(ImageView)findViewById(R.id.menu_icon_action);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });

        clientPic=findViewById(R.id.imageView3);
        clientidtv = findViewById(R.id.clientIDTV);
        clientidtv.setText(clientID);
        startdatetv = findViewById(R.id.startdateTV);
        startdatetv.setText(startdate);
        enddatetv = findViewById(R.id.enddateTV);
        enddatetv.setText(enddate);
        clientemailtv = findViewById(R.id.clientemailTV);
        gendertv = findViewById(R.id.genderTV);
        agetv = findViewById(R.id.ageTV);
        mobiletv = findViewById(R.id.mobileTV);
        plantv = findViewById(R.id.planTV);
        diet_chart_btn = findViewById(R.id.diet_chart_btn);
        chat_btn = findViewById(R.id.chat_btn);
        metrics_btn = findViewById(R.id.metrics_btn);
        tracker_btn = findViewById(R.id.tracker_btn);
        health_details_btn = findViewById(R.id.health_details_btn);
        //write php code to find if the selected client has a diet chart or no
        // if()
        getSupportFragmentManager().beginTransaction().replace(R.id.client_details_sec,new BlankDietChart()).commit();
        //else
        //getSupportFragmentManager().beginTransaction().replace(R.id.client_details_sec,new dietchart_plan()).commit();


        queue = Volley.newRequestQueue(getApplicationContext());
        Log.d("clientDetails","before");
        StringRequest stringRequest = new StringRequest(Request.Method.POST,url,response ->{
            if (!response.equals("failure")) {
                Log.d("ClientList", "success");
                Log.d("response", response);
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                    String email = jsonObject.getString("email");
                    clientemailtv.setText(email);
                    String gender = jsonObject.getString("gender");
                    gendertv.setText(gender);
                    String age = jsonObject.getString("age");
                    agetv.setText(age);
                    String mobile = jsonObject.getString("mobile");
                    mobiletv.setText(mobile);
                    String plan = jsonObject.getString("plan");
                    plantv.setText(plan);
                    byte[] qrimage = Base64.decode(jsonObject.getString("profilePhoto"),0);
                    Bitmap img = BitmapFactory.decodeByteArray(qrimage,0,qrimage.length);
                    clientPic.setImageBitmap(img);
                    Log.d("ClientDetails","details"+email+" "+gender+" "+age+" "+mobile+" "+plan);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }else if (response.equals("failure")){
                Log.d("clientDetails","failure");
                Toast.makeText(getApplicationContext(), "ClientDetails failed", Toast.LENGTH_SHORT).show();
            }
            },error -> {
            Toast.makeText(getApplicationContext(),error.toString().trim(),Toast.LENGTH_SHORT).show();
        })
        {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                data.put("clientuserID", dataFromDatabase.clientuserID);
                Log.d("ClientDetails","clientid = "+dataFromDatabase.clientuserID);
                return data;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
        Log.d("ClientDetails","at end");



        diet_chart_btn.setOnClickListener(v ->{
            diet_chart_btn.setBackgroundResource(R.drawable.diet_chart_selected);
            chat_btn.setBackgroundResource(R.drawable.chat_unselected);
            metrics_btn.setBackgroundResource(R.drawable.metrics_unselected);
            tracker_btn.setBackgroundResource(R.drawable.tracker_unselected);
            health_details_btn.setBackgroundResource(R.drawable.health_details_unselected);
            // if()
           // getSupportFragmentManager().beginTransaction().replace(R.id.client_details_sec,new BlankDietChart()).commit();
            //else
            getSupportFragmentManager().beginTransaction().replace(R.id.client_details_sec,new dietchart_plan()).commit();
        });
        chat_btn.setOnClickListener(v ->{
            diet_chart_btn.setBackgroundResource(R.drawable.diet_chart_unselected);
            chat_btn.setBackgroundResource(R.drawable.chat_selected);
            metrics_btn.setBackgroundResource(R.drawable.metrics_unselected);
            tracker_btn.setBackgroundResource(R.drawable.tracker_unselected);
            health_details_btn.setBackgroundResource(R.drawable.health_details_unselected);
            getSupportFragmentManager().beginTransaction().replace(R.id.client_details_sec,new Messages_Recycler()).commit();
        });
        metrics_btn.setOnClickListener(v ->{
            diet_chart_btn.setBackgroundResource(R.drawable.diet_chart_unselected);
            chat_btn.setBackgroundResource(R.drawable.chat_unselected);
            metrics_btn.setBackgroundResource(R.drawable.metrics_selected);
            tracker_btn.setBackgroundResource(R.drawable.tracker_unselected);
            health_details_btn.setBackgroundResource(R.drawable.health_details_unselected);

            getSupportFragmentManager().beginTransaction().replace(R.id.client_details_sec, new ClientMetrics()).commit();
        });
        tracker_btn.setOnClickListener(v ->{
            diet_chart_btn.setBackgroundResource(R.drawable.diet_chart_unselected);
            chat_btn.setBackgroundResource(R.drawable.chat_unselected);
            metrics_btn.setBackgroundResource(R.drawable.metrics_unselected);
            tracker_btn.setBackgroundResource(R.drawable.tracker_selected);
            health_details_btn.setBackgroundResource(R.drawable.health_details_unselected);
            //Intent i=new Intent(getApplicationContext(),Tracker.class);
            //startActivity(i);
            getSupportFragmentManager().beginTransaction().replace(R.id.client_details_sec,new TrackerFragment()).commit();
        });
        health_details_btn.setOnClickListener(v ->{
            diet_chart_btn.setBackgroundResource(R.drawable.diet_chart_unselected);
            chat_btn.setBackgroundResource(R.drawable.chat_unselected);
            metrics_btn.setBackgroundResource(R.drawable.metrics_unselected);
            tracker_btn.setBackgroundResource(R.drawable.tracker_unselected);
            health_details_btn.setBackgroundResource(R.drawable.health_details_selected);
            getSupportFragmentManager().beginTransaction().replace(R.id.client_details_sec,new ClientHealthDetails()).commit();
        });
    }
}