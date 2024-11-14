/*package com.example.dsa;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PaymentHistoryAdapter extends RecyclerView.Adapter<PaymentHistoryAdapter.PaymentHistoryViewHolder> {

    private List<PaymentHistory> paymentHistoryList;

    public PaymentHistoryAdapter(List<PaymentHistory> paymentHistoryList) {
        this.paymentHistoryList = paymentHistoryList;
    }

    @NonNull
    @Override
    public PaymentHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_payment_history, parent, false);
        return new PaymentHistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PaymentHistoryViewHolder holder, int position) {
        PaymentHistory paymentHistory = paymentHistoryList.get(position);

        // Binding data to the views
        holder.vehicleNumberTextView.setText(paymentHistory.getVehicleNumber());
        holder.paymentStatusTextView.setText(paymentHistory.getPaymentStatus());
        holder.paymentAmountTextView.setText(String.valueOf(paymentHistory.getAmount()));
        holder.paymentDateTextView.setText(paymentHistory.getPaymentDate());
    }

    @Override
    public int getItemCount() {
        return paymentHistoryList.size();
    }

    public static class PaymentHistoryViewHolder extends RecyclerView.ViewHolder {
        TextView vehicleNumberTextView, paymentStatusTextView, paymentAmountTextView, paymentDateTextView;

        public PaymentHistoryViewHolder(View itemView) {
            super(itemView);

            // Initialize all views in the item layout
            vehicleNumberTextView = itemView.findViewById(R.id.vehicleNumberTextView);
            paymentStatusTextView = itemView.findViewById(R.id.paymentStatusTextView);
            paymentAmountTextView = itemView.findViewById(R.id.paymentAmountTextView);
            paymentDateTextView = itemView.findViewById(R.id.paymentDateTextView);
        }
    }
}
*/