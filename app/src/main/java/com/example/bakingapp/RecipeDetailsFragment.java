package com.example.bakingapp;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bakingapp.Adapters.RecipeIngredientAdapter;
import com.example.bakingapp.Adapters.RecipeStepAdapter;
import com.example.bakingapp.model.Recipe;
import com.example.bakingapp.model.Ingredient;
import com.example.bakingapp.model.Step;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static com.example.bakingapp.MainActivity.SELECTED_RECIPES;


public class RecipeDetailsFragment extends Fragment {

    ArrayList<Recipe> recipe;
    //    String recipeName;
    private RecipeIngredientAdapter mRecipeIngredientAdapter;
    private RecyclerView mIngredientRecyclerView;
    private RecyclerView mStepRecyclerView;
    private RecipeStepAdapter mRecipeStepAdapter;

    public RecipeDetailsFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        ActionBar actionBar;
        actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);


    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mRecipeStepAdapter = new RecipeStepAdapter((RecipeDetailsActivity)getActivity());

        recipe = new ArrayList<>();


        Intent intent = getActivity().getIntent();
        recipe = intent.getParcelableArrayListExtra(SELECTED_RECIPES);
        List<Ingredient> ingredients = recipe.get(0).getIngredients();
        List<Step> steps = recipe.get(0).getSteps();

        View rootView = inflater.inflate(R.layout.recipe_detail_fragment, container, false);

        final ArrayList<String> recipeIngredientsForWidgets= new ArrayList<>();

        ingredients.forEach(new Consumer<Ingredient>() {
                                @Override
                                public void accept(Ingredient a) {
                                    recipeIngredientsForWidgets.add(a.getIngredient() + "\n" +
                                            "Quantity: " + a.getQuantity().toString() + "\n" +
                                            "Measure: " + a.getMeasure() + "\n");
                                }
                            }
        );

        BakingService.startBakingService(getContext(),recipeIngredientsForWidgets);

        mIngredientRecyclerView = (RecyclerView) rootView.findViewById(R.id.recipe_ingredient);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mIngredientRecyclerView.setLayoutManager(mLayoutManager);


        LinearLayoutManager mLayoutManagerSteps = new LinearLayoutManager(getActivity());
        mStepRecyclerView = (RecyclerView) rootView.findViewById(R.id.recipe_step);
        mStepRecyclerView.setLayoutManager(mLayoutManagerSteps);
        mRecipeStepAdapter.setRecipeData(getActivity() , steps);
        mStepRecyclerView.setAdapter(mRecipeStepAdapter);



        mRecipeIngredientAdapter = new RecipeIngredientAdapter(getActivity(), ingredients);
        mIngredientRecyclerView.setAdapter(mRecipeIngredientAdapter);


        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d("attached ya 5ra" , "attached");
    }
}
