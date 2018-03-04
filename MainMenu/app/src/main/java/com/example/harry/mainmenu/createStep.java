package com.example.harry.mainmenu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.harry.mainmenu.R;

/**
 * Created by Harry on 28/02/2018.
 */

public class createStep extends Activity implements View.OnClickListener{

    private EditText name;
    private EditText info;
    private Button sub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.stepwindow);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width* .8),(int) (height * .8));

        LinearLayout layout = findViewById(R.id.mainLayout);
            // Gets the layout params that will allow you to resize the layout
        ViewGroup.LayoutParams params = layout.getLayoutParams();
            // Changes the height and width to the specified *pixels*
        params.height = (int)(height * .8);
        params.width = (int)(width * .8);
        layout.setLayoutParams(params);

        name = (EditText) findViewById(R.id.stepName);
        info = (EditText) findViewById(R.id.stepInfo);
        sub = (Button) findViewById(R.id.submit);

        sub.setOnClickListener(this);

        if(getIntent().hasExtra("taskName")){
            name.setText(getIntent().getStringExtra("taskName"));
            info.setText(getIntent().getStringExtra("task"));
        }
    }

    public void onClick(View v){
        switch(v.getId()){
            case R.id.submit:
                Intent i = new Intent();
                i.putExtra("name", name.getText().toString());
                i.putExtra("info", info.getText().toString());
                setResult(RESULT_OK,i);
                finish();
                break;
        }
    }

}
