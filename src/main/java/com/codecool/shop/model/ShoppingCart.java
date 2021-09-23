package com.codecool.shop.model;

import java.util.HashMap;
import java.util.Hashtable;

public class ShoppingCart {

    HashMap<String, Integer> cart = new HashMap<>();

    public void addToCart(String productName, int amount) {
        this.cart.put(productName, amount);
    }

    public HashMap<String, Integer> getCart() {
        return cart;
    }
/*
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (String productName : cart.keySet()) {
            output.append(productName).append(":").append(cart.get(productName)).append(",");
        }
        return output.toString();
    }

 */
}
