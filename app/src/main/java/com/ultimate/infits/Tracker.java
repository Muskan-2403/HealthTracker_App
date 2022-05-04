package com.ultimate.infits;

import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Tracker extends AppCompatActivity{

    boolean expanded = false;
    LinearLayout img_overview,food_list;
    String[] foodTime = {"Breakfast","Lunch","Dinner"};
    @Override
    protected void  onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracker);
        RecyclerView trackerList = findViewById(R.id.tracker_list);
        img_overview = findViewById(R.id.img_list_overview);
        food_list = findViewById(R.id.food_list);
        TackerAdapter ta = new TackerAdapter(foodTime,this);
        trackerList.setAdapter(ta);
        trackerList.setLayoutManager(new LinearLayoutManager(this));
        trackerList.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener());
    }

//    @Override
//    public void onFoodItemClick(int position) {
//        if (expanded){
//            food_list.setVisibility(View.GONE);
//            img_overview.setVisibility(View.VISIBLE);
//        }
//        if (!expanded){
//            food_list.setVisibility(View.VISIBLE);
//            img_overview.setVisibility(View.GONE);
//        }
//    }
}