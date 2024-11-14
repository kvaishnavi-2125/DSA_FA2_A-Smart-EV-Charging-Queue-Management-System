package com.example.dsa;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class RegisteredUserActivity extends AppCompatActivity {

    private ListView registeredUsersListView;
    private DatabaseHelper dbHelper;
    private ArrayList<User> registeredUsers;
    private UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered_users);

        // Initialize the ListView
        registeredUsersListView = findViewById(R.id.registeredUsersListView);

        // Initialize the DatabaseHelper
        dbHelper = new DatabaseHelper(this);

        // Retrieve the list of registered users from the database
        registeredUsers = dbHelper.getAllRegisteredUsers();

        // Check if there are no registered users
        if (registeredUsers.isEmpty()) {
            Toast.makeText(this, "No registered users found", Toast.LENGTH_SHORT).show();
        } else {
            // Set up the adapter to bind the data to the ListView
            userAdapter = new UserAdapter(this, registeredUsers);
            registeredUsersListView.setAdapter(userAdapter);
        }
    }
}
