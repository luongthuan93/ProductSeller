/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thuanjava.pr31_jv37_thuanphan_spingmvc_product.models;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author NO1
 */
public class Order {
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date orderDate;
    private String name;
    private String address;
    private String phone;
    

    public Order() {
    }

    public Order(Date orderDate, String name, String address, String phone) {
        this.orderDate = orderDate;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }
    
    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
    
    
}
