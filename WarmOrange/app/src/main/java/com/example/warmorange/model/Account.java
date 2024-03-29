package com.example.warmorange.model;

import java.util.Vector;

public class Account {
    public static long nextId = 0;

    private final String firstName;
    private final String lastName;
    private final String password;
    private final String email;
    private final Vector<Product> ownedProducts;
    private final Vector<Product> wishlist;
    private final Vector<Product> wizardOutcomes;

    private final long accountId;

    public Account(String firstName, String lastName, String password, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        accountId = nextId++;
        ownedProducts = new Vector<>();
        wishlist = new Vector<>();
        wizardOutcomes = new Vector<>();
    }

    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getName() { return firstName + " " + lastName; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public long getAccountId() { return accountId; }
    public Vector<Product> getOwnedProducts() {
        return ownedProducts;
    }
    public void removeProduct(String name){
        wishlist.removeIf(p -> p.getName() == name);
    }
    public boolean productInWishlist(String name){
        for(Product p : wishlist){
            if(p.getName().equals(name))
                return true;
        }
        return false;
    }
    public Vector<Product> getWishlist() {
        return wishlist;
    }
    public void addProduct(Product p){
        ownedProducts.add(p);
    }

    public Vector<Product> getWizardOutcomes() {
        return wizardOutcomes;
    }
    public void addWizardOutcome(String s){
        wizardOutcomes.add(applicationData.getInstance().getProductData().getProduct(s));
    }
}
