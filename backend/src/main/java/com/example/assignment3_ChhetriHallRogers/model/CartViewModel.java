package com.example.assignment3_ChhetriHallRogers.model;

import java.util.List;

public class CartViewModel {
    int cartId;
    List<Shoes> contents;
    int[] entryIds;
    Float totalPrice = 0.0f;
    int cartSize;

    public CartViewModel(int cartId) {
        this.cartId = cartId;
    }

    public List<Shoes> getContents() {
        return contents;
    }
    public void setContents(List<Shoes> contents) {
        this.contents = contents;
    }

    public int[] getEntryIds() {
        return entryIds;
    }
    public void setEntryIds(int[] entryIds) {
        this.entryIds = entryIds;
    }

    public Float getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getCartSize() {
        return cartSize;
    }
    public void setCartSize(int cartSize) {
        this.cartSize = cartSize;
    }
}
