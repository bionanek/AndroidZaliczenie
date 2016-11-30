package com.example.artur.timeger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    private TextView forgetPassword;
    private Button passwordReset;
    private boolean openPasswordReminder = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        forgetPassword = (TextView)findViewById(R.id.forgetPassword);
        passwordReset = (Button)findViewById(R.id.passwordReset);

        //Password reminder opertaion
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
    }

}
