package com.example.assignment3_ChhetriHallRogers.model;

public class Cart {
    private int cartID;
    private String sessionId;
    private int productCount;
    private float totalPrice;

    // Constructor initializing all fields
    public Cart(int cartID, String sessionId, int productCount, float totalPrice) {
        this.cartID = cartID;
        this.sessionId = sessionId;
        this.productCount = productCount;
        this.totalPrice = totalPrice;
    }
    // Default constructor with only sessionId
    public Cart(String sessionId){
        this.sessionId = sessionId;
    }

    // Getters & Setters
    public int getCartID() {
        return cartID;
    }
    public void setCartID(int cartID) {
        this.cartID = cartID;
    }
    public String getSessionId() {
        return sessionId;
    }
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
    public int getProductCount() {
        return productCount;
    }
    public void setProductCount(int productCount) {
        this.productCount = productCount;
    }
    public float getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }
}
