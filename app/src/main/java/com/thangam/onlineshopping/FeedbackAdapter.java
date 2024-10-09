package com.thangam.onlineshopping;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class FeedbackAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<FeedBackModel> feedbackList;
    private FeedbackDatabase feedbackDatabase;

    public FeedbackAdapter(Context context, ArrayList<FeedBackModel> feedbackList, FeedbackDatabase feedbackDatabase) {
        this.context = context;
        this.feedbackList = feedbackList;
        this.feedbackDatabase = feedbackDatabase;
    }

    @Override
    public int getCount() {
        return feedbackList.size();
    }

    @Override
    public Object getItem(int position) {
        return feedbackList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.feedback_list_item, parent, false);
        }

        TextView userName = convertView.findViewById(R.id.userName);
        TextView feedbackText = convertView.findViewById(R.id.feedbackText);
        TextView feedbackRating = convertView.findViewById(R.id.feedbackRating);
        Button removeButton = convertView.findViewById(R.id.removeFeedbackButton);

        final FeedBackModel feedback = feedbackList.get(position);

        userName.setText("Name: " + feedback.getName());
        feedbackText.setText("Feedback: " + feedback.getFeedback());
        feedbackRating.setText("Rating: " + feedback.getRating() + "/5");

        // Remove feedback button functionality
        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                feedbackDatabase.removeFeedback(feedback.getId());
                feedbackList.remove(position);
                notifyDataSetChanged();
            }
        });

        return convertView;
    }
}
