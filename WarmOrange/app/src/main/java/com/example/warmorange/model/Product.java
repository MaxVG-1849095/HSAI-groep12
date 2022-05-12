package com.example.warmorange.model;

import com.example.warmorange.R;

import java.util.Vector;

public class Product {
    private Vector<String> tags = new Vector<>();
    private String name;
    private boolean available;
    private int imageId;
    private String type;
    private Vector<String> included = new Vector<>();
    private Vector<Review> reviews = new Vector<>();

    public Product(String name, boolean available, int imageId, String type) {
        this.name = name;
        this.available = available;
        this.imageId = imageId;
        this.type = type;

    }
    public Vector<String> getIncluded(){
        return included;
    }
    public void addIncluded(String newIncl){
        included.add(newIncl);
    }
    public Vector<String> getTags() {
        return tags;
    }

    public void addTag(String tag) {
        tags.add(tag);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageid(int imageId) {
        this.imageId = imageId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public void addReview(String text, int rating){
        reviews.add(new Review(text, rating));
    }

    public Vector<Review> getReviews() {
        return reviews;
    }

    public double getAverageReviewScore(){
        double score = 0;
        for(Review r : reviews){
            score += r.getRating();

        }
        return score/ reviews.size();
    }
}
