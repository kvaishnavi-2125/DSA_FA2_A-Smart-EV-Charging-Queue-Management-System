/*package com.example.dsa;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class UserScreen extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    private TextView userDetailsTextView;
    private TextView vehicleDetailsTextView;
    private int userId; // Assume userId is set after login and passed to this screen

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userscreen);

        databaseHelper = new DatabaseHelper(this);
        userDetailsTextView = findViewById(R.id.userDetails);
        vehicleDetailsTextView = findViewById(R.id.vehicleDetails);

        // ImageViews for the icons
        ImageView checkAvailabilityIcon = findViewById(R.id.check_availability_icon);
        ImageView homeIcon = findViewById(R.id.home_icon);
        ImageView sendRequestIcon = findViewById(R.id.send_request_icon);

        // Display details
        displayUserDetails();
        displayVehicleDetails();

        // Set OnClickListener for icons
        checkAvailabilityIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleCheckAvailability();
            }
        });

        homeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateHome();
            }
        });

        sendRequestIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleSendRequest();
            }
        });
    }

    // Method to display user details
    private void displayUserDetails() {
        List<String> userDetails = databaseHelper.getUserDetails();
        StringBuilder detailsText = new StringBuilder("User Details:\n");
        for (String detail : userDetails) {
            detailsText.append(detail).append("\n");
        }
        userDetailsTextView.setText(detailsText.toString());
    }

    // Method to display vehicle details
    private void displayVehicleDetails() {
        List<String> vehicleDetails = databaseHelper.getVehicleDetails();
        StringBuilder detailsText = new StringBuilder("Vehicle Details:\n");
        for (String detail : vehicleDetails) {
            detailsText.append(detail).append("\n");
        }
        vehicleDetailsTextView.setText(detailsText.toString());
    }

    // Method to handle sending a charging request
    private void handleSendRequest() {
        boolean isSlotAvailable = databaseHelper.isSlotAvailable();
        if (isSlotAvailable) {
            boolean requestSent = databaseHelper.sendChargingRequest(userId);
            if (requestSent) {
                Toast.makeText(this, "Request sent successfully", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Failed to send request", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "No slots available. Please wait.", Toast.LENGTH_SHORT).show();
        }
    }

    // Method to handle check availability action (you can expand as needed)
    private void handleCheckAvailability() {
        // You can implement the functionality to check the availability of slots here
        Toast.makeText(this, "Checking slot availability...", Toast.LENGTH_SHORT).show();
    }

    // Method to navigate to the home screen
    private void navigateHome() {
        // Implement navigation to home screen (e.g., using Intent)
        Toast.makeText(this, "Navigating to home...", Toast.LENGTH_SHORT).show();
    }
}
*/