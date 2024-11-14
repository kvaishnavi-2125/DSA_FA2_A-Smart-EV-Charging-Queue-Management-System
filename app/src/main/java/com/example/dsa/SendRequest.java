package com.example.dsa;

/*import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class SendRequest extends AppCompatActivity {

    private EditText etVehicleId, etBatteryLevel;
    private Button btnAddRequest;
    private ListView chargingQueueListView;
    private PriorityQueue<ChargingRequest> chargingQueue;
    private ArrayAdapter<String> queueAdapter;
    private ArrayList<String> queueList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charging_request);

        etVehicleId = findViewById(R.id.etVehicleId);
        etBatteryLevel = findViewById(R.id.etBatteryLevel);
        btnAddRequest = findViewById(R.id.btnAddRequest);
        chargingQueueListView = findViewById(R.id.chargingQueueListView);

        chargingQueue = new PriorityQueue<>();
        queueList = new ArrayList<>();
        queueAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, queueList);
        chargingQueueListView.setAdapter(queueAdapter);

        // Add request on button click
        btnAddRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addChargingRequest();
            }
        });
    }

    private void addChargingRequest() {
        String vehicleId = etVehicleId.getText().toString().trim();
        String batteryLevelStr = etBatteryLevel.getText().toString().trim();

        if (vehicleId.isEmpty() || batteryLevelStr.isEmpty()) {
            Toast.makeText(this, "Please enter both Vehicle ID and Battery Level", Toast.LENGTH_SHORT).show();
            return;
        }

        int batteryLevel = Integer.parseInt(batteryLevelStr);

        ChargingRequest newRequest = new ChargingRequest(vehicleId, batteryLevel);
        chargingQueue.add(newRequest);
        updateQueueDisplay();

        Toast.makeText(this, "Added to Queue: " + vehicleId, Toast.LENGTH_SHORT).show();
        etVehicleId.setText("");
        etBatteryLevel.setText("");
    }

    private void updateQueueDisplay() {
        queueList.clear();
        PriorityQueue<ChargingRequest> tempQueue = new PriorityQueue<>(chargingQueue);

        while (!tempQueue.isEmpty()) {
            queueList.add(tempQueue.poll().toString());
        }

        queueAdapter.notifyDataSetChanged();
    }
}*/
