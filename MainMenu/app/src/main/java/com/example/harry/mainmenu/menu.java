package com.example.harry.mainmenu;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }

    public void goToSettings(View view){
        Intent intent = new Intent(this, mainSettings.class);
        startActivity(intent);
    }


    public void goToRecipe(View view){
        Intent intent = new Intent(this, recipe.class);
        startActivity(intent);
        finish();
    }
}
