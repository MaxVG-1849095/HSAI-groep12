package com.example.warmorange.model;

import java.util.ArrayList;

public class Wizard {
    private ArrayList<Integer> responses = new ArrayList<>();
    private String result = "";
    private String date = "";

    public Wizard(WizardInstance instance) {
        responses = instance.getResponses();
        result = instance.getResult();
        date = instance.getDate();
    }
    public String getResult() {
        return result;
    }
    public String getDate() {
        return date;
    }
}
