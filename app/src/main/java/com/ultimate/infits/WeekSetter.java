package com.ultimate.infits;

import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class WeekSetter extends AppCompatActivity {
    TextView sun_date,mon_date,tue_date,wed_date,thur_date,fri_date,sat_date;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week_setter);
        setUpUI();
        TextView[] dateArr = {sun_date,mon_date,tue_date,wed_date,thur_date,fri_date,sat_date};
        ArrayList<String> days = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd ");
        Calendar cal = Calendar.getInstance();
        if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
            cal.add(Calendar.DAY_OF_YEAR, 0);
            days.add(sdf.format(cal.getTime()));
            for(int i = 0; i< 6; i++){
                cal.add(Calendar.DAY_OF_YEAR, 1);
                days.add(sdf.format(cal.getTime()));
            }
        }
        if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY){
            cal.add(Calendar.DAY_OF_YEAR, -1);
            days.add(sdf.format(cal.getTime()));
            for(int i = 0; i< 6; i++){
                cal.add(Calendar.DAY_OF_YEAR, 1);
                days.add(sdf.format(cal.getTime()));
            }
        }
        if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY){
            cal.add(Calendar.DAY_OF_YEAR, -2);
            days.add(sdf.format(cal.getTime()));
            for(int i = 0; i< 6; i++){
                cal.add(Calendar.DAY_OF_YEAR, 1);
                days.add(sdf.format(cal.getTime()));
            }
        }
        if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY){
            cal.add(Calendar.DAY_OF_YEAR, -3);
            days.add(sdf.format(cal.getTime()));
            for(int i = 0; i< 6; i++){
                cal.add(Calendar.DAY_OF_YEAR, 1);
                days.add(sdf.format(cal.getTime()));
            }
        }
        if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY){
            cal.add(Calendar.DAY_OF_YEAR, -4);
            days.add(sdf.format(cal.getTime()));
            for(int i = 0; i< 6; i++){
                cal.add(Calendar.DAY_OF_YEAR, 1);
                days.add(sdf.format(cal.getTime()));
            }
        }
        if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY){
            cal.add(Calendar.DAY_OF_YEAR, -5);
            days.add(sdf.format(cal.getTime()));
            for(int i = 0; i< 6; i++){
                cal.add(Calendar.DAY_OF_YEAR, 1);
                days.add(sdf.format(cal.getTime()));
            }
        }
        if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY){
            cal.add(Calendar.DAY_OF_YEAR, -6);
            days.add(sdf.format(cal.getTime()));
            for(int i = 0; i< 6; i++){
                cal.add(Calendar.DAY_OF_YEAR, 1);
                days.add(sdf.format(cal.getTime()));
            }
        }

        for (int a = 0 ; a < dateArr.length;a++) {
            dateArr[a].setText(days.get(a));
        }
    }
    private void setUpUI(){
        sun_date = findViewById(R.id.sun_date);
        mon_date = findViewById(R.id.mon_date);
        tue_date = findViewById(R.id.tue_date);
        wed_date = findViewById(R.id.wed_date);
        thur_date = findViewById(R.id.thur_date);
        fri_date = findViewById(R.id.fri_date);
        sat_date = findViewById(R.id.sat_date);
    }

}