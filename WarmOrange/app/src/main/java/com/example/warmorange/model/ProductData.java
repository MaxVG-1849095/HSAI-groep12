package com.example.warmorange.model;

import com.example.warmorange.R;

import java.util.HashMap;

public class ProductData {
    private HashMap<String,Product> productMap = new HashMap<>();
    private Product currentProduct;
    public ProductData(){
        fillProductData();
        currentProduct = productMap.get("Philips The One (50PUS8506) - Ambilight (2021)");
    }
    private void fillProductData(){
        productMap.put("Samsung QLED 50Q64A (2021)",new Product("Samsung QLED 50Q64A (2021)",true, R.drawable.lg_tv,"Televisie"));
        productMap.get("Samsung QLED 50Q64A (2021)").addTag("4K");
        productMap.get("Samsung QLED 50Q64A (2021)").addTag("UHD");
        productMap.get("Samsung QLED 50Q64A (2021)").addTag("LED-LCD");
        productMap.get("Samsung QLED 50Q64A (2021)").addIncluded("HDMI kabel");
        productMap.get("Samsung QLED 50Q64A (2021)").addIncluded("Stekker");
        productMap.get("Samsung QLED 50Q64A (2021)").addIncluded("Afstandsbediening");
        productMap.get("Samsung QLED 50Q64A (2021)").addReview("Zeer goede televie!",4);
        productMap.get("Samsung QLED 50Q64A (2021)").addReview("Niet zo handig om mee te nemen",3);

        productMap.put("Philips The One (50PUS8506) - Ambilight (2021)", new Product("Philips The One (50PUS8506) - Ambilight (2021)",false,R.drawable.philips_tv,"Televisie"));
        productMap.get("Philips The One (50PUS8506) - Ambilight (2021)").addTag("50Hz");
        productMap.get("Philips The One (50PUS8506) - Ambilight (2021)").addTag("UHD");
        productMap.get("Philips The One (50PUS8506) - Ambilight (2021)").addTag("LED-LCD");
        productMap.get("Philips The One (50PUS8506) - Ambilight (2021)").addIncluded("HDMI kabel");
        productMap.get("Philips The One (50PUS8506) - Ambilight (2021)").addIncluded("Stekker");
        productMap.get("Philips The One (50PUS8506) - Ambilight (2021)").addIncluded("Afstandsbediening");
        productMap.get("Philips The One (50PUS8506) - Ambilight (2021)").addReview("Zeer goede televie!",4);
        productMap.get("Philips The One (50PUS8506) - Ambilight (2021)").addReview("Niet zo handig om mee te nemen",3);
    }
    public Product getProduct(String productname) {
        return productMap.get(productname);
    }
    public void setCurrentProduct(String name){
        currentProduct = productMap.get(name);
    }
    public Product getCurrentProduct(){
        return currentProduct;
    }
}
