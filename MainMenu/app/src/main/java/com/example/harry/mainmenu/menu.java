package com.example.harry.mainmenu;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.content.Intent;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

public class menu extends AppCompatActivity {

    private static final int ADD_RECIPE = 1;
    private ArrayList<userRecipes> userMadeRecipes= new ArrayList<userRecipes>();
    private int recipeIdentify = 0;
    private LinearLayout recipeSection;
    private LinearLayout newSection;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        recipeSection = (LinearLayout) findViewById(R.id.mainLinear);

    }

    public void addRecipe(View view){
        Intent intent = new Intent(this, maker.class);
        startActivityForResult(intent, ADD_RECIPE);
    }

    public void searchRecipe(View view){
        Intent intent = new Intent(this, search.class);
        startActivity(intent);
    }

    public void viewSettings(View view){
        Intent intent = new Intent(this, mainSettings.class);
        startActivity(intent);
    }

    public void viewBasket(View view){
        Intent intent = new Intent(this, basket.class);
        startActivity(intent);
    }

    public void viewFavourites(View view){
        Intent intent = new Intent(this, favourites.class);
        startActivity(intent);
    }

    public void viewRecipe(View view){
        Intent intent = new Intent(this, recipe.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch(requestCode){
            case ADD_RECIPE:
                if(resultCode == RESULT_OK){
                    userRecipes tempRecipe = new userRecipes();

                    tempRecipe.setName(data.getStringExtra("recipeName"));
                    tempRecipe.setIngredients((ArrayList<ingredient>) data.getSerializableExtra("recipeIngredients"));
                    tempRecipe.setRecipeTasks((ArrayList<task>) data.getSerializableExtra("recipeTasks"));

                    try {
                        Bitmap bitmapImage = BitmapFactory.decodeStream(this.openFileInput("myImage"));
                        tempRecipe.setRecipeImage(bitmapImage);
                    }
                    catch(IOException e){
                        //need error code here
                    }

                    userMadeRecipes.add(tempRecipe);

                    createRecipeSection(tempRecipe);

                }

                break;
        }
    }

    private void createRecipeSection(userRecipes newRecipeView){
        LinearLayout.LayoutParams lParamsLayout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 300);

        LinearLayout newLayout = new LinearLayout(this);

        newLayout.setOrientation(LinearLayout.HORIZONTAL);
        newLayout.setLayoutParams(lParamsLayout);
        newLayout.setBackgroundResource(R.drawable.edit_text_background);
        newLayout.setId(recipeIdentify);


        recipeSection.addView(newLayout);


        newSection = (LinearLayout) findViewById(recipeIdentify);

        LinearLayout.LayoutParams lParamsImage = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        ImageView newImage = new ImageView(this);
        Bitmap size = newRecipeView.getRecipeImage();
        size = Bitmap.createScaledBitmap(size, recipeSection.getWidth() / 2, 300, false);
        newImage.setPadding(25,5,0,5);
        newImage.setLayoutParams(lParamsImage);
        newImage.setImageBitmap(size);
        newSection.addView(newImage);

        recipeIdentify = recipeIdentify + 1;

        LinearLayout.LayoutParams lParamsText = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        lParamsText.setMargins(0,10,0,10);
        TextView newTxtView = new TextView(this);
        newTxtView.setLayoutParams(lParamsText);
        newTxtView.setEms(10);
        newTxtView.setTextColor(getResources().getColor(R.color.black));
        newTxtView.setEllipsize(TextUtils.TruncateAt.START);
        newTxtView.setGravity(Gravity.CENTER);
        newTxtView.setBackgroundResource(R.drawable.edit_text_background);
        newTxtView.setPadding(0,0,0,0);
        newTxtView.setTextSize(18);
        newTxtView.setText(newRecipeView.getName());

        newSection.addView(newTxtView);

    }
}


