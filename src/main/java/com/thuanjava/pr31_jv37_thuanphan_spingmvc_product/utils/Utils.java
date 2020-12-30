/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thuanjava.pr31_jv37_thuanphan_spingmvc_product.utils;

import com.thuanjava.pr31_jv37_thuanphan_spingmvc_product.entities.ProductEntity;
import com.thuanjava.pr31_jv37_thuanphan_spingmvc_product.models.CartInfo;
import com.thuanjava.pr31_jv37_thuanphan_spingmvc_product.models.CartItem;
import com.thuanjava.pr31_jv37_thuanphan_spingmvc_product.models.Order;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author NO1
 */
public class Utils {


    public static CartInfo getCartInSession(HttpServletRequest request) {

        CartInfo cartInfo = (CartInfo) request.getSession().getAttribute("myCart");

        if (cartInfo == null) {
            cartInfo = new CartInfo();
            request.getSession().setAttribute("myCart", cartInfo);
        }

        return cartInfo;
    }

    public static void removeCartInSession(HttpServletRequest request){
        request.getSession().removeAttribute("myCart");
    }
    
    public static void addCartItemInSession(HttpServletRequest request, ProductEntity product) {
        CartInfo cartInfo = getCartInSession(request);
        List<CartItem> cartItems = cartInfo.getCartItems();

        CartItem newItem = new CartItem();
        newItem.getCartItem(product);
        
        if (cartItems.isEmpty()) {

            cartItems.add(newItem);
        } else {
            if (cartInfo.findItembyId(product.getId()) == null) {
                cartItems.add(newItem);
            } else {
                for (CartItem item : cartItems) {
                    if (item.getProductId() == product.getId()) {
                        item.setQuantity(item.getQuantity() + 1);
                    }
                }
            }
        }

        cartInfo.setCartItems(cartItems);
        request.getSession().setAttribute("myCart", cartInfo);
    }


    public static void deteleCartItem(HttpServletRequest request, ProductEntity product) {
        CartInfo cartInfo = getCartInSession(request);
        List<CartItem> cartItems = cartInfo.getCartItems();

        if (!cartItems.isEmpty()) {
            if(cartInfo.findItembyId(product.getId())!=null){
                cartItems.remove(cartInfo.findItembyId(product.getId()));
            }
        }
        
        cartInfo.setCartItems(cartItems);
        request.getSession().setAttribute("myCart", cartInfo);
    }

    public static void updateQuantityCart(HttpServletRequest request, CartInfo cartInfoForm) {
        CartInfo cartInfo = getCartInSession(request);
        for (CartItem itemForm : cartInfoForm.getCartItems()){
            if(itemForm.getQuantity()<=0){
                CartItem cartTemp = cartInfo.findItembyId(itemForm.getProductId());
                cartInfo.getCartItems().remove(cartTemp);                
            }
        }
        for (CartItem itemSession : cartInfo.getCartItems()) {
            for (CartItem itemForm : cartInfoForm.getCartItems()) {
                if (itemSession.getProductId() == itemForm.getProductId()) {                    
                    itemSession.setQuantity(itemForm.getQuantity());                    
                }
            }
        }
        
        
        request.getSession().setAttribute("myCart", cartInfo);
    }
    
    public static void orderSubmit(HttpServletRequest request,Order order){
        CartInfo cartInfo = getCartInSession(request);
        cartInfo.setOrder(order);
        request.getSession().setAttribute("myCart", cartInfo);
    }
    
    public static double getTotalPrice(HttpServletRequest request){
        double tempTotal = 0; 
        CartInfo cartInfo = getCartInSession(request);
        for(CartItem item:cartInfo.getCartItems()){
            tempTotal+= (item.getPrice()*item.getQuantity());
        }
        return tempTotal;
    }
}
