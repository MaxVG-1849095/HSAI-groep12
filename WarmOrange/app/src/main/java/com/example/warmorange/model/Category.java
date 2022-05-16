package com.example.warmorange.model;

import java.util.List;

public class Category {
    private final String name; // Tag name; e.g. Televisie
    private final String displayName; // Display name; e.g. Televisies
    private final int imageId;
    private final List<Category> subcategories;

    public Category(String name, String displayName, int imageId, List<Category> subcategories) {
        this.name = name;
        this.displayName = displayName;
        this.imageId = imageId;
        this.subcategories = subcategories;
    }
    public Category(String name, String displayName, int imageId) {
        this.name = name;
        this.displayName = displayName;
        this.imageId = imageId;
        subcategories = null;
    }

    public List<Category> getSubcategories() {
        return subcategories;
    }
    public int getImageId() {
        return imageId;
    }
    public String getDisplayName() {
        return displayName;
    }
    public String getName() {
        return name;
    }
}
