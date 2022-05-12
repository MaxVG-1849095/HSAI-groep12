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
    private final SharedPreferences preferences;

    public LoginData(Context context) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        retrieveActiveUser();
    }

    public Account getActiveUser() {
        return currentAccount;
    }

    // Assumes account with this email and password exists
    public void setActiveUser(String email) {
        accounts.stream()
                .filter(a -> a.getEmail().equals(email)).findFirst()
                .ifPresent( account -> {
                    currentAccount = account;
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putLong("activeUser", account.getAccountId());
                    editor.apply();
                });
    }

    public void setActiveUser(long userId) {
        accounts.stream()
                .filter(a -> a.getAccountId() == userId).findFirst()
                .ifPresent( account -> {
                    currentAccount = account;
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putLong("activeUser", userId);
                    editor.apply();
                });
    }

    public void clearActiveUser() {
        currentAccount = null;
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove("activeUser");
        editor.apply();
    }

    private void retrieveActiveUser() {
        long userId = preferences.getLong("activeUser", -1);
        if (userId != -1)
            setActiveUser(userId);
        else
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

}
