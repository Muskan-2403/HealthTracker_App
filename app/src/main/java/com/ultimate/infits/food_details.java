package com.ultimate.infits;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

public class food_details extends AppCompatActivity {

    Spinner category,cookingTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_details2);

        category=findViewById(R.id.category);
        cookingTime = findViewById(R.id.cookingTime);

        ArrayList<String> time = new ArrayList<>();
        time.add("Breakfast");
        time.add("Lunch");
        time.add("Snacks");
        time.add("Dinner");

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,time );
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        category.setAdapter(spinnerArrayAdapter);

        ArrayList<String> duration = new ArrayList<>();
        duration.add("10 min");
        duration.add("30 min");
        duration.add("1 hour");

        ArrayAdapter<String> spinnerArrayAdapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,duration );
        spinnerArrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        cookingTime.setAdapter(spinnerArrayAdapter2);
    }
}