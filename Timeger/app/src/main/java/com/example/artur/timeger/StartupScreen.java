package com.example.artur.timeger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class StartupScreen extends AppCompatActivity {

    private Button login,register;
    private ImageView turnOff,aboutApplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup_screen);

        login = (Button)findViewById(R.id.onLoginClick);
        register = (Button)findViewById(R.id.onRegisterClick);
        turnOff = (ImageView)findViewById(R.id.onTurnOffClick);
        aboutApplication = (ImageView)findViewById(R.id.aboutApplicationClick);


    }


    public void Login(View view) {
        Intent intent = new Intent(StartupScreen.this,LoginActivity.class);
        startActivity(intent);
    }
}
