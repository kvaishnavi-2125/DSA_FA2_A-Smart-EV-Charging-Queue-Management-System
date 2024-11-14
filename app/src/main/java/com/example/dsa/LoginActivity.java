package com.example.dsa;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private Button btnUserLogin, btnAdminLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize buttons
        btnUserLogin = findViewById(R.id.btnUserLogin);
        btnAdminLogin = findViewById(R.id.btnAdminLogin);

        // Set click listener for User Login button
        btnUserLogin.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, UserLogin.class);
            startActivity(intent);
        });

        // Set click listener for Admin Login button
        btnAdminLogin.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, AdminLogin.class);
            startActivity(intent);
        });
    }
}
