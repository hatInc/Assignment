package com.example.harry.mainmenu;

import java.io.Serializable;

/**
 * Created by Harry on 02/03/2018.
 */

public class task implements Serializable{

    private String taskName = null;
    private String instructions = null;

    public String getTaskName(){
        return taskName;
    }

    public String getInstructions(){
        return instructions;
    }

    public void setTaskName(String name){
        taskName = name;
    }

    public void setInstructions(String newInstructions){
        instructions = newInstructions;
    }

}
