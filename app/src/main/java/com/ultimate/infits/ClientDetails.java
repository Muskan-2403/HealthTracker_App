package com.ultimate.infits;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class ClientDetails extends AppCompatActivity  {

    ImageButton diet_chart_btn,chat_btn,metrics_btn,tracker_btn,health_details_btn;
    ImageView menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_details);

        menu=(ImageView)findViewById(R.id.menu_icon_action);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });
        diet_chart_btn = findViewById(R.id.diet_chart_btn);
        chat_btn = findViewById(R.id.chat_btn);
        metrics_btn = findViewById(R.id.metrics_btn);
        tracker_btn = findViewById(R.id.tracker_btn);
        health_details_btn = findViewById(R.id.health_details_btn);
        getSupportFragmentManager().beginTransaction().replace(R.id.client_details_sec,new DietChartFragment()).commit();
        diet_chart_btn.setOnClickListener(v ->{
            diet_chart_btn.setBackgroundResource(R.drawable.diet_chart_selected);
            chat_btn.setBackgroundResource(R.drawable.chat_unselected);
            metrics_btn.setBackgroundResource(R.drawable.metrics_unselected);
            tracker_btn.setBackgroundResource(R.drawable.tracker_unselected);
            health_details_btn.setBackgroundResource(R.drawable.health_details_unselected);
            getSupportFragmentManager().beginTransaction().replace(R.id.client_details_sec,new DietChartFragment()).commit();
        });
        chat_btn.setOnClickListener(v ->{
            diet_chart_btn.setBackgroundResource(R.drawable.diet_chart_unselected);
            chat_btn.setBackgroundResource(R.drawable.chat_selected);
            metrics_btn.setBackgroundResource(R.drawable.metrics_unselected);
            tracker_btn.setBackgroundResource(R.drawable.tracker_unselected);
            health_details_btn.setBackgroundResource(R.drawable.health_details_unselected);
            getSupportFragmentManager().beginTransaction().replace(R.id.client_details_sec,new ChatForProfile()).commit();
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
            getSupportFragmentManager().beginTransaction().replace(R.id.client_details_sec,new DietChartFragment()).commit();
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