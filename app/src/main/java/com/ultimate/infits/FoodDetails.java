package com.ultimate.infits;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FoodDetails extends AppCompatActivity {

    RecyclerView ingredients,instructions;
    TextView Food_Name,br_time,br_serving,LinkFood;
    ImageView foodpic;
    TextView nuttv1,nuttv2,nuttv3,nuttv4;
    TextView nuttv11,nuttv21,nuttv31,nuttv41;
    TextView nuttv12,nuttv22,nuttv32,nuttv42;
    List<String> ingrd = new ArrayList<>();
    List<String> instr = new ArrayList<>();

    String url = "http://192.168.244.1/foodDetails.php";
    RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_details);

        Intent in = getIntent();
        String foodName = in.getStringExtra("Food Name");
        Log.d("Intent Extra",foodName);
        Food_Name = findViewById(R.id.Food_Name);
        Food_Name.setText(foodName);

        br_time = findViewById(R.id.br_time);
        br_serving = findViewById(R.id.br_serving);
        LinkFood = findViewById(R.id.Link);
        foodpic = findViewById(R.id.food_pic);

        ingredients = findViewById(R.id.ingredients_food);
        instructions = findViewById(R.id.instructions_food);
        nuttv1=findViewById(R.id.nuttvv1);
        nuttv2=findViewById(R.id.nuttvv2);
        nuttv3=findViewById(R.id.nuttvv3);
        nuttv4=findViewById(R.id.nuttvv4);

        nuttv11=findViewById(R.id.nuttv11);
        nuttv21=findViewById(R.id.nuttv21);
        nuttv31=findViewById(R.id.nuttv31);
        nuttv41=findViewById(R.id.nuttv41);

        nuttv12=findViewById(R.id.nuttv12);
        nuttv22=findViewById(R.id.nuttv22);
        nuttv32=findViewById(R.id.nuttv32);
        nuttv42=findViewById(R.id.nuttv42);
//        Ingredients obj = new Ingredients("2 tbsp olive oil");


//        Log.d("size",String.valueOf(ingrd.size()));


//        instr.add("2 tbsp olive oil");
//        instr.add("5 tbsp olive oil");
//        Log.d("size",String.valueOf(ingrd.size()));


        queue = Volley.newRequestQueue(getApplicationContext());
        Log.d("foodDetails","before");
        StringRequest stringRequest = new StringRequest(Request.Method.POST,url, response -> {
            if (!response.equals("failure")){
                Log.d("foodDetails","success");
                Log.d("response",response);

                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i=0;i< jsonArray.length();i++){
                        JSONObject object = jsonArray.getJSONObject(i);

                        String Nutritions = object.getString("Nutritions");
                        br_time.setText(object.getString("time")+" minutes");
                        br_serving.setText(object.getString("serving")+" serving");
                        LinkFood.setText(object.getString("link"));
                        instr.add(object.getString("instruction"));

                        String Ingredients = object.getString("Ingredients");
                        String Utensils = object.getString("utensils");
                        ingrd.add(object.getString("Ingredients"));

                        //"image" to foodpic
                        byte[] qrimage = Base64.decode(object.getString("image"),0);
                        Bitmap img = BitmapFactory.decodeByteArray(qrimage,0,qrimage.length);
                        foodpic.setImageBitmap(img);
                    }
                    IngrdAdapter adap = new IngrdAdapter(ingrd,getApplicationContext());
                    ingredients.setAdapter(adap);
                    ingredients.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

                    IngrdAdapter adap2 = new IngrdAdapter(instr,getApplicationContext());
                    instructions.setAdapter(adap2);
                    instructions.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Toast.makeText(getApplicationContext(), "foodDetails success", Toast.LENGTH_SHORT).show();
            }
            else if (response.equals("failure")){
                Toast.makeText(getApplicationContext(), "foodDetails failure", Toast.LENGTH_SHORT).show();
            }
        },error -> {
            Toast.makeText(getApplicationContext(),error.toString().trim(),Toast.LENGTH_SHORT).show();
        })
        {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                data.put("name", foodName);
                return data;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
        Log.d("foodDetails","at end");

    }
}