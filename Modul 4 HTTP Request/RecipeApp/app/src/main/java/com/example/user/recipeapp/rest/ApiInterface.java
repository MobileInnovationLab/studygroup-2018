package com.example.user.recipeapp.rest;

import com.example.user.recipeapp.Response;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("search")
    public Call<Response> getRecipe(@Query("q") String query,
                                    @Query("app_id") String appId,
                                    @Query("app_key") String appKey
                                    );
}
