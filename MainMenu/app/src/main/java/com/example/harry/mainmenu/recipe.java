package com.example.harry.mainmenu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class recipe extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
    }

    public void goToMain(View view){
        Intent intent = new Intent(this, menu.class);
        startActivity(intent);
        finish();
    }

    public void goToSettings(View view){
        Intent intent = new Intent(this, mainSettings.class);
        startActivity(intent);
    }

}
