package com.example.dsa;

public class Slot {
    private int id;
    private String status;

    public Slot(int id, String status) {
        this.id = id;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }
}
