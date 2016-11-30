package com.example.artur.timeger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    private EditText loginText,passwordText;
    private Button loginButton,registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        loginText = (EditText) findViewById(R.id.login);
        passwordText = (EditText) findViewById(R.id.pass);
        loginButton = (Button) findViewById(R.id.regLoginClick);
        registerButton = (Button) findViewById(R.id.regRegisterClick);

        //go to login activity
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goLogin = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(goLogin);
            }
        });

        //register user operation
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (loginText.getText().length() != 0 && passwordText.getText().length() != 0)
                {
                    Intent succedRegister = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(succedRegister);
                }
                else if (loginText.getText().length() == 0 || passwordText.getText().length() == 0)
                {
                   if (loginText.getText().length() == 0)
                   {
                       Toast.makeText(RegisterActivity.this, "Nie wprowadzono loginu", Toast.LENGTH_SHORT).show();
                   }
                    if (passwordText.getText().length() == 0)
                    {
                        Toast.makeText(RegisterActivity.this, "Nie wprowadzono hasła", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

}


