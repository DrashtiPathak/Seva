package com.example.drashtimuni.seva;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String TAG = "DatabaseHelper";
    public static final String TABLE_NAME = "seva";

    public DatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(id integer primary key autoincrement,item_name text, type_of_food text, quantity text," +
                " date_of_expiry text, perishable text, allergies text, supplier_name text, address text, pickup_time text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists item");
        onCreate(db);
    }

    public boolean insertItem(String itemName, String typeOfFood, String quantity, String dateOfExpiry, String perishable,
                              String allergies, String supplierName, String address, String pickupTime) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("item_name", itemName);
        contentValues.put("type_of_food", typeOfFood);
        contentValues.put("quantity", quantity);
        contentValues.put("date_of_expiry", dateOfExpiry);
        contentValues.put("perishable", perishable);
        contentValues.put("allergies", allergies);
        contentValues.put("supplier_name", supplierName);
        contentValues.put("address", address);
        contentValues.put("pickup_time", pickupTime);
        long result = database.insert(TABLE_NAME, null, contentValues);
        return (result != -1);
    }

    public Cursor getAllData() {
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor result = database.rawQuery("select * from " + TABLE_NAME, null);
        return result;
    }

    public Cursor getAllDataForId(String id) {
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor result = database.rawQuery("select * from " + TABLE_NAME + " where id = " + id, null);
        return result;
    }

    public int deleteDataForId(String id) {
        SQLiteDatabase database = this.getWritableDatabase();
        return database.delete(TABLE_NAME, "id = " + id, null);
    }

    public Cursor getItemNames() {
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor result = database.rawQuery("select item_name from " + TABLE_NAME, null);
        return result;
    }
}