package com.thangam.onlineshopping;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class FeedbackDatabase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "FeedbackDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_FEEDBACK = "feedback";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_FEEDBACK = "feedback";
    private static final String COLUMN_RATING = "rating";

    public FeedbackDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_FEEDBACK_TABLE = "CREATE TABLE " + TABLE_FEEDBACK + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_FEEDBACK + " TEXT,"
                + COLUMN_RATING + " INTEGER" + ")";
        db.execSQL(CREATE_FEEDBACK_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FEEDBACK);
        onCreate(db);
    }

    // Add a new feedback entry
    public boolean addFeedback(String name, String feedback, int rating) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_FEEDBACK, feedback);
        values.put(COLUMN_RATING, rating);

        long result = db.insert(TABLE_FEEDBACK, null, values);
        db.close();

        // If insert fails, it returns -1
        return result != -1;
    }

    // Fetch all feedback entries from the database
    public ArrayList<FeedBackModel> getAllFeedback() {
        ArrayList<FeedBackModel> feedbackList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_FEEDBACK;
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID));
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME));
                @SuppressLint("Range") String feedback = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_FEEDBACK));
                @SuppressLint("Range") int rating = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_RATING));

                FeedBackModel feedbackItem = new FeedBackModel(id, name, feedback, rating);
                feedbackList.add(feedbackItem);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return feedbackList;
    }

    // Remove feedback by ID
    public void removeFeedback(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_FEEDBACK, COLUMN_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
    }
}
