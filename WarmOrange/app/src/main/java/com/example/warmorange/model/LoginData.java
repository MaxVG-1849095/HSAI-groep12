package com.example.warmorange.model;

import java.util.Optional;
import java.util.stream.Stream;

public class LoginData {
    private Account currentAccount;

    public LoginData() {
        currentAccount = null;
    }

    public boolean isAccount(String email) {
        return Stream.of(accounts).anyMatch(acc -> acc.getEmail().equals(email));
    }

    public boolean matchingPassword(String email, String password) {
        return Stream.of(accounts).anyMatch(
                acc ->
                    acc.getEmail().equals(email) &&
                    acc.getPassword().equals(password)
        );
    }

    // Assumes account with this email and password exists
    public void setAccount(String email) {
        Optional<Account> account = Stream.of(accounts)
                .filter(acc -> acc.getEmail().equals(email)).findFirst();
        account.ifPresent(value -> currentAccount = value);
    }

    private final Account[] accounts = new Account[] {
        new Account("Max", "Van Gastel", "max","max.vangastel@student.uhasselt.be"),
        new Account("Korneel", "Vanhulst", "korneel","korneel.vanhulst@student.uhasselt.be"),
        new Account("Wino", "Joosten", "wino","wino.joosten@student.uhasselt.be"),
        new Account("admin", "man", "123","admin@a"),
    };

}
