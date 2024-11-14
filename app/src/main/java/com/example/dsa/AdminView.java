package com.example.dsa;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class AdminView extends AppCompatActivity {

    private ListView userListView;
    private DatabaseHelper dbHelper;
    private Button viewQueueStatusButton;
    private Button viewRegisteredUserButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminview); // Make sure this matches your layout file name

        viewQueueStatusButton = findViewById(R.id.viewQueueStatusButton1);

        viewRegisteredUserButton = findViewById(R.id.viewRegisteredUserButton);


        viewQueueStatusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent queueStatusIntent = new Intent(AdminView.this, QueueStatusActivity.class);
                startActivity(queueStatusIntent);
            }
        });
        viewRegisteredUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("AdminView", "Button clicked");

                Intent registeredUsersIntent = new Intent(AdminView.this, RegisteredUserActivity.class);
                startActivity(registeredUsersIntent);
            }
        });

    }
}