package com.example.dsa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AdminLogin extends AppCompatActivity {

    private EditText etAdminUsername, etAdminPassword;
    private Button btnAdminLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        etAdminUsername = findViewById(R.id.etName);
        etAdminPassword = findViewById(R.id.etPassword);
        btnAdminLogin = findViewById(R.id.btnLogin);

        btnAdminLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = etAdminUsername.getText().toString().trim();
                String password = etAdminPassword.getText().toString().trim();

                // Simple check for demonstration purposes
                if (username.equals("Vaishnavi") && password.equals("vaishu@02")) {
                    Intent intent = new Intent(AdminLogin.this, AdminView.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(AdminLogin.this, "Invalid Admin Credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
