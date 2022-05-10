package com.example.warmorange.model;

public class Review {
    String text;
    int rating;
    public Review(String text, int rating) {
        this.text = text;
        this.rating = rating;
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
