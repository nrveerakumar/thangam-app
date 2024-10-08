package com.thangam.onlineshopping;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CartDatabase extends SQLiteOpenHelper {
    Context context;

    public CartDatabase(@Nullable Context context) {
        super(context, "cartDatabase", null, 2); // Updated the version to 2
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create table including the 'quantity' field
        String CREATE_TABLE_QUERY = "CREATE TABLE cartDatabase(id INTEGER PRIMARY KEY AUTOINCREMENT, itemName TEXT, itemDisc TEXT, prise INTEGER, image INTEGER, itemCartColor INTEGER, isCart INTEGER, quantity INTEGER)";
        db.execSQL(CREATE_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 2) {
            db.execSQL("ALTER TABLE cartDatabase ADD COLUMN quantity INTEGER DEFAULT 1"); // Add 'quantity' column
        }
    }

    // Method to add data, now including the 'quantity' field
    public boolean addData(String itemName, String itemDisc, int prise, int image, int itemCartColor, int isCart, int quantity) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("itemName", itemName);
        contentValues.put("itemDisc", itemDisc);
        contentValues.put("prise", prise);
        contentValues.put("image", image);
        contentValues.put("itemCartColor", itemCartColor);
        contentValues.put("isCart", isCart);
        contentValues.put("quantity", quantity); // Add quantity value

        long l = sqLiteDatabase.insert("cartDatabase", null, contentValues);
        sqLiteDatabase.close();
        return l != -1;
    }

    // Method to retrieve all items in the cart, including 'quantity'
    public ArrayList<ItemClass> getCartItems() {
        ArrayList<ItemClass> cartArrayList = new ArrayList<>();
        try {
            SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM cartDatabase", null);
            if (cursor.moveToFirst()) {
                do {
                    String itemName = cursor.getString(1);
                    String itemDisc = cursor.getString(2);
                    int prise = cursor.getInt(3);
                    int image = cursor.getInt(4);
                    int itemCartColor = cursor.getInt(5);
                    int isCart = cursor.getInt(6);
                    int quantity = cursor.getInt(7); // Get quantity from cursor
                    cartArrayList.add(new ItemClass(itemName, itemDisc, prise, image, itemCartColor, isCart, quantity));
                } while (cursor.moveToNext());
                cursor.close();
            }
        } catch (Exception e) {
            Toast.makeText(context, "Error " + e, Toast.LENGTH_SHORT).show();
        }
        return cartArrayList;
    }

    // Method to clear the cart
    public void clear() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete("cartDatabase", null, null);
        sqLiteDatabase.close();
    }
}
