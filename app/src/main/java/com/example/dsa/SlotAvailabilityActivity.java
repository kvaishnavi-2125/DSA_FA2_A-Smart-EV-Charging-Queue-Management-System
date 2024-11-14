package com.example.dsa;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SlotAvailabilityActivity extends AppCompatActivity {

    private TextView slotStatusText;
    private TextView slot1, slot2, slot3;

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slot_availability);

        // Initialize Views
        slotStatusText = findViewById(R.id.slotStatusText);
        slot1 = findViewById(R.id.slot1);
        slot2 = findViewById(R.id.slot2);
        slot3 = findViewById(R.id.slot3);

        // Setting up the Slot Availability
        //setSlotAvailability();

    }

  /*  public void setSlotAvailability() {
        // Query the database to get the current status of all slots
        Cursor cursor = db.rawQuery("SELECT slot_id, status FROM slots", null);
        if (cursor.moveToFirst()) {
            do {
                int slotId = cursor.getInt(0);
                String status = cursor.getString(1);

                // Update the UI based on the slot status
                if (status.equals("Available")) {
                    // Set slot color to green (Available)
                    updateSlotUI(slotId, "Available", R.color.green);
                } else {
                    // Set slot color to red (Occupied)
                    updateSlotUI(slotId, "Occupied", R.color.red);
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
    }

    private void updateSlotUI(int slotId, String status, int color) {
        // Update the UI for each slot based on its ID and status
        // Find the appropriate slot in the UI and change its background color accordingly
        switch (slotId) {
            case 1:
                slot1.setBackgroundColor(getResources().getColor(color));
                TextView slot1Text = (TextView) slot1.getChildAt(0);
                slot1Text.setText("Slot 1: " + status);
                break;
            case 2:
                slot2.setBackgroundColor(getResources().getColor(color));
                TextView slot2Text = (TextView) slot2.getChildAt(0);
                slot2Text.setText("Slot 2: " + status);
                break;
            case 3:
                slot3.setBackgroundColor(getResources().getColor(color));
                TextView slot3Text = (TextView) slot3.getChildAt(0);
                slot3Text.setText("Slot 3: " + status);
                break;
        }
    }*/
}