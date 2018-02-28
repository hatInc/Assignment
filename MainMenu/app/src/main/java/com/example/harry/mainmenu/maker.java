package com.example.harry.mainmenu;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.content.Intent;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.Closeable;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static android.provider.Settings.System.DATE_FORMAT;

public class maker extends AppCompatActivity implements View.OnClickListener {

    private static int RESULT_LOAD_IMAGE = 1;
    private ImageView imageView;
    private ImageView ingredient;
    private ImageView tasks;
    private LinearLayout newIngredientLayout;
    private LinearLayout newTaskLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maker);

        imageView = (ImageView) findViewById(R.id.imgView);
        ingredient = (ImageView) findViewById(R.id.addIngredient);
        tasks = (ImageView) findViewById(R.id.addTask);
        newIngredientLayout = (LinearLayout) findViewById(R.id.newIngredients);
        newTaskLayout = (LinearLayout) findViewById(R.id.newTasks);

        imageView.setOnClickListener(this);
        ingredient.setOnClickListener(this);
        tasks.setOnClickListener(this);

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
                newIngredientLayout.addView(createTxtBox("Enter An Ingredient"));
                break;
            case R.id.addTask:
                newTaskLayout.addView(createTxtBox("Enter An Instruction"));
                break;
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == Activity.RESULT_OK){
        try {
            Uri imgUri = data.getData();

            Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imgUri);

            bitmap = Bitmap.createScaledBitmap(bitmap, imageView.getWidth(), imageView.getHeight(), false);
            imageView.setImageBitmap(bitmap);
        }
        catch(IOException e){
            //add code for the exception
        }
}


    }

    private EditText createTxtBox(String hint){
        LinearLayout.LayoutParams lParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lParams.setMargins(0,10,0,0);
        EditText newTxtBox = new EditText(this);
        newTxtBox.setHint(hint);
        newTxtBox.setGravity(Gravity.CENTER);
        newTxtBox.setSingleLine(true);
        newTxtBox.setEms(10);
        newTxtBox.setEllipsize(TextUtils.TruncateAt.START);
        newTxtBox.setLayoutParams(lParams);
        newTxtBox.setBackgroundResource(R.drawable.edit_text_background);
        newTxtBox.setPadding(0,0,0,0);
        return newTxtBox;
    }

}