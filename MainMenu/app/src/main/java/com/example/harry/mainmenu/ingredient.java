package com.example.harry.mainmenu;

import java.io.Serializable;

/**
 * Created by Harry on 04/03/2018.
 */

public class ingredient implements Serializable{
    private String ingredientName = null;

    public String getIngredientName(){return ingredientName;}

    public void setIngredientName(String newName){
        ingredientName = newName;
    }
}
