package com.example.warmorange.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.security.Timestamp;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class LoginData {
    private Account currentAccount;

    public LoginData() {
        currentAccount = null;
    }

    public Account getActiveUser() {
        return currentAccount;
    }

    // Assumes account with this email and password exists
    public void setActiveUser(String email) {
        accounts.stream()
                .filter(a -> a.getEmail().equals(email)).findFirst()
                .ifPresent( account -> currentAccount = account );
    }

    public void setActiveUser(long userId) {
        accounts.stream()
                .filter(a -> a.getAccountId() == userId).findFirst()
                .ifPresent( account -> currentAccount = account );
    }

    public void clearActiveUser() {
        currentAccount = null;
    }

    public boolean isAccount(String email) {
        return accounts.stream().anyMatch(acc -> acc.getEmail().equals(email));
    }

    public boolean matchingPassword(String email, String password) {
        return accounts.stream().anyMatch(
                acc ->
                    acc.getEmail().equals(email) &&
                    acc.getPassword().equals(password)
        );
    }

    public static void addAccount(Account account) {
        accounts.add(account);
    }

    private static final List<Account> accounts = new ArrayList<>(Arrays.asList(
            new Account("Max", "Van Gastel", "max", "max.vangastel@student.uhasselt.be"),
            new Account("Korneel", "Vanhulst", "korneel", "korneel.vanhulst@student.uhasselt.be"),
            new Account("Wino", "Joosten", "wino", "wino.joosten@student.uhasselt.be"),
            new Account("admin", "man", "123", "admin@a")
    ));

    public void fillAdmin(Product p){
        accounts.get(3).addProduct(p);
    }
}
