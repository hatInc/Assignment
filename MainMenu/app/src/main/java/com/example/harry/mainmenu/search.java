package com.example.harry.mainmenu;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class search extends AppCompatActivity {

    private
    Spinner dropdown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        dropdown = findViewById(R.id.dropdownlist);
        String[] items = new String[]{"Chinese", "Indian", "American", "Thai", "British"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
    }

    public void backMain(View view){
        finish();
    }

    public void viewSettings(View view){
        Intent intent = new Intent(this, mainSettings.class);
        startActivity(intent);
    }
}
