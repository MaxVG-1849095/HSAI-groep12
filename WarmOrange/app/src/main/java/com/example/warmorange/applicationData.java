package com.example.warmorange;

import com.example.warmorange.model.LoginData;
import com.example.warmorange.model.ProductData;

public class applicationData {
    private static final applicationData instance = new applicationData();
    private final wizardData wData= new wizardData();
    private final LoginData loginData = new LoginData();
    private final ProductData productData = new ProductData();
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
    public ProductData getProductData(){return productData;}
}
