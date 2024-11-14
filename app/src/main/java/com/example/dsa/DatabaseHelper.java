package com.example.dsa;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "EVChargingSystem.db";
    private static final int DATABASE_VERSION = 2;

    // Tables and columns for user and vehicle registration
    public static final String TABLE_USERS = "users";
    public static final String COLUMN_VEHICLE_NO = "vehicle_no";
    public static final String COLUMN_PASSWORD = "password";

    public static final String TABLE_REGISTER = "registered_users";
    public static final String COLUMN_USER_NAME = "name";
    public static final String COLUMN_VEHICLE_NAME = "vehicle_name";
    public static final String COLUMN_ADDRESS = "address";
    public static final String COLUMN_MOBILE = "mobile";

    // Tables and columns for Slot Availability
    private static final String TABLE_SLOTS = "Slots";
    private static final String COLUMN_SLOT_ID = "slot_id";
    private static final String COLUMN_SLOT_STATUS = "status";

    // Tables and columns for Charging Requests
    private static final String TABLE_REQUESTS = "ChargingRequests";
    private static final String COLUMN_REQUEST_ID = "request_id";
    private static final String COLUMN_VEHICLE_TYPE = "vehicle_type";

    // Tables and columns for Queue Status
    private static final String TABLE_QUEUE = "QueueStatus";
    private static final String COLUMN_QUEUE_ID = "queue_id";
    private static final String COLUMN_VEHICLE_IN_QUEUE = "vehicle";

    // Charging Queue table (priority and battery level)
    private static final String TABLE_NAME = "charging_queue";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_VEHICLE_NUMBER = "vehicle_number";
    private static final String COLUMN_BATTERY_LEVEL = "battery_level";
    private static final String COLUMN_PRIORITY = "priority";

    // Table and columns for Payment Queue
