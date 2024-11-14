/*package com.example.dsa;

import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dsa.DatabaseHelper;
import com.example.dsa.PaymentHistory;
import com.example.dsa.PaymentHistoryAdapter;

import java.util.ArrayList;
import java.util.List;

public class PaymentHistoryActivity extends AppCompatActivity {

    private RecyclerView paymentHistoryRecyclerView;
    private PaymentHistoryAdapter paymentHistoryAdapter;
    private List<PaymentHistory> paymentHistoryList;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_history);

        paymentHistoryRecyclerView = findViewById(R.id.paymentHistoryRecyclerView);
        paymentHistoryRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        dbHelper = new DatabaseHelper(this);
        paymentHistoryList = new ArrayList<>();

        paymentHistoryAdapter = new PaymentHistoryAdapter(paymentHistoryList);
        paymentHistoryRecyclerView.setAdapter(paymentHistoryAdapter);
    }

    DatabaseHelper dbHelper = new DatabaseHelper(this);
    Cursor cursor = dbHelper.getAllPayments();
    if (cursor != null && cursor.moveToFirst()) {
        do {
            String vehicleNumber = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_PAYMENT_VEHICLE_NUMBER));
            String paymentStatus = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_PAYMENT_STATUS));
            String paymentDate = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_PAYMENT_DATE));
            double amount = cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_PAYMENT_AMOUNT));

            paymentHistoryList.add(new PaymentHistory(vehicleNumber, paymentStatus, paymentDate, amount));
        } while (cursor.moveToNext());
        cursor.close();
    }
    }
}
*/