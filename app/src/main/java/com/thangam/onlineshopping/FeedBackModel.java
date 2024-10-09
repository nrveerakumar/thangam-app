package com.thangam.onlineshopping;

public class FeedBackModel {

    private int id;
    private String name;
    private String feedback;
    private int rating;

    // Constructor
    public FeedBackModel(int id, String name, String feedback, int rating) {
        this.id = id;
        this.name = name;
        this.feedback = feedback;
        this.rating = rating;
    }

    // Getter methods
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFeedback() {
        return feedback;
    }

    public int getRating() {
        return rating;
    }

    // Setter methods, if needed
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
