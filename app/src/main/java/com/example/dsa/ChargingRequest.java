package com.example.dsa;

public class ChargingRequest {
    private String vehicleId;
    private int batteryLevel;

    public ChargingRequest(String vehicleId, int batteryLevel) {
        this.vehicleId = vehicleId;
        this.batteryLevel = batteryLevel;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public int getBatteryLevel() {
        return batteryLevel;
    }
}
