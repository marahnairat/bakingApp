package com.example.bakingapp;


import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class BakingService extends IntentService {

    public static String FROM_ACTIVITY_INGREDIENTS_LIST ="FROM_ACTIVITY_INGREDIENTS_LIST";

    public BakingService() {
        super("UpdateBakingService");
    }



    public static void startBakingService(Context context, ArrayList<String> fromActivityIngredientsList) {
        Intent intent = new Intent(context, BakingService.class);
        intent.putExtra(FROM_ACTIVITY_INGREDIENTS_LIST,fromActivityIngredientsList);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        if (intent != null) {
            ArrayList<String> fromActivityIngredientsList = intent.getExtras().getStringArrayList(FROM_ACTIVITY_INGREDIENTS_LIST);
            handleActionUpdateBakingWidgets(fromActivityIngredientsList);

        }
    }

    private void handleActionUpdateBakingWidgets(ArrayList<String> fromActivityIngredientsList) {
        Intent intent = new Intent("android.appwidget.action.APPWIDGET_UPDATE2");
        intent.setAction("android.appwidget.action.APPWIDGET_UPDATE2");
        intent.putExtra(FROM_ACTIVITY_INGREDIENTS_LIST,fromActivityIngredientsList);
        sendBroadcast(intent);
    }
}
