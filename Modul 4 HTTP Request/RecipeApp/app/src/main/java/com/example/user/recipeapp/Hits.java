package com.example.user.recipeapp;

import com.google.gson.annotations.SerializedName;

public class Hits {
    @SerializedName("recipe")
    private Resep resep;

    public Resep getResep() {
        return resep;
    }
}
