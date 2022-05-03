package com.example.warmorange.model;

public class Account {
    private final String firstName;
    private final String lastName;
    private final String password;
    private final String email;

    public Account(String firstName, String lastName, String password, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
    }

    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getName() { return firstName + " " + lastName; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
}
