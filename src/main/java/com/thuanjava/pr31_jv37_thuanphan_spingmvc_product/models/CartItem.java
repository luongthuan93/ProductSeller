/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thuanjava.pr31_jv37_thuanphan_spingmvc_product.models;

import com.thuanjava.pr31_jv37_thuanphan_spingmvc_product.entities.ProductEntity;

/**
 *
 * @author NO1
 */
public class CartItem {
    private int productId;
    private String name;
    private double price;
    private int quantity;

    public CartItem() {
    }

    public CartItem(int productId, String name, double price, int quantity) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
    

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public void getCartItem(ProductEntity product){
        this.productId = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.quantity = 1;
    }
}
