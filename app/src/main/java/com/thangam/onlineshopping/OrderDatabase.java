package com.thangam.onlineshopping;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.thangam.onlineshopping.myclass.Order;

import java.util.ArrayList;

public class OrderDatabase extends SQLiteOpenHelper {

    private Context context;

    public OrderDatabase(@Nullable Context context) {
        super(context, "orderDatabase", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create a table to store order details
        String CREATE_TABLE_QUERY = "CREATE TABLE orders(id INTEGER PRIMARY KEY AUTOINCREMENT, phone TEXT, address TEXT, items TEXT, totalPrice REAL)";
        db.execSQL(CREATE_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS orders");
        onCreate(db);
    }

    // Method to add a new order to the database
    public boolean addOrder(String phone, String address, String items, double totalPrice) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("phone", phone);
        contentValues.put("address", address);
        contentValues.put("items", items);
        contentValues.put("totalPrice", totalPrice);

        long result = db.insert("orders", null, contentValues);
        db.close();
        return result != -1; // If insertion is successful, return true
    }

    // Method to retrieve all orders
    public ArrayList<Order> getAllOrders() {
        ArrayList<Order> orders = new ArrayList<Order>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM orders", null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String phone = cursor.getString(1);
                String address = cursor.getString(2);
                String items = cursor.getString(3);
                double totalPrice = cursor.getDouble(4);

                orders.add(new Order(id, items, totalPrice, address, phone));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return orders;
    }

    // Method to remove an order by ID
    public void removeOrder(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("orders", "id=?", new String[]{String.valueOf(id)});
        db.close();
    }
}
