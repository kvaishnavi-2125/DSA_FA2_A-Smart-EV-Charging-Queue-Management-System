package com.example.dsa;

import static com.example.dsa.DatabaseHelper.TABLE_REGISTER;
import static com.example.dsa.DatabaseHelper.TABLE_USERS;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UserLogin extends AppCompatActivity {

    private EditText etVehicleId, etPassword;
    private Button btnLogin;
    private TextView tvRegister;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        etVehicleId = findViewById(R.id.etVehicleId);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvRegister = findViewById(R.id.tvRegister);

        dbHelper = new DatabaseHelper(this);

        // Login button action
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String vehicleId = etVehicleId.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                if (vehicleId.isEmpty() || password.isEmpty()) {
                    Toast.makeText(UserLogin.this, "Please enter Vehicle ID and Password", Toast.LENGTH_SHORT).show();
                } else {
                    // Perform login logic here
                    if (authenticateUser(vehicleId, password)) {
                        Toast.makeText(UserLogin.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        // Redirect to home page or dashboard
                        Intent intent = new Intent(UserLogin.this, HomeActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(UserLogin.this, "User Not Available or Incorrect Password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        // Register link action
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to registration activity
                Intent intent = new Intent(UserLogin.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });
    }

    public boolean authenticateUser(String vehicleId, String password) {
        // Get a readable database instance
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Query to check if the vehicle ID and password match
        String query = "SELECT * FROM " + DatabaseHelper.TABLE_REGISTER + " WHERE " +
                DatabaseHelper.COLUMN_VEHICLE_NO + " = ? AND " +
                DatabaseHelper.COLUMN_PASSWORD + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{vehicleId, password});

        boolean isAuthenticated = cursor != null && cursor.getCount() > 0;

        if (cursor != null) {
            cursor.close();
        }
        db.close();
        return isAuthenticated;
    }
}
