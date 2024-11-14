package com.example.dsa;

public class User {
    private String name;
    private long mobile;
    private String address;
    private String vehicleName;
    private String vehicleNo;
    private String password;

    // Constructor
    public User(String name, long mobile, String address, String vehicleName, String vehicleNo, String password) {
        this.name = name;
        this.mobile = mobile;
        this.address = address;
        this.vehicleName = vehicleName;
        this.vehicleNo = vehicleNo;
        this.password = password;
    }
    // Getters
    public String getName() {
        return name;
    }

    public long getMobile() {
        return mobile;
    }

    public String getAddress() {
        return address;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public String getPassword() {
        return password;
    }
}
