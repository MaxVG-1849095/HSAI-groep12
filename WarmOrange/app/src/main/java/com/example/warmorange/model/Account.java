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

    private final long accountId;

    public Account(String firstName, String lastName, String password, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        accountId = nextId++;
        ownedProducts = new Vector<>();
        wishlist = new Vector<>();
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
    public Vector<Product> getWishlist() {
        return wishlist;
    }
    public void addProduct(Product p){
        ownedProducts.add(p);
    }
}
