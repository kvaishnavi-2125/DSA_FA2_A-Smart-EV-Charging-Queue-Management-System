package com.example.dsa;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class QueueStatusActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private TableLayout tableQueueStatus;
    private Button btnClearUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queue_status);

        tableQueueStatus = findViewById(R.id.tableQueueStatus);
        btnClearUsers = findViewById(R.id.btnClearUsers);

        dbHelper = new DatabaseHelper(this);

        displayQueueStatus();
    }

    private void displayQueueStatus() {
        Cursor cursor = dbHelper.getQueue();
        // Remove all existing rows in the TableLayout
        tableQueueStatus.removeAllViews();

        // Add the table headers
        TableRow headerRow = new TableRow(this);
        headerRow.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));

        TextView vehicleHeader = new TextView(this);
        vehicleHeader.setText("Vehicle No.");
        vehicleHeader.setTypeface(null, android.graphics.Typeface.BOLD);  // Apply bold style
        vehicleHeader.setPadding(16, 16, 16, 16); // Add padding to make the text stand out
        vehicleHeader.setTextColor(getResources().getColor(android.R.color.black));
       // vehicleHeader.setBackgroundResource(android.R.drawable.dialog_holo_light_frame);
        headerRow.addView(vehicleHeader);

        TextView batteryHeader = new TextView(this);
        batteryHeader.setText("Battery (%)");
        batteryHeader.setTypeface(null, android.graphics.Typeface.BOLD);  // Apply bold style
        batteryHeader.setPadding(16, 16, 16, 16); // Add padding
        batteryHeader.setTextColor(getResources().getColor(android.R.color.black));
       // batteryHeader.setBackgroundResource(android.R.drawable.dialog_holo_light_frame);
        headerRow.addView(batteryHeader);

        TextView priorityHeader = new TextView(this);
        priorityHeader.setText("Priority");
        priorityHeader.setTypeface(null, android.graphics.Typeface.BOLD);  // Apply bold style
        priorityHeader.setPadding(16, 16, 16, 16); // Add padding
        priorityHeader.setTextColor(getResources().getColor(android.R.color.black));
       // priorityHeader.setBackgroundResource(android.R.drawable.dialog_holo_light_frame);
        headerRow.addView(priorityHeader);

        tableQueueStatus.addView(headerRow);

        // Add rows for each vehicle in the queue
        if (cursor != null && cursor.moveToFirst()) {
            do {
                String vehicleNumber = cursor.getString(cursor.getColumnIndexOrThrow("vehicle_number"));
                int batteryLevel = cursor.getInt(cursor.getColumnIndexOrThrow("battery_level"));
                int priority = cursor.getInt(cursor.getColumnIndexOrThrow("priority"));

                TableRow row = new TableRow(this);
                row.setBackgroundColor(getResources().getColor(android.R.color.white));

                TextView vehicleText = new TextView(this);
                vehicleText.setText(vehicleNumber);
                vehicleText.setPadding(16, 16, 16, 16);
                row.addView(vehicleText);

                TextView batteryText = new TextView(this);
                batteryText.setText(String.valueOf(batteryLevel) + "%");
                batteryText.setPadding(16, 16, 16, 16);
                row.addView(batteryText);

                TextView priorityText = new TextView(this);
                priorityText.setText(priority == 1 ? "High" : "Normal");
                priorityText.setPadding(16, 16, 16, 16);
                row.addView(priorityText);

                tableQueueStatus.addView(row);
            } while (cursor.moveToNext());
        } else {
            // If no data, display a message in a single row
            TableRow emptyRow = new TableRow(this);
            TextView emptyText = new TextView(this);
            emptyText.setText("No users in queue.");
            emptyText.setPadding(16, 16, 16, 16);
            emptyRow.addView(emptyText);
            tableQueueStatus.addView(emptyRow);
        }

        if (cursor != null) {
            cursor.close();
        }

        btnClearUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearUserList();
            }
        });
    }

    private void clearUserList() {
        dbHelper.clearAllUsers();
        tableQueueStatus.removeAllViews(); // Remove all rows after clearing the list
        Toast.makeText(this, "User list cleared", Toast.LENGTH_SHORT).show();
    }
}
