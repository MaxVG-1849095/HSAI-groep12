package com.example.warmorange.model;

import java.util.ArrayList;
import java.util.List;

public class Account {
    public static long nextId = 0;

    private final String firstName;
    private final String lastName;
    private final String password;
    private final String email;
    private final List<Product> boughtProducts;

    private final long accountId;

    public Account(String firstName, String lastName, String password, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        accountId = nextId++;
        boughtProducts = new ArrayList<>();
    }

    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getName() { return firstName + " " + lastName; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public long getAccountId() { return accountId; }
    public List<Product> getBoughtProducts() { return boughtProducts; }
}
