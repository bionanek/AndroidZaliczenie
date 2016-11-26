package com.example.artur.timeger;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.database.DatabaseUtilsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;

public class RegisterActivity extends AppCompatActivity {

    private SQLiteDatabase db;
    private EditText loginText;
    private EditText passText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        loginText = (EditText) findViewById(R.id.login);
        passText = (EditText) findViewById(R.id.pass);
    }

    private void getLogin()
    {
        return;
    }

    public void onBackButtonClick(View view)
    {

        Intent mainActivity = new Intent(this,LoginActivity.class);
        startActivity(mainActivity);
    }
    public void onCreateButtonClick(View view)
    {
    }

    public void onCheckButtonClick(View view)
    {
        File database = getApplicationContext().getDatabasePath("Timeger.db");
        if(database.exists())
        {
            Toast.makeText(this, "tru", Toast.LENGTH_SHORT).show();
            Log.e("Check", "istnieje chyba");
        }
        else
            Toast.makeText(this, "fals", Toast.LENGTH_SHORT).show();

    }


    public void onDeleteButtonClick(View view)
    {
        Toast.makeText(this, "deleted", Toast.LENGTH_SHORT).show();
    }


    public void onRegisterButtonClick(View view)
    {
        String login = loginText.getText().toString();
        String pass = passText.getText().toString();

        loginText.setText("");
        passText.setText("");
        Toast.makeText(this, "User has been added", Toast.LENGTH_SHORT).show();
    }

    public void onDownloadClick(View view)
    {
    }
}


