package com.example.warmorange.model;

public class Demo {
    private final boolean forProduct;
    private String demoName;
    private boolean cancelled = false;
    private int day, month, year;
    private int hour, minute;
    private int id;
    private Product product;
    private wizardInstance wizzard;
    public Demo(String name, boolean forProduct, int day, int month, int year, int hour, int minute) {
        demoName = name;
        this.forProduct = forProduct;
        this.day = day;
        this.month = month;
        this.year = year;
        this.hour = hour;
        this.minute = minute;
    }
    public Demo(Product product, int day, int month, int year, int hour, int minute) {
        this(product.getName(), true, day, month, year, hour, minute);
        this.product = product;
    }
    public Demo(wizardInstance wizzard, int day, int month, int year, int hour, int minute) {
        this(wizzard.getResult(), false, day, month, year, hour, minute);
        this.wizzard = wizzard;
    }

    public void setDate(int day, int month, int year, int hour, int minute) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.hour = hour;
        this.minute = minute;
    }

    public void setID(int id) { this.id = id; }

    public boolean isForProduct() { return forProduct; }

    public String getDemoName() { return demoName; }

    public boolean isCancelled() { return cancelled; }

    public void cancel() { cancelled = true; }

    public int getDay() { return day; }

    public int getMonth() { return month; }

    public int getYear() { return year; }

    public int getHour() { return hour; }

    public int getMinute() { return minute; }

    public int getId() { return id; }
}
