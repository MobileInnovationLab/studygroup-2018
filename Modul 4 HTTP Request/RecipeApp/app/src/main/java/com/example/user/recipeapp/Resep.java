package com.example.user.recipeapp;

import com.google.gson.annotations.SerializedName;

public class Resep {
    @SerializedName("image")
    private String image;
    @SerializedName("label")
    private String title;
    @SerializedName("calories")
    private String calories;

    public Resep(){

    }

    public Resep(String image, String title, String calories) {
        this.image = image;
        this.title = title;
        this.calories = calories;
    }

    public String getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getCalories() {
        return calories;
    }
}
