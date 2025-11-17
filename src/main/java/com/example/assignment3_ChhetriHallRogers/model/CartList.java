package com.example.assignment3_ChhetriHallRogers.model;

public class CartList {
    private int EntryId;
    private int CartId;
    private int ShoeId;

    public CartList(int EntryId, int CartId, int ShoeId) {
        this.EntryId = EntryId;
        this.CartId = CartId;
        this.ShoeId = ShoeId;
    }

    public int getEntryId() {
        return EntryId;
    }

    public int getCartId() {
        return CartId;
    }

    public int getShoeId() {
        return ShoeId;
    }
}
