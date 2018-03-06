package com.example.harry.mainmenu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
public class welcome extends AppCompatActivity {

    private
    Animation myAnimDown;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startLayout1();
    }

    private void startLayout1() {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_welcome);

        ImageView logoView = (ImageView) findViewById(R.id.logoImage);
        logoView.setImageResource(R.drawable.logo);

        myAnimDown = AnimationUtils.loadAnimation(this,R.anim.transition_down);

        logoView.setAnimation(myAnimDown);
    };

    public void toMain(View view){
        Intent intent = new Intent();
        intent.setClass(welcome.this, login.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        finish();
    };
}