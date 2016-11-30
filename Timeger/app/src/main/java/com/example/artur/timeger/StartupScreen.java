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

        //login operation (by clicking Login button)
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goLogin = new Intent (StartupScreen.this,LoginActivity.class);
                startActivity(goLogin);
            }
        });

        //register operation (by clicking Register button
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goRegister = new Intent(StartupScreen.this,RegisterActivity.class);
                startActivity(goRegister);
            }
        });

        //system exit (by touching TurnOff icon)
        turnOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.exit(0);
            }
        });

        //show info about application (by touching Info icon)
        aboutApplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToAboutApplication = new Intent(StartupScreen.this,AboutApplication.class);
                startActivity(goToAboutApplication);
            }
        });
    }



}
