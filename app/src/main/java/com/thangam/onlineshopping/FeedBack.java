package com.thangam.onlineshopping;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

public class FeedBack extends AppCompatActivity {

    private EditText etName, etFeedback;
    private RatingBar ratingBar;
    private Button btnSubmit;
    private FeedbackDatabase feedbackDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);

        // Initialize views
        etName = findViewById(R.id.etName);
        etFeedback = findViewById(R.id.etFeedback);
        ratingBar = findViewById(R.id.ratingBar);
        btnSubmit = findViewById(R.id.btnSubmit);

        // Initialize database helper
        feedbackDatabase = new FeedbackDatabase(this);

        // Set click listener for the submit button
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitFeedback();
            }
        });
    }

    // Method to submit feedback
    private void submitFeedback() {
        String name = etName.getText().toString().trim();
        String feedback = etFeedback.getText().toString().trim();
        int rating = (int) ratingBar.getRating(); // Get the rating (out of 5)

        // Basic validation
        if (name.isEmpty() || feedback.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Insert feedback into the database
        boolean success = feedbackDatabase.addFeedback(name, feedback, rating);

        if (success) {
            Toast.makeText(this, "Feedback submitted successfully!", Toast.LENGTH_SHORT).show();
            // Clear the fields after submission
            etName.setText("");
            etFeedback.setText("");
            ratingBar.setRating(0);
        } else {
            Toast.makeText(this, "Failed to submit feedback", Toast.LENGTH_SHORT).show();
        }
    }
}
