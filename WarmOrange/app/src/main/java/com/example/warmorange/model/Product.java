package com.example.warmorange.model;

import com.example.warmorange.R;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class Product {
    private Vector<String> tags = new Vector<>();
    private final Map<String, String> attributes = new HashMap<>();
    private String name;
    private boolean available;
    private int imageId;
    private String type;
    private Vector<String> included = new Vector<>();
    private Vector<Review> reviews = new Vector<>();
    private int totalWarranty;
    private int currentWarranty = 0;
    private boolean reviewed = false;
    private boolean inComparison = false;

    public Product(String name, boolean available, int imageId, String type) {
        this.name = name;
        this.available = available;
        this.imageId = imageId;
        this.type = type;
        this.totalWarranty = 20;
        this.currentWarranty = 10;
    }

    public boolean isReviewed() {
        return reviewed;
    }

    public void setReviewed(boolean reviewed) {
        this.reviewed = reviewed;
    }

    public int getTotalWarranty() {
        return totalWarranty;
    }

    public void setTotalWarranty(int totalWarranty) {
        this.totalWarranty = totalWarranty;
    }

    public int getCurrentWarranty() {
        return currentWarranty;
    }

    public void setCurrentWarranty(int currentWarranty) {
        this.currentWarranty = currentWarranty;
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
    public List<String> getTagList() {return Collections.list(tags.elements());}

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
    public int getTextReviewAmount(){
        int count = 0;
        for(Review r : reviews){
            if(!r.getText().equals("")){
                count++;
            }
        }
        return count;
    }

    public boolean isInComparison() { return inComparison; }
    public void addToComparison() { inComparison = true; }
    public void removeFromComparison() { inComparison = false; }

    public Map<String, String> getAttributes() {
        return attributes;
    }
    public void addAttribute(String key, String value) {
        attributes.put(key, value);
    }
}
