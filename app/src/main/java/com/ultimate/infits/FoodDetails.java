package com.ultimate.infits;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class FoodDetails extends AppCompatActivity {

    RecyclerView ingredients,instructions;
    List<String> ingrd = new ArrayList<>();
    List<String> instr = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_details);

        ingredients = findViewById(R.id.ingredients_food);
        instructions = findViewById(R.id.instructions_food);
//        Ingredients obj = new Ingredients("2 tbsp olive oil");

        ingrd.add("2 tbsp olive oil");
        ingrd.add("5 tbsp olive oil");
//        Log.d("size",String.valueOf(ingrd.size()));
        IngrdAdapter adap = new IngrdAdapter(ingrd,getApplicationContext());
        ingredients.setAdapter(adap);
        ingredients.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        instr.add("2 tbsp olive oil");
        instr.add("5 tbsp olive oil");
//        Log.d("size",String.valueOf(ingrd.size()));
        IngrdAdapter adap2 = new IngrdAdapter(ingrd,getApplicationContext());
        instructions.setAdapter(adap2);
        instructions.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

    }
}