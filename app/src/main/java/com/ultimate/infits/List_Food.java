package com.ultimate.infits;

import android.graphics.Bitmap;

public class List_Food {
    private String foodName;
    private String foodTime;
    private Bitmap foodImage;
    private String foodServing;

    public List_Food(String foodName,String foodTime,Bitmap foodImage,String foodServing) {
        this.foodName = foodName;
        this.foodImage= foodImage;
        this.foodServing = foodServing;
        this.foodTime = foodTime;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodTime() {
        return foodTime;
    }

    public void setFoodTime(String foodTime) {
        this.foodTime = foodTime;
    }

    public Bitmap getFoodImage() {
        return foodImage;
    }

    public void setFoodImage(Bitmap foodImage) {
        this.foodImage = foodImage;
    }

    public String getFoodServing() {
        return foodServing;
    }

    public void setFoodServing(String foodServing) {
        this.foodServing = foodServing;
    }
}
