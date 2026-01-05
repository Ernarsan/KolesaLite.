package com.kolesalite.model;

public class Car {
    private String id;
    private String brand;
    private String model;
    private int year;
    private int price;
    private int mileage;
    private String city;
    private String imageUrl;


    public Car(String id, String brand, String model, int year, int price, int mileage, String city, String imageUrl) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.price = price;
        this.mileage = mileage;
        this.city = city;
        this.imageUrl = imageUrl;
    }


    public Car(String id, String brand, String model, int year, int price, int mileage, String city) {
        this(id, brand, model, year, price, mileage, city, "");
    }


    public String getId() { return id; }
    public String getBrand() { return brand; }
    public String getModel() { return model; }
    public int getYear() { return year; }
    public int getPrice() { return price; }
    public int getMileage() { return mileage; }
    public String getCity() { return city; }
    public String getImageUrl() { return imageUrl; }


    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
}