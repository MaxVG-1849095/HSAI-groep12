package com.example.warmorange.model;

import java.util.HashMap;
import java.util.Map;

public class applicationData {
    private static final applicationData instance = new applicationData();
    private final WizardData wData= new WizardData();
    private final ProductData productData = new ProductData();
    private final DemoData demoData = new DemoData();
    private final LoginData loginData = new LoginData();
    private applicationData(){
        loginData.fillAdmin(productData.getProduct("Iphone 13"));
        loginData.fillAdmin(productData.getProduct("Samsung QLED 50Q64A (2021)"));
    }

    public static applicationData getInstance(){
        return instance;
    }
    public Map<String, String> getAttributeExplanations() {
        return attributeExplanations;
    }
    public WizardData getwData(){
        return wData;
    }
    public ProductData getProductData(){return productData;}
    public DemoData getDemoData() {return demoData;}
    public LoginData getLoginData() { return loginData; }



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
