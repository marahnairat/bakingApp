package com.example.bakingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.bakingapp.Adapters.RecipeStepAdapter;
import com.example.bakingapp.model.Step;

import java.util.ArrayList;
import java.util.List;

import static com.example.bakingapp.RecipeDetailsActivity.SELECTED_INDEX;
import static com.example.bakingapp.RecipeDetailsActivity.SELECTED_STEPS;

public class RecipeStepDetailActivity extends AppCompatActivity implements RecipeStepAdapter.ListItemClickListener,RecipeStepDetailsFragment.ListItemClickListener {

    RecipeStepDetailsFragment recipeDetailsFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_step_detail);
        if (savedInstanceState == null) {
            recipeDetailsFragment = new RecipeStepDetailsFragment();
        } else {
            recipeDetailsFragment = (RecipeStepDetailsFragment) getSupportFragmentManager().getFragment(savedInstanceState, "my_fragment");
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.recipe_step_fragment, recipeDetailsFragment)
                .commit();




        if (recipeDetailsFragment==null || ! recipeDetailsFragment.isInLayout()) {

        }

    }

    @Override
    public void onListItemClick(List<Step> stepsOut, int clickedItemIndex) {

        Bundle stepBundle = new Bundle();
        stepBundle.putParcelableArrayList(SELECTED_STEPS,(ArrayList<Step>) stepsOut);
        stepBundle.putInt(SELECTED_INDEX,clickedItemIndex);


        final Intent intent = new Intent(this, RecipeStepDetailActivity.class);
        intent.putExtras(stepBundle);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getSupportFragmentManager().putFragment(outState, "my_fragment", recipeDetailsFragment);

    }
}
