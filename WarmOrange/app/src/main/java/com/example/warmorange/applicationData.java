package com.example.warmorange;

import com.example.warmorange.model.LoginData;

public class applicationData {
    private static final applicationData instance = new applicationData();
    private final wizardData wData= new wizardData();
    private final LoginData loginData = new LoginData();
    private applicationData(){

    }

    public static applicationData getInstance(){
        return instance;
    }
    public wizardData getwData(){
        return wData;
    }
    public LoginData getLoginData() {
        return loginData;
    }
}
