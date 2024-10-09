package com.thangam.onlineshopping;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import java.util.ArrayList;

public class AdminViewFeedback extends AppCompatActivity {

    private ListView feedbackListView;
    private ArrayList<FeedBackModel> feedbackList;
    private FeedbackAdapter feedbackAdapter;
    private FeedbackDatabase feedbackDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_feedback);

        feedbackListView = findViewById(R.id.feedbackListView);
        feedbackDatabase = new FeedbackDatabase(this);

        // Get all feedbacks from the database and update the list
        feedbackList = feedbackDatabase.getAllFeedback(); // Returns ArrayList<FeedBackModel>

        // Initialize adapter with the correct data type FeedBackModel
        feedbackAdapter = new FeedbackAdapter(this, feedbackList, feedbackDatabase);

        feedbackListView.setAdapter(feedbackAdapter);
    }
}
