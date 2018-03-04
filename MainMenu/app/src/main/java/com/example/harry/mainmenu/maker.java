package com.example.harry.mainmenu;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class maker extends AppCompatActivity implements View.OnClickListener {

    private static final int RESULT_LOAD_IMAGE = 1;
    private static final int STEP_RESULT_POP_UP = 2;
    private static final int STEP_RESULT_POP_UP_EDIT = 3;
    private static final int INGREDIENT_RESULT_POP_UP = 4;
    private static final int INGREDIENT_RESULT_POP_UP_EDIT = 5;
    private ImageView imageView;

    private LinearLayout newIngredientLayout;
    private LinearLayout newTaskLayout;
    private Button newStep;
    private Button newIndredient;
    private ArrayList<task> steps = new ArrayList<task>();
    private ArrayList<ingredient> ingredients = new ArrayList<ingredient>();
    private int taskIdentifier = 0;
    private int editViewNumber;
    private task taskToEdit;
    private ingredient ingredientToEdit;
    private Button create;
    private EditText recipeName;
    private Bitmap image = null;
    private ByteArrayOutputStream bytes = new ByteArrayOutputStream();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maker);

        imageView = (ImageView) findViewById(R.id.imgView);

        newStep = (Button) findViewById(R.id.addStep);
        newIndredient = (Button) findViewById(R.id.addIngredient);
        create = (Button) findViewById(R.id.createRecipe);
        recipeName = (EditText) findViewById(R.id.nameRecipe);

        newIngredientLayout = (LinearLayout) findViewById(R.id.newIngredients);
        newTaskLayout = (LinearLayout) findViewById(R.id.newTasks);

        newIndredient.setOnClickListener(this);
        imageView.setOnClickListener(this);
        newStep.setOnClickListener(this);
        create.setOnClickListener(this);
    }

    public void backMain(View view) {
        finish();
    }

    public void viewSettings(View view) {
        Intent intent = new Intent(this, mainSettings.class);
        startActivity(intent);
    }

    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.imgView:
                Intent intentGalley = new Intent(Intent.ACTION_PICK);
                intentGalley.setType("image/*");
                startActivityForResult(intentGalley, RESULT_LOAD_IMAGE);
                break;
            case R.id.addIngredient:
                startActivityForResult(new Intent(this, ingredientWindow.class), INGREDIENT_RESULT_POP_UP);
                break;
            case R.id.addStep:
                startActivityForResult(new Intent(this, createStep.class), STEP_RESULT_POP_UP);
                break;
            case R.id.createRecipe:
                Intent i = new Intent();
                i.putExtra("recipeName", recipeName.getText().toString());
                i.putExtra("recipeTasks", steps);
                i.putExtra("recipeIngredients", ingredients);

                String fileName = "myImage";//no .png or .jpg needed
                try {
                    image.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
                    FileOutputStream fo = openFileOutput(fileName, Context.MODE_PRIVATE);
                    fo.write(bytes.toByteArray());
                    // remember close file output
                    fo.close();
                } catch (Exception e) {
                    e.printStackTrace();
                    fileName = null;
                }

                i.putExtra("recipeImage", fileName);
                setResult(RESULT_OK, i);
                finish();
                break;
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case RESULT_LOAD_IMAGE:
                if(resultCode == RESULT_OK){
                    try {
                        Uri imgUri = data.getData();

                        image = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imgUri);
                        image.compress(Bitmap.CompressFormat.JPEG, 50, bytes);

                        image = Bitmap.createScaledBitmap(image, imageView.getWidth(), imageView.getHeight(), false);
                        imageView.setImageBitmap(image);
                    }
                    catch(IOException e){
                        //add code for the exception
                    }
                }

                break;
            case STEP_RESULT_POP_UP:
                if(resultCode == RESULT_OK){
                    newTaskLayout.addView(createViewBox(data.getStringExtra("name"), 1));
                    task tempTask = new task();

                    tempTask.setTaskName(data.getStringExtra("name"));
                    tempTask.setInstructions(data.getStringExtra("info"));

                    steps.add(tempTask);

                }
                break;
            case STEP_RESULT_POP_UP_EDIT:
                if(resultCode == RESULT_OK) {
                    taskToEdit.setInstructions(data.getStringExtra("info"));
                    taskToEdit.setTaskName(data.getStringExtra("name"));

                    TextView txtDisplay = findViewById(editViewNumber);

                    txtDisplay.setText(data.getStringExtra("name"));
                }
                break;
            case INGREDIENT_RESULT_POP_UP:
                if(resultCode == RESULT_OK){
                    newIngredientLayout.addView(createViewBox(data.getStringExtra("name"), 0));

                    ingredient tempIngredient = new ingredient();

                    tempIngredient.setIngredientName(data.getStringExtra("name"));

                    ingredients.add(tempIngredient);
                }
                break;
            case INGREDIENT_RESULT_POP_UP_EDIT:
                if(resultCode == RESULT_OK){
                    ingredientToEdit.setIngredientName(data.getStringExtra("name"));

                    TextView txtDisplay = findViewById(editViewNumber);

                    txtDisplay.setText(data.getStringExtra("name"));
                }
                break;
        }


    }

    private TextView createViewBox(String name, int TOrI){
        LinearLayout.LayoutParams lParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lParams.setMargins(0,10,0,10);
        TextView newTxtView = new TextView(this);
        newTxtView.setLayoutParams(lParams);
        newTxtView.setEms(10);
        newTxtView.setTextColor(getResources().getColor(R.color.black));
        newTxtView.setEllipsize(TextUtils.TruncateAt.START);
        newTxtView.setGravity(Gravity.CENTER);
        newTxtView.setBackgroundResource(R.drawable.edit_text_background);
        newTxtView.setPadding(0,0,0,0);
        newTxtView.setTextSize(18);
        newTxtView.setText(name);
        newTxtView.setId(taskIdentifier);
        taskIdentifier = taskIdentifier + 1;

        if(TOrI == 1){
            newTxtView.setOnClickListener(taskListener);
        }
        else{
            newTxtView.setOnClickListener(ingredientListner);
        }

        return newTxtView;
    }

    View.OnClickListener taskListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            editViewNumber = view.getId();


            taskToEdit = steps.get(editViewNumber);

            Intent i = new Intent(view.getContext(), createStep.class);

            i.putExtra("taskName", taskToEdit.getTaskName());
            i.putExtra("task", taskToEdit.getInstructions());
            startActivityForResult(i, STEP_RESULT_POP_UP_EDIT);
        }
    };

    View.OnClickListener ingredientListner = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            editViewNumber = view.getId();


            ingredientToEdit = ingredients.get(editViewNumber);

            Intent i = new Intent(view.getContext(), ingredientWindow.class);

            i.putExtra("ingredientName", ingredientToEdit.getIngredientName());
            startActivityForResult(i, INGREDIENT_RESULT_POP_UP_EDIT);
        }
    };


}