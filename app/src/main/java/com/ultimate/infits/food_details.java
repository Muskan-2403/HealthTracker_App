package com.ultimate.infits;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class food_details extends AppCompatActivity {

    Spinner category,cookingTime;
    RecyclerView ingredients_food,nutritions,utensis_food;
    ImageView add_nutrients,add_ingredients,add_utensils,nextStep;
    List<add_new> ingred= new ArrayList<>();
    List<add_new> nutrit= new ArrayList<>();
    List<add_new> utensils= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_details2);

        category=findViewById(R.id.category);
        cookingTime = findViewById(R.id.cookingTime);
        ingredients_food = findViewById(R.id.ingredients_food);
        nutritions = findViewById(R.id.nutritions);
        utensis_food = findViewById(R.id.utensils_food);
        add_ingredients = findViewById(R.id.add_ingredients);
        add_utensils = findViewById(R.id.add_utensils);
        add_nutrients = findViewById(R.id.add_nutrients);
        nextStep = findViewById(R.id.nextStep);

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

//        add_new addNew = new add_new("cholestrol","mg","10");
//        nutrit.add(addNew);
//        add_newAdapter obj = new add_newAdapter(getApplicationContext(),nutrit);
//        nutritions.setAdapter(obj);
//        nutritions.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//
//        add_new addNew2 = new add_new("olive oil","tbsp","5");
//        ingred.add(addNew2);
//        add_newAdapter obj2 = new add_newAdapter(getApplicationContext(),ingred);
//        ingredients_food.setAdapter(obj2);
//        ingredients_food.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//
//        add_new addNew3 = new add_new("cutting Board","","1");
//        utensils.add(addNew3);
//        add_newAdapter obj3 = new add_newAdapter(getApplicationContext(),utensils);
//        utensis_food.setAdapter(obj3);
//        utensis_food.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        add_nutrients.setOnClickListener(v->{
            add_new addNew = new add_new("cholestrol","mg","10");
            nutrit.add(addNew);
            add_newAdapter obj = new add_newAdapter(getApplicationContext(),nutrit);
            nutritions.setAdapter(obj);
            nutritions.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        });

        add_ingredients.setOnClickListener(v->{
            add_new addNew2 = new add_new("olive oil","tbsp","5");
            ingred.add(addNew2);
            add_newAdapter obj2 = new add_newAdapter(getApplicationContext(),ingred);
            ingredients_food.setAdapter(obj2);
            ingredients_food.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        });


        add_utensils.setOnClickListener(v->{
            add_new addNew3 = new add_new("cutting Board","","1");
            utensils.add(addNew3);
            add_newAdapter obj3 = new add_newAdapter(getApplicationContext(),utensils);
            utensis_food.setAdapter(obj3);
            utensis_food.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        });
        nextStep.setOnClickListener(v->{
            Intent intent = new Intent(this,AddStepsOfRecipes.class);
            startActivity(intent);
        });
    }
}