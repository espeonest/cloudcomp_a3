package com.example.assignment3_ChhetriHallRogers.model;

public class Shoes {

    private int shoeID;
    private String name;
    private String description;
    private double price;
    private String sku;
    private boolean isActive;
    private String image;

    // Constructor with all fields
    public Shoes(int shoeID, String name, String description, double price, String sku, boolean isActive, String image) {
        this.shoeID = shoeID;
        this.name = name;
        this.description = description;
        this.price = price;
        this.sku = sku;
        this.isActive = isActive;
        this.image = image;
    }

    // Default constructor
    public Shoes() {
    }

    // Getters and Setters
    public int getShoeID() {
        return shoeID;
    }

    public void setShoeID(int shoeID) {
        this.shoeID = shoeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
