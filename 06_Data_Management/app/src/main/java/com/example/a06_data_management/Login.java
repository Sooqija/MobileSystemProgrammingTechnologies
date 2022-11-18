package com.example.a06_data_management;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

public class Login extends AppCompatActivity {
    boolean checked;
    String login;
    String password;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actvity_login);
        CheckBox checkBox = findViewById(R.id.checkBox);
        login = PreferenceManager.getDefaultSharedPreferences(this).getString("Login", "");
        password = PreferenceManager.getDefaultSharedPreferences(this).getString("Password", "");
        EditText loginView = findViewById(R.id.Login);
        EditText passwordView = findViewById(R.id.Password);
        loginView.setText(login);
        passwordView.setText(password);
        checked = PreferenceManager.getDefaultSharedPreferences(this).getBoolean("RememberMe", false);
        checkBox.setChecked(checked);
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checked = checkBox.isChecked();
                login = String.valueOf(loginView.getText());
                password = String.valueOf(passwordView.getText());
                PreferenceManager.getDefaultSharedPreferences(Login.this).edit().putBoolean("RememberMe", checked).apply();
                if (checkBox.isChecked()) {
                    PreferenceManager.getDefaultSharedPreferences(Login.this).edit().putString("Login", login).apply();
                    PreferenceManager.getDefaultSharedPreferences(Login.this).edit().putString("Password", password).apply();
                } else {
                    PreferenceManager.getDefaultSharedPreferences(Login.this).edit().putString("Login", "").apply();
                    PreferenceManager.getDefaultSharedPreferences(Login.this).edit().putString("Password", "").apply();
                }
            }
        });
        View loginLayout = findViewById(R.id.LoginLayout);
        loginLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (checked) {
                    login = String.valueOf(loginView.getText());
                    password = String.valueOf(passwordView.getText());
                    PreferenceManager.getDefaultSharedPreferences(Login.this).edit().putString("Login", login).apply();
                    PreferenceManager.getDefaultSharedPreferences(Login.this).edit().putString("Password", password).apply();
                } else {
                    PreferenceManager.getDefaultSharedPreferences(Login.this).edit().putString("Login", "").apply();
                    PreferenceManager.getDefaultSharedPreferences(Login.this).edit().putString("Password", "").apply();
                }
            }
        });

        Button bPrevious = findViewById(R.id.PreviousPage);
        bPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, CheckBoxState.class);
                startActivity(intent);
            }
        });
        Button bNext = findViewById(R.id.NextPage);
        bNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, SquaresList.class);
                startActivity(intent);
            }
        });
    }
}
