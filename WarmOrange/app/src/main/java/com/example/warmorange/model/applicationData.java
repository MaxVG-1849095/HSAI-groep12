package com.example.warmorange.model;

import java.util.HashMap;
import java.util.Map;

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
    public Map<String, String> getAttributeExplanations() {
        return attributeExplanations;
    }
    public wizardData getwData(){
        return wData;
    }
    public ProductData getProductData(){return productData;}
    public LoginData getLoginData() { return loginData; }

    public Account getTestAccount() {
        return testAccount;
    }

    private static final Map<String, String> attributeExplanations = new HashMap<String, String>()
    {{
            put("Paneeltype", "Schermen komen in verschillende soorten en maten, elk met hun eigen voor- en nadelen. De meest voorkomende paneeltypes zijn:\n" +
                    "\n" +
                    "*OLED:* OLED Schermen zijn kleurrijk en kunnen een diep zwart tonen. Meestal zijn dit soort schermen echter minder licht en duurder.\n" +
                    "\n" +
                    "*IPS:* Een goed allround type. Helder en redelijk veel diepgang in kleur.\n" +
                    "\n" +
                    "...");
            put("exmp", "exmp");
    }};
}
