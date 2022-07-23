package com.ultimate.infits;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class food_details extends AppCompatActivity {

    Spinner category,cookingTime;
    String fileName, path;
    File file;
    Bitmap photoBit;
    ActivityResultLauncher<String> photo;
    RecyclerView ingredients_food,nutritions,utensis_food;
    ImageView add_nutrients,add_ingredients,add_utensils,nextStep,pic;
    EditText title;
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
        pic = findViewById(R.id.foodPic);

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

        pic.setOnClickListener(v->{
            photo.launch("image/*");
        });

        photo = registerForActivityResult(
                new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri result) {
                        pic.setImageURI(result);
                        path = result.getPath();
                        file = new File(result.toString());
                        String filename = path.substring(path.lastIndexOf("/")+1);
                        if (filename.indexOf(".") > 0) {
                            fileName = filename.substring(0, filename.lastIndexOf("."));
                        } else {
                            fileName =  filename;
                        }
                        Log.d("MainClass", "Real Path: " + path);
                        Log.d("MainClass", "Filename With Extension: " + filename);
                        Log.d("MainClass", "File Without Extension: " + fileName);
                        try {
                            photoBit = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver() , result);
                            DataFromDatabase.profile = photoBit;
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );

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
            add_new addNew = new add_new("cholestrol","1 mg","10");
            nutrit.add(addNew);
            add_newAdapter obj = new add_newAdapter(getApplicationContext(),nutrit);
            nutritions.setAdapter(obj);
            nutritions.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        });

        add_ingredients.setOnClickListener(v->{
            add_new addNew2 = new add_new("olive oil","1 tbsp","5");
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
            String foodName = title.getText().toString();
            int posCategory = category.getSelectedItemPosition();
            int posCooking = cookingTime.getSelectedItemPosition();

            if(foodName.equals("")||foodName.equals(" ")){
                Toast.makeText(getApplicationContext(),"Enter food Name",Toast.LENGTH_SHORT);
            }else if (posCategory==0){
                Toast.makeText(getApplicationContext(),"Enter valid food category",Toast.LENGTH_SHORT);
            }else if (posCooking==0){
                Toast.makeText(getApplicationContext(),"Enter valid cooking time",Toast.LENGTH_SHORT);
            }else{
                DataFromDatabase.categoryChoosen = time.get(posCategory);
                DataFromDatabase.timeChoosen = duration.get(posCooking);
                DataFromDatabase.foodImage = getStringOfImage(photoBit);
                DataFromDatabase.nutritions = nutrit.toString();
                DataFromDatabase.ingredients = ingred.toString();
                DataFromDatabase.utensil = utensils.toString();

                Intent intent = new Intent(this,AddStepsOfRecipes.class);
                startActivity(intent);
            }
        });
    }

    public String getStringOfImage(Bitmap bm){
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG,100,bo);
        byte[] imageByte = bo.toByteArray();
        String imageEncode = Base64.encodeToString(imageByte, Base64.DEFAULT);
        return imageEncode;
    }
}