package com.thangam.onlineshopping;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AdminDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "AdminLogin.db";
    private static final String TABLE_NAME = "admin";
    private static final String COL_1 = "ID";
    private static final String COL_2 = "EMAIL";
    private static final String COL_3 = "PASSWORD";

    public AdminDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, EMAIL TEXT, PASSWORD TEXT)");

        // Insert default admin credentials
        insertDefaultAdmin(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // Insert Admin Credentials
    public boolean insertAdminData(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, email);
        contentValues.put(COL_3, password);
        long result = db.insert(TABLE_NAME, null, contentValues);
        return result != -1; // Return true if data is inserted successfully
    }

    // Check if Admin Exists
    public boolean checkAdmin(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE EMAIL = ? AND PASSWORD = ?", new String[]{email, password});
        return cursor.getCount() > 0;
    }

    // Insert default admin credentials
    private void insertDefaultAdmin(SQLiteDatabase db) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, "admin@admin.com");
        contentValues.put(COL_3, "admin123");
        db.insert(TABLE_NAME, null, contentValues);
    }
}