// Payment Queue Table name and column names
    public static final String TABLE_PAYMENT_QUEUE = "payment_queue";
    public static final String COLUMN_PAYMENT_VEHICLE_NUMBER = "vehicle_number";
    public static final String COLUMN_PAYMENT_STATUS = "payment_status";
    public static final String COLUMN_PAYMENT_DATE = "payment_date";
    public static final String COLUMN_PAYMENT_AMOUNT = "payment_amount";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create Slots table
        String createSlotsTable = "CREATE TABLE " + TABLE_SLOTS + " (" +
                COLUMN_SLOT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_SLOT_STATUS + " TEXT)";
        db.execSQL(createSlotsTable);

        // Create Charging Requests table
        String createRequestsTable = "CREATE TABLE " + TABLE_REQUESTS + " (" +
                COLUMN_REQUEST_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_VEHICLE_TYPE + " TEXT, " +
                COLUMN_USER_NAME + " TEXT)";
        db.execSQL(createRequestsTable);

        // Create Queue Status table
        String createQueueTable = "CREATE TABLE " + TABLE_QUEUE + " (" +
                COLUMN_QUEUE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_VEHICLE_IN_QUEUE + " TEXT)";
        db.execSQL(createQueueTable);

        // Create Charging Queue table (priority and battery level)
        String createTablePriority = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_VEHICLE_NUMBER + " TEXT, " +
                COLUMN_BATTERY_LEVEL + " INTEGER, " +
                COLUMN_PRIORITY + " INTEGER)";
        db.execSQL(createTablePriority);

        // Create Registered Users table
        String createTableRegisteredUsers = "CREATE TABLE " + TABLE_REGISTER + " (" +
                COLUMN_VEHICLE_NO + " TEXT PRIMARY KEY, " +
                COLUMN_PASSWORD + " TEXT NOT NULL, " +
                COLUMN_USER_NAME + " TEXT, " +
                COLUMN_VEHICLE_NAME + " TEXT, " +
                COLUMN_ADDRESS + " TEXT, " +
                COLUMN_MOBILE + " INTEGER)";
        db.execSQL(createTableRegisteredUsers);

        String CREATE_PAYMENT_QUEUE_TABLE = "CREATE TABLE " + TABLE_PAYMENT_QUEUE + " ("
                + COLUMN_PAYMENT_VEHICLE_NUMBER + " TEXT, "
                + COLUMN_PAYMENT_STATUS + " TEXT, "
                + COLUMN_PAYMENT_DATE + " TEXT, "
                + COLUMN_PAYMENT_AMOUNT + " REAL" + ")";
        db.execSQL(CREATE_PAYMENT_QUEUE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SLOTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REQUESTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUEUE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REGISTER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PAYMENT_QUEUE);
        onCreate(db);
    }

    public void insertPaymentRecord(String vehicleNumber, String paymentStatus, String paymentDate, double amount) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_PAYMENT_VEHICLE_NUMBER, vehicleNumber);
        values.put(COLUMN_PAYMENT_STATUS, paymentStatus);
        values.put(COLUMN_PAYMENT_DATE, paymentDate);
        values.put(COLUMN_PAYMENT_AMOUNT, amount);

        // Inserting Row
        db.insert(TABLE_PAYMENT_QUEUE, null, values);
        db.close(); // Closing database connection
    }
    public Cursor getAllPayments() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_PAYMENT_QUEUE,
                new String[]{COLUMN_PAYMENT_VEHICLE_NUMBER, COLUMN_PAYMENT_STATUS, COLUMN_PAYMENT_DATE, COLUMN_PAYMENT_AMOUNT},
                null, null, null, null, COLUMN_PAYMENT_DATE + " DESC");  // Order by payment date
    }

    // You can also add other functions like updatePaymentStatus, deletePaymentRecord, etc.
    public void addVehicleToPaymentQueue(String vehicleNumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PAYMENT_VEHICLE_NUMBER, vehicleNumber);
        values.put(COLUMN_PAYMENT_STATUS, "Pending");

        db.insert(TABLE_PAYMENT_QUEUE, null, values);
        db.close();
    }

    public void updatePaymentStatus(String vehicleNumber, String status) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PAYMENT_STATUS, status);

        db.update(TABLE_PAYMENT_QUEUE, values, COLUMN_PAYMENT_VEHICLE_NUMBER + " = ?", new String[]{vehicleNumber});
        db.close();
    }
   /* public String getPaymentStatus(String vehicleNumber) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_PAYMENT_QUEUE, new String[]{COLUMN_PAYMENT_STATUS},
                COLUMN_PAYMENT_VEHICLE_NUMBER + " = ?", new String[]{vehicleNumber},
                null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            String status = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PAYMENT_STATUS));
            cursor.close();
            return status;
        }

        cursor.close();
        return "Not Found";  // If vehicle number not found
    }*/
    // Add slot to Slots table
    public void addSlot(String status) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_SLOT_STATUS, status);
        db.insert(TABLE_SLOTS, null, values);
        db.close();
    }

    // Get all available slots
    public Cursor getAvailableSlots() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_SLOTS + " WHERE " + COLUMN_SLOT_STATUS + " = 'Available'", null);
    }

    // Add a charging request
    public void addChargingRequest(String vehicleType, String userName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_VEHICLE_TYPE, vehicleType);
        values.put(COLUMN_USER_NAME, userName);
        db.insert(TABLE_REQUESTS, null, values);
        db.close();
    }

    // Add a vehicle to the queue
    public void addToQueue(String vehicle) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_VEHICLE_IN_QUEUE, vehicle);
        db.insert(TABLE_QUEUE, null, values);
        db.close();
    }

    // Get all vehicles in the queue
    public Cursor getQueueStatus() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_QUEUE, null);
    }

    // Insert vehicle into queue with priority
    public void addVehicleToQueue(String vehicleNumber, int batteryLevel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_VEHICLE_NUMBER, vehicleNumber);
        values.put(COLUMN_BATTERY_LEVEL, batteryLevel);

        // Assign priority based on battery level (vehicles < 20% get higher priority)
        int priority = (batteryLevel < 20) ? 1 : 2; // 1 is higher priority, 2 is lower
        values.put(COLUMN_PRIORITY, priority);

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    // Method to assign a charging slot to the vehicle

    public boolean releaseChargingSlot(String slotId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_SLOT_STATUS, "Available");

        int rowsUpdated = db.update(TABLE_SLOTS, values, COLUMN_SLOT_ID + " = ?", new String[]{slotId});

        return rowsUpdated > 0;  // If slot was successfully updated
    }


    // Insert user into the Users table
    public void insertUser(String vehicleNo, String password, String name, String vehicleName, String address, long mobile) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_VEHICLE_NO, vehicleNo);
        values.put(COLUMN_PASSWORD, password);
        values.put(COLUMN_USER_NAME, name);
        values.put(COLUMN_VEHICLE_NAME, vehicleName);
        values.put(COLUMN_ADDRESS, address);
        values.put(COLUMN_MOBILE, mobile); // Ensure mobile is long type

        db.insert(TABLE_REGISTER, null, values);
        db.close();
    }

    // Retrieve vehicles from the queue sorted by priority and battery level
    public Cursor getQueue() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_NAME, null, null, null, null, null,
                COLUMN_PRIORITY + " ASC, " + COLUMN_BATTERY_LEVEL + " ASC");
    }

    // Get all registered users
    public ArrayList<User> getAllRegisteredUsers() {
        ArrayList<User> users = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_REGISTER, null, null, null, null, null, null);

        while (cursor.moveToNext()) {
            // Get all column values with correct data types
            String vehicleNo = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_VEHICLE_NO));
            String password = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PASSWORD));
            String name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_USER_NAME));
            String vehicleName = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_VEHICLE_NAME));
            String address = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ADDRESS));
            long mobile = cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_MOBILE)); // Retrieve as long

            // Create a new User object
            User user = new User(name, mobile, address, vehicleName, vehicleNo, password); // Adjust based on User constructor
            users.add(user);
        }
        cursor.close();
        return users;
    }

    public void clearAllUsers() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, null, null); // Replace "users" with your table name
        db.close();
    }

    // Check if the vehicle is already in the charging queue
    public boolean isVehicleInQueue(String vehicleNumber) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, COLUMN_VEHICLE_NUMBER + " = ?", new String[]{vehicleNumber}, null, null, null);
        boolean inQueue = cursor.getCount() > 0;
        cursor.close();
        return inQueue;
    }

    public boolean areSlotsAvailable() {
        SQLiteDatabase db = this.getReadableDatabase();
        // Query to check if there is at least one slot with status "Available"
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_SLOTS + " WHERE " + COLUMN_SLOT_STATUS + " = 'Available'", null);
        boolean available = cursor.getCount() > 0; // If there is at least one available slot
        cursor.close();
        return available;
    }
    // Add initial slots with 'Available' status
    public void addInitialSlots() {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_SLOT_STATUS, "Available");
        db.insert(TABLE_SLOTS, null, values); // Insert initial slot
        db.close();
    }

}