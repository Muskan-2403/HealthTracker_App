package com.ultimate.infits;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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

public class AcceptRejectClients extends AppCompatActivity {


    DataFromDatabase dataFromDatabase;
    RequestQueue queue;
    String url = "http://192.168.134.1/allClients.php";
    String all_clients_img[]={"app/src/main/res/drawable/doctor_blue_border.png"
            ,"app/src/main/res/drawable/doctor_blue_border.png", "app/src/main/res/drawable/doctor_blue_border.png",
            "app/src/main/res/drawable/doctor_blue_border.png"};
    String all_client_name[]={"Michael Simpson","Michael Simpson","Michael Simpson","Michael Simpson"};
    String all_clients_plan[]={"diet plan","diet plan","diet plan","diet plan"};
    String particular_plan_client_name[]={"Michael Simpson","Michael Simpson","Michael Simpson","Michael Simpson"};
    String particular_plan_clients_img[]={"app/src/main/res/drawable/doctor_blue_border.png"
            ,"app/src/main/res/drawable/doctor_blue_border.png", "app/src/main/res/drawable/doctor_blue_border.png",
            "app/src/main/res/drawable/doctor_blue_border.png"};
    String particular_clients_plan[]={"diet plan","diet plan","diet plan","diet plan"};

    List<AcceptRejectList> plan_diet= new ArrayList<>();
    List<AcceptRejectList> plan_premium= new ArrayList<>();
    List<AcceptRejectList> plan_1to1= new ArrayList<>();
    List<AcceptRejectList> all_plans=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accept_reject_clients);
        ImageView back=findViewById(R.id.accept_reject_back_button);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        RecyclerView recyclerView2=findViewById(R.id.recycler_view_for_all_clients);
        RecyclerView recyclerView1=findViewById(R.id.recycler_view_for_plans);
        Button diet_btn=findViewById(R.id.plan_diet);
        Button premium_btn=findViewById(R.id.plan_premium);
        Button one_to_one_btn= findViewById(R.id.plan_1to1);

        queue = Volley.newRequestQueue(getApplicationContext());
        Log.d("allClients","before");
        StringRequest stringRequest = new StringRequest(Request.Method.POST,url, response -> {
            if (!response.equals("failure")){
                Log.d("allClients","success");
                Log.d("response",response);

                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i=0;i<jsonArray.length();i++){
                        JSONObject object = jsonArray.getJSONObject(i);
                        String clientid = object.getString("clientID");
                        String plan = object.getString("plan");
                        String dieticianID = object.getString("dietitianID");
                        if (dieticianID=="null"){
                            AcceptRejectList obj=new AcceptRejectList(all_clients_img[i],clientid,plan);
                            Log.d("plan_x",plan.toString());
                            all_plans.add(obj);
                            if (plan.equals("diet chart")){
                                plan_diet.add(obj);
                            }
                            if (plan.toString()==" 1to1"){
                                Log.d("1to1"," 1to1");
                                plan_1to1.add(obj);
                            }
                            if (plan.equals("premium")){
                                plan_premium.add(obj);
                            }
                        }
                    }
                    Log.d("all_plans", String.valueOf(all_plans));
                    Log.d("plan_diet", String.valueOf(plan_diet));
                    Log.d("plan_1to1", String.valueOf(plan_1to1));
                    Log.d("plan_premium", String.valueOf(plan_premium));
                    AcceptRejectListAdapter adapx= new AcceptRejectListAdapter(getApplicationContext(),all_plans, (AcceptRejectListAdapter.Selecteditem) this);
                    recyclerView2.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));
                    recyclerView2.setAdapter(adapx);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Toast.makeText(getApplicationContext(), "allClients success", Toast.LENGTH_SHORT).show();
            }
            else if (response.equals("failure")){
                Log.d("allClients","failure");
                Toast.makeText(getApplicationContext(), "allClients failed", Toast.LENGTH_SHORT).show();
            }
        },error -> {
            Toast.makeText(getApplicationContext(),error.toString().trim(),Toast.LENGTH_SHORT).show();});

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
        Log.d("allClients","at end");



        diet_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                diet_btn.setBackgroundColor(Color.parseColor("#1D8BF1"));
                diet_btn.setTextColor(Color.parseColor("#FFFFFF"));
                premium_btn.setBackgroundResource(R.drawable.plan_button_background);
                premium_btn.setTextColor(Color.parseColor("#1D8BF1"));
                one_to_one_btn.setBackgroundResource(R.drawable.plan_button_background);
                one_to_one_btn.setTextColor(Color.parseColor("#1D8BF1"));
                //fetch data from database and store in particular_plan_x arrays
                AcceptRejectListAdapter adap= new AcceptRejectListAdapter(getApplicationContext(),plan_diet);
                recyclerView1.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));
                recyclerView1.setAdapter(adap);
            }
        });
        premium_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                premium_btn.setBackgroundColor(Color.parseColor("#1D8BF1"));
                premium_btn.setTextColor(Color.parseColor("#FFFFFF"));
                diet_btn.setBackgroundResource(R.drawable.plan_button_background);
                diet_btn.setTextColor(Color.parseColor("#1D8BF1"));
                one_to_one_btn.setBackgroundResource(R.drawable.plan_button_background);
                one_to_one_btn.setTextColor(Color.parseColor("#1D8BF1"));
                //fetch data from database and store in particular_plan_x arrays
                AcceptRejectListAdapter adap= new AcceptRejectListAdapter(getApplicationContext(),plan_premium);
                recyclerView1.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));
                recyclerView1.setAdapter(adap);

            }
        });
        one_to_one_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                one_to_one_btn.setBackgroundColor(Color.parseColor("#1D8BF1"));
                one_to_one_btn.setTextColor(Color.parseColor("#FFFFFF"));
                premium_btn.setBackgroundResource(R.drawable.plan_button_background);
                premium_btn.setTextColor(Color.parseColor("#1D8BF1"));
                diet_btn.setBackgroundResource(R.drawable.plan_button_background);
                diet_btn.setTextColor(Color.parseColor("#1D8BF1"));
                //fetch data from database and store in particular_plan_x arrays
                AcceptRejectListAdapter adap= new AcceptRejectListAdapter(getApplicationContext(),plan_1to1);
                recyclerView1.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));
                recyclerView1.setAdapter(adap);

            }
        });
//            for(int i=0;i<all_plans.size();i++)
//        {
//            List<AcceptRejectList> obj= new ArrayList<>();
//            obj= (List<AcceptRejectList>) all_plans.get(i);
//            Log.d("xhdbekjf", String.valueOf(obj));
//        }

//        for(int i=0;i<all_clients_img.length;i++)
//        {
//            AcceptRejectList obj=new AcceptRejectList(all_clients_img[i],all_client_name[i],all_clients_plan[i]);
//            all_plans.add(obj);
//        }
//        AcceptRejectListAdapter adap1= new AcceptRejectListAdapter(getApplicationContext(),all_plans);
//        recyclerView2.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));
//        recyclerView2.setAdapter(adap1);






    }


}