package com.example.dsa;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class UserAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<User> users;

    public UserAdapter(Context context, ArrayList<User> users) {
        this.context = context;
        this.users = users;
    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Object getItem(int position) {
        return users.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_user_item, parent, false);
        }

        User user = users.get(position);

        TextView userName = convertView.findViewById(R.id.userName);
        TextView vehicleNo = convertView.findViewById(R.id.vehicleNo);
        TextView mobile = convertView.findViewById(R.id.mobile);
        TextView userAddress = convertView.findViewById(R.id.userAddress);

        userName.setText("Name: " + user.getName());
        vehicleNo.setText("Vehicle No: " + user.getVehicleNo());
        mobile.setText("Mobile No: " + user.getMobile());
        userAddress.setText("Address: " + user.getAddress());

        return convertView;
    }
}
