package com.example.warmorange.model;

import android.hardware.camera2.CameraOfflineSession;

import com.example.warmorange.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;
import java.util.List;

public class ProductData {
    private HashMap<String,Product> productMap = new HashMap<>();
    private Product currentProduct;
    private Vector<Product> comparisonList = new Vector<>();
    private final Vector<Category> categories = new Vector<>();
    public ProductData(){
        fillProductData();
        fillCategories();
        currentProduct = productMap.get("Iphone 13");
    }
    private void fillProductData(){
        productMap.put("Samsung QLED 50Q64A (2021)",new Product("Samsung QLED 50Q64A (2021)",true, R.drawable.samsung_tv,"Televisie"));
        productMap.get("Samsung QLED 50Q64A (2021)").addTag("4K");
        productMap.get("Samsung QLED 50Q64A (2021)").addTag("UHD");
        productMap.get("Samsung QLED 50Q64A (2021)").addTag("LED-LCD");
        productMap.get("Samsung QLED 50Q64A (2021)").addIncluded("HDMI kabel");
        productMap.get("Samsung QLED 50Q64A (2021)").addIncluded("Stekker");
        productMap.get("Samsung QLED 50Q64A (2021)").addIncluded("Afstandsbediening");
        productMap.get("Samsung QLED 50Q64A (2021)").addReview("Zeer goede televie!",4);
        productMap.get("Samsung QLED 50Q64A (2021)").addReview("Niet zo handig om mee te nemen",3);
        productMap.get("Samsung QLED 50Q64A (2021)").setTotalWarranty(24);
        productMap.get("Samsung QLED 50Q64A (2021)").setCurrentWarranty(25);
        productMap.get("Samsung QLED 50Q64A (2021)").addAttribute("Grootte", "Groot");
        productMap.get("Samsung QLED 50Q64A (2021)").addAttribute("Diagonaal", "56 inch");
        productMap.get("Samsung QLED 50Q64A (2021)").addAttribute("Paneeltype", "LED-LCD");
        productMap.get("Samsung QLED 50Q64A (2021)").addAttribute("Resolutie", "4K");

        productMap.put("Philips The One (50PUS8506) - Ambilight (2021)", new Product("Philips The One (50PUS8506) - Ambilight (2021)",false,R.drawable.philips_tv,"Televisie"));
        productMap.get("Philips The One (50PUS8506) - Ambilight (2021)").addTag("50Hz");
        productMap.get("Philips The One (50PUS8506) - Ambilight (2021)").addTag("UHD");
        productMap.get("Philips The One (50PUS8506) - Ambilight (2021)").addTag("LED-LCD");
        productMap.get("Philips The One (50PUS8506) - Ambilight (2021)").addIncluded("HDMI kabel");
        productMap.get("Philips The One (50PUS8506) - Ambilight (2021)").addIncluded("Stekker");
        productMap.get("Philips The One (50PUS8506) - Ambilight (2021)").addIncluded("Afstandsbediening");
        productMap.get("Philips The One (50PUS8506) - Ambilight (2021)").addReview("Zeer goede televie!",4);
        productMap.get("Philips The One (50PUS8506) - Ambilight (2021)").addReview("Niet zo handig om mee te nemen",3);
        productMap.get("Philips The One (50PUS8506) - Ambilight (2021)").setTotalWarranty(24);
        productMap.get("Philips The One (50PUS8506) - Ambilight (2021)").setCurrentWarranty(15);

        productMap.put("LG C1 OLED55C16LA - 55 inch - 4K OLED - 2021", new Product("LG C1 OLED55C16LA - 55 inch - 4K OLED - 2021",true,R.drawable.lg_tvc1,"Televisie"));
        productMap.get("LG C1 OLED55C16LA - 55 inch - 4K OLED - 2021").addTag("Prachtig beeld");
        productMap.get("LG C1 OLED55C16LA - 55 inch - 4K OLED - 2021").addTag("Uiterst snel");
        productMap.get("LG C1 OLED55C16LA - 55 inch - 4K OLED - 2021").addTag("Responsief");
        productMap.get("LG C1 OLED55C16LA - 55 inch - 4K OLED - 2021").addTag("100Hz");
        productMap.get("LG C1 OLED55C16LA - 55 inch - 4K OLED - 2021").addTag("4K");
        productMap.get("LG C1 OLED55C16LA - 55 inch - 4K OLED - 2021").addTag("HDR");
        productMap.get("LG C1 OLED55C16LA - 55 inch - 4K OLED - 2021").addIncluded("HDMI kabel");
        productMap.get("LG C1 OLED55C16LA - 55 inch - 4K OLED - 2021").addIncluded("Stekker");
        productMap.get("LG C1 OLED55C16LA - 55 inch - 4K OLED - 2021").addIncluded("Afstandsbediening");
        productMap.get("LG C1 OLED55C16LA - 55 inch - 4K OLED - 2021").addReview("Echt prachtig beeld!",5);
        productMap.get("LG C1 OLED55C16LA - 55 inch - 4K OLED - 2021").addReview("Perfecte grootte!",2);
        productMap.get("LG C1 OLED55C16LA - 55 inch - 4K OLED - 2021").setTotalWarranty(24);
        productMap.get("LG C1 OLED55C16LA - 55 inch - 4K OLED - 2021").setCurrentWarranty(13);
        productMap.get("LG C1 OLED55C16LA - 55 inch - 4K OLED - 2021").addAttribute("Grootte", "Groot");
        productMap.get("LG C1 OLED55C16LA - 55 inch - 4K OLED - 2021").addAttribute("Diagonaal", "55 inch");
        productMap.get("LG C1 OLED55C16LA - 55 inch - 4K OLED - 2021").addAttribute("Paneeltype", "OLED");
        productMap.get("LG C1 OLED55C16LA - 55 inch - 4K OLED - 2021").addAttribute("Verversings-\nsnelheid", "100Hz");

        productMap.put("Iphone 13", new Product("Iphone 13",true, R.drawable.iphone_13,"Smartphone"));
        productMap.get("Iphone 13").addTag("Prachtig beeld");
        productMap.get("Iphone 13").addTag("Uiterst snel");
        productMap.get("Iphone 13").addTag("Face id");
        productMap.get("Iphone 13").addTag("128GB");
        productMap.get("Iphone 13").addTag("5G");
        productMap.get("Iphone 13").addIncluded("Oplaadkabel");
        productMap.get("Iphone 13").addReview("Loopt geweldig!",5);
        productMap.get("Iphone 13").addReview("Past niet in mijn broekzak!",2);
        productMap.get("Iphone 13").setTotalWarranty(18);
        productMap.get("Iphone 13").setCurrentWarranty(3);

        productMap.put("Samsung Galaxy S22", new Product("Samsung Galaxy S22",false,R.drawable.samsung_s22,"Smartphone"));
        productMap.get("Samsung Galaxy S22").addTag("Prachtig beeld");
        productMap.get("Samsung Galaxy S22").addTag("Uiterst snel");
        productMap.get("Samsung Galaxy S22").addTag("Geweldige camera");
        productMap.get("Samsung Galaxy S22").addTag("256GB");
        productMap.get("Samsung Galaxy S22").addTag("5G");
        productMap.get("Samsung Galaxy S22").addIncluded("Oplaadkabel");
        productMap.get("Samsung Galaxy S22").addIncluded("Draadloze oplader");
        productMap.get("Samsung Galaxy S22").addReview("I Love Android!",5);
        productMap.get("Samsung Galaxy S22").addReview("Iets te groot voor mij!",3);
        productMap.get("Samsung Galaxy S22").setTotalWarranty(18);
        productMap.get("Samsung Galaxy S22").setCurrentWarranty(5);

        productMap.put("OPPO Find X5 Pro", new Product("OPPO Find X5 Pro",true,R.drawable.oppox5,"Smartphone"));
        productMap.get("OPPO Find X5 Pro").addTag("Prachtig scherm");
        productMap.get("OPPO Find X5 Pro").addTag("Snel opgeladen");
        productMap.get("OPPO Find X5 Pro").addTag("Geweldige camera");
        productMap.get("OPPO Find X5 Pro").addTag("256GB");
        productMap.get("OPPO Find X5 Pro").addTag("5G");
        productMap.get("OPPO Find X5 Pro").addIncluded("Oplaadkabel");
        productMap.get("OPPO Find X5 Pro").addIncluded("Draadloze oortjes");
        productMap.get("OPPO Find X5 Pro").addReview("Geweldige telefoon!",5);
        productMap.get("OPPO Find X5 Pro").addReview("Geweldige camera",4);
        productMap.get("OPPO Find X5 Pro").setTotalWarranty(18);
        productMap.get("OPPO Find X5 Pro").setCurrentWarranty(9);

        productMap.put("Apple magic keyboard", new Product("Apple magic keyboard",true,R.drawable.applekeyboard,"Keyboard"));
        productMap.get("Apple magic keyboard").addTag("Responsief");
        productMap.get("Apple magic keyboard").addTag("Uiterst snel");
        productMap.get("Apple magic keyboard").addTag("Draadloos");
        productMap.get("Apple magic keyboard").addIncluded("Oplaadkabel");
        productMap.get("Apple magic keyboard").addIncluded("Draadloze oortjes");
        productMap.get("Apple magic keyboard").addReview("Typt geweldig!",5);
        productMap.get("Apple magic keyboard").setTotalWarranty(12);
        productMap.get("Apple magic keyboard").setCurrentWarranty(3);

        productMap.put("SteelSeries Apex Pro Gaming", new Product("SteelSeries Apex Pro Gaming",true,R.drawable.steelseriesapex,"Keyboard"));
        productMap.get("SteelSeries Apex Pro Gaming").addTag("Razendsnel");
        productMap.get("SteelSeries Apex Pro Gaming").addTag("RGB verlichting");
        productMap.get("SteelSeries Apex Pro Gaming").addTag("AZERTY");
        productMap.get("SteelSeries Apex Pro Gaming").addReview("Onmisbaar voor gamers!",5);
        productMap.get("SteelSeries Apex Pro Gaming").addReview("Super leuke kleuren!",4);
        productMap.get("SteelSeries Apex Pro Gaming").setTotalWarranty(12);
        productMap.get("SteelSeries Apex Pro Gaming").setTotalWarranty(13);
    }
    private void fillCategories() {
        categories.add(new Category("Televisie", "Televisies", R.drawable.televisions));
        categories.add(new Category("Smartphone", "Smartphones", R.drawable.smartphones));
        categories.add(new Category("Keyboard", "Toetsenborden", R.drawable.keyboards));
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
    public Vector<Product> getComparisonList() { return comparisonList; }
    public List<Category> getCategories() {
        return categories;
    }
    public List<Product> getAllProducts() {
        return new ArrayList<>(productMap.values());
    }

}
