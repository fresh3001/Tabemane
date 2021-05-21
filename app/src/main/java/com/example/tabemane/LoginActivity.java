package com.example.tabemane;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;


public class LoginActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText usernameEditText = findViewById(R.id.username);
        EditText passwordEditText = findViewById(R.id.password);
        Button loginButton = findViewById(R.id.login);
        ProgressBar loadingProgressBar = findViewById(R.id.loading);

        loginButton.setOnClickListener(new loginListner());

    }

    private class loginListner implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplication(), MainActivity.class);
            startActivity(intent);
        }
    }
}