package com.ultimate.infits;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class AcceptRejectClients extends AppCompatActivity {

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

    List<AcceptRejectList> plan_wise= new ArrayList<>();
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
        Button diet_btn=findViewById(R.id.plan_diet);
        Button premium_btn=findViewById(R.id.plan_premium);
        Button one_to_one_btn= findViewById(R.id.plan_1to1);
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

            }
        });
        RecyclerView recyclerView1=findViewById(R.id.recycler_view_for_plans);
        for(int i=0;i<particular_plan_clients_img.length;i++)
        {
            AcceptRejectList obj1=new AcceptRejectList(particular_plan_clients_img[i],particular_plan_client_name[i],particular_clients_plan[i]);
            plan_wise.add(obj1);
        }
        AcceptRejectListAdapter adap= new AcceptRejectListAdapter(getApplicationContext(),plan_wise);
        recyclerView1.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));
        recyclerView1.setAdapter(adap);
        RecyclerView recyclerView2=findViewById(R.id.recycler_view_for_all_clients);
        for(int i=0;i<all_clients_img.length;i++)
        {
            AcceptRejectList obj=new AcceptRejectList(all_clients_img[i],all_client_name[i],all_clients_plan[i]);
            all_plans.add(obj);
        }
        AcceptRejectListAdapter adap1= new AcceptRejectListAdapter(getApplicationContext(),all_plans);
        recyclerView2.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));
        recyclerView2.setAdapter(adap1);

    }
}