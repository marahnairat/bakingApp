package com.example.bakingapp.retrofit;

import com.example.bakingapp.model.Recipe;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;


public interface IRecipe {
    @GET("baking.json")
    Call< ArrayList< Recipe > > getRecipe();
}
