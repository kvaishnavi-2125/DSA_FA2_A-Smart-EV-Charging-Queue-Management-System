package com.example.dsa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;

public class HomeActivity extends AppCompatActivity {
    private Button checkSlotAvailabilityButton, sendChargingRequestButton, logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        // Reference to UI elements
        checkSlotAvailabilityButton = findViewById(R.id.checkSlotAvailabilityButton);
        sendChargingRequestButton = findViewById(R.id.sendChargingRequestButton);
        logoutButton = findViewById(R.id.logoutButton);

        // Null check for userName and vehicleName


        // Set up button click listeners with intents
        checkSlotAvailabilityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent checkSlotIntent = new Intent(HomeActivity.this, SlotAvailabilityActivity.class);
                startActivity(checkSlotIntent);
            }
        });

        sendChargingRequestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendRequestIntent = new Intent(HomeActivity.this, ChargingRequestActivity.class);
                startActivity(sendRequestIntent);
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Return to Login Activity and clear user session data if needed
                Intent logoutIntent = new Intent(HomeActivity.this, LoginActivity.class);
                logoutIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(logoutIntent);
                finish(); // Close HomeActivity
            }
        });
    }
}
