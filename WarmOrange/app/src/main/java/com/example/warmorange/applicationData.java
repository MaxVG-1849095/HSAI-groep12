package com.example.warmorange;

import com.example.warmorange.model.LoginData;
import com.example.warmorange.model.ProductData;

public class applicationData {
    private static final applicationData instance = new applicationData();
    private final wizardData wData= new wizardData();
    private final ProductData productData = new ProductData();
    private final LoginData loginData = new LoginData();
    private applicationData(){

    }

    public static applicationData getInstance(){
        return instance;
    }
    public wizardData getwData(){
        return wData;
    }
    public ProductData getProductData(){return productData;}
    public LoginData getLoginData() { return loginData; }
}
