package com.example.warmorange.model;

public class applicationData {
    private static final applicationData instance = new applicationData();
    private final wizardData wData= new wizardData();
    private final ProductData productData = new ProductData();
    private final LoginData loginData = new LoginData();
    private final Account testAccount = new Account("testNaam","testNaam2", "test", "Test@test.test");
    private applicationData(){
        testAccount.addProduct(productData.getProduct("Samsung QLED 50Q64A (2021)"));
        testAccount.addProduct(productData.getProduct("Iphone 13"));
    }

    public static applicationData getInstance(){
        return instance;
    }
    public wizardData getwData(){
        return wData;
    }
    public ProductData getProductData(){return productData;}
    public LoginData getLoginData() { return loginData; }

    public Account getTestAccount() {
        return testAccount;
    }
}
