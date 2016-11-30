package com.example.artur.timeger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    private TextView forgetPassword;
    private Button passwordReset,loginButton,registerButton;
    private boolean openPasswordReminder = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        forgetPassword = (TextView)findViewById(R.id.forgetPassword);
        passwordReset = (Button)findViewById(R.id.passwordReset);
        loginButton = (Button) findViewById(R.id.logLoginClick);
        registerButton = (Button) findViewById(R.id.logRegisterClick);

        //Password reminder operation
        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!openPasswordReminder)
                {
                    passwordReset.setVisibility(View.VISIBLE);
                    openPasswordReminder = true;
                }
                else
                {
                    passwordReset.setVisibility(View.INVISIBLE);
                    openPasswordReminder = false;
                }
            }
        });

        //login operation
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent succedLogin = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(succedLogin);
            }
        });

        //go register operation
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goRegister = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(goRegister);
            }
        });
    }

}
