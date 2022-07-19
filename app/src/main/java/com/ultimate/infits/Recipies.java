package com.ultimate.infits;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class Recipies extends AppCompatActivity {

    ImageView menu;
    ImageButton breakfast,lunch,snacks,dinner;
    TextView meal_time,add_recipes_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipies);

        meal_time = findViewById(R.id.meal_time);
        add_recipes_btn = findViewById(R.id.add_recipes_btn);
        menu=(ImageView)findViewById(R.id.menu_icon_action2);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });

        breakfast = findViewById(R.id.breakfast_btn);
        lunch = findViewById(R.id.lunch_btn);
        snacks = findViewById(R.id.snacks_btn);
        dinner = findViewById(R.id.dinner_btn);
        getSupportFragmentManager().beginTransaction().replace(R.id.recepies_sec,new breakfast()).commit();

        breakfast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().replace(R.id.recepies_sec,new breakfast()).commit();
                meal_time.setText("Breakfast Recipes");
            }
        });
        lunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().replace(R.id.recepies_sec,new lunch()).commit();
                meal_time.setText("Lunch Recipes");
            }
        });
        snacks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().replace(R.id.recepies_sec,new snacks()).commit();
                meal_time.setText("Snacks Recipes");
            }
        });
        dinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().replace(R.id.recepies_sec,new dinner()).commit();
                meal_time.setText("Dinner Recipes");
            }
        });
        add_recipes_btn.setOnClickListener(v->{
            startActivity(new Intent(this,food_details.class));
        });
    }
}