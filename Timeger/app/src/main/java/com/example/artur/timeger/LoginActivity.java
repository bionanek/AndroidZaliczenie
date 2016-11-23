package com.example.artur.timeger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void onLogin(View v1)
    {
        Intent mainActivity = new Intent(this,MainActivity.class);
        startActivity(mainActivity);
    }

    public void onQuitButton(View v2)
    {
        System.exit(0);
    }

    public void onRegisterClick(View view) {
        Intent mainActivity = new Intent(this,RegisterActivity.class);
        startActivity(mainActivity);
    }
}
