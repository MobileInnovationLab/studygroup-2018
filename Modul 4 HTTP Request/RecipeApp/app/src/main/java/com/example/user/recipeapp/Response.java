package com.example.user.recipeapp;

import com.example.user.recipeapp.Resep;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Response {
    @SerializedName("q")
    private String query;
    @SerializedName("hits")
    private List<Hits> hits = new ArrayList<>();

    public String getQuery() {
        return query;
    }

    public List<Hits> getHits() {
        return hits;
    }
}
