package com.example.dsa;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ChargingRequestActivity extends AppCompatActivity {

    private EditText vehicleNumberEditText, batteryLevelEditText;
    private Button requestChargingButton;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charging_request);

        vehicleNumberEditText = findViewById(R.id.vehicleNumberEditText);
        batteryLevelEditText = findViewById(R.id.batteryLevelEditText);
        requestChargingButton = findViewById(R.id.requestChargingButton);
        dbHelper = new DatabaseHelper(this);

        requestChargingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addVehicleToQueue();
            }
        });
    }

    private void addVehicleToQueue() {
        String vehicleNumber = vehicleNumberEditText.getText().toString().trim();
        int batteryLevel;

        try {
            batteryLevel = Integer.parseInt(batteryLevelEditText.getText().toString().trim());
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Enter a valid battery level", Toast.LENGTH_SHORT).show();
            return;
        }

        if (batteryLevel < 0 || batteryLevel > 100) {
            Toast.makeText(this, "Battery level must be between 0 and 100", Toast.LENGTH_SHORT).show();
            return;
        }

        dbHelper.addVehicleToQueue(vehicleNumber, batteryLevel);
        Toast.makeText(this, "Charging request submitted with priority", Toast.LENGTH_SHORT).show();
    }
}