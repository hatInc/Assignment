package com.example.harry.mainmenu;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {

    private
    Button signInButton;
    EditText textName;
    EditText textPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        signInButton = (Button) findViewById(R.id.login);
        signInButton.setOnClickListener(okOnClickListener);
    }

    /** Called when the user taps new user*/

    public void newUser(View view){
        Intent intent = new Intent(this, register.class);
        startActivity(intent);
        finish();
    }

    private Button.OnClickListener okOnClickListener = new Button.OnClickListener(){
        @Override
        public void onClick(View v) {
            textName = (EditText) findViewById(R.id.email);
            CharSequence textName_value = textName.getText();

            textPassword = (EditText) findViewById(R.id.confirmPass);
            CharSequence textPassword_value = textPassword.getText();

            if(textName_value.length() < 1 || textPassword_value.length() < 1){
                Context context = getApplicationContext();
                CharSequence text = "Invalid!";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
            else {
                Intent intent = new Intent();
                intent.setClass(login.this, menu.class);

                startActivity(intent);
                finish();
            }
        }
    };
}
