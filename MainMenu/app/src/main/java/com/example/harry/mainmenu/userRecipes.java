package com.example.harry.mainmenu;

import android.graphics.Bitmap;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Harry on 02/03/2018.
 */

public class userRecipes {

    private Bitmap recipeImage = null;
    private String name = null;
    private ArrayList<task> recipeTasks = null;
    private ArrayList<ingredient> ingredients = null;

    public void setRecipeImage(Bitmap img){
        recipeImage = img;
    }

    public void setName(String _name){
        name = _name;
    }

    public Bitmap getRecipeImage(){
        return recipeImage;
    }

    public String getName(){
        return name;
    }

    public ArrayList<task> getRecipeTasks(){
        return recipeTasks;
    }

    public ArrayList<ingredient> getIngredients(){
        return ingredients;
    }

    public void setRecipeTasks(ArrayList<task> newTasks){
        recipeTasks = newTasks;
    }

    public void setIngredients(ArrayList<ingredient> newIngredients){
        ingredients = newIngredients;
    }


}
