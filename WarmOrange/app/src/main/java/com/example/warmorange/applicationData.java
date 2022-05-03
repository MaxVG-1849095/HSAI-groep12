package com.example.warmorange;

public class applicationData {
    private static final applicationData instance = new applicationData();
    private wizardData wData= new wizardData();
    private applicationData(){

    }

    public static applicationData getInstance(){
        return instance;
    }
    public wizardData getwData(){
        return wData;
    }
}
