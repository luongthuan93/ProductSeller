/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thuanjava.pr31_jv37_thuanphan_spingmvc_product.models;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author NO1
 */
public class CartInfo {
    private Order order;
    private List<CartItem> cartItems = new ArrayList<CartItem>();

    public CartInfo() {
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }
    
    public CartItem findItembyId(int id){
        for(CartItem cartItem:cartItems){
           if(cartItem.getProductId()==id){
               return cartItem;
           }
        }
        return null;
    }
    
}
