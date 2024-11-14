package com.example.dsa;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;

public class RegistrationActivity extends AppCompatActivity {

    private EditText etName, etMobile, etAddress, etVehicleName, etVehicleNo, etPassword;
    private Button btnRegister;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etName = findViewById(R.id.etName);
        etMobile = findViewById(R.id.etMobile);
        etAddress = findViewById(R.id.etAddress);
        etVehicleName = findViewById(R.id.etVehicleName);
        etVehicleNo = findViewById(R.id.etVehicleNo);
        etPassword = findViewById(R.id.etPassword);
        btnRegister = findViewById(R.id.btnRegister);

        // Initialize DatabaseHelper
        dbHelper = new DatabaseHelper(this);

        // Register button action
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString().trim();
                String mobile = etMobile.getText().toString().trim();
                String address = etAddress.getText().toString().trim();
                String vehicleName = etVehicleName.getText().toString().trim();
                String vehicleNo = etVehicleNo.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                if (name.isEmpty() || mobile.isEmpty() || address.isEmpty() || vehicleName.isEmpty() || vehicleNo.isEmpty() || password.isEmpty()) {
                    Toast.makeText(RegistrationActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else {
                    // Save the new user into the database
                    saveUserToDatabase(name, mobile, address, vehicleName, vehicleNo, password);

                    Toast.makeText(RegistrationActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();

                    // Pass data to HomeActivity
                    Intent homeIntent = new Intent(RegistrationActivity.this, HomeActivity.class);
                    homeIntent.putExtra("userName", name);
                    homeIntent.putExtra("vehicleName", vehicleName);
                    startActivity(homeIntent);
                    finish(); // Close this activity and return to the login page
                }
            }
        });
    }

    private void saveUserToDatabase(String name, String mobile, String address, String vehicleName, String vehicleNo, String password) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_MOBILE, mobile);
        values.put(DatabaseHelper.COLUMN_ADDRESS, address);
        values.put(DatabaseHelper.COLUMN_VEHICLE_NAME, vehicleName);
        values.put(DatabaseHelper.COLUMN_VEHICLE_NO, vehicleNo);
        values.put(DatabaseHelper.COLUMN_PASSWORD, password);
        values.put(DatabaseHelper.COLUMN_USER_NAME, name);

        db.insert(DatabaseHelper.TABLE_REGISTER, null, values);
        db.close();
    }
}
