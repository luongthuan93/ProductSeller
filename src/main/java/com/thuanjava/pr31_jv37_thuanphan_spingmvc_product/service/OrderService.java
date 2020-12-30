/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thuanjava.pr31_jv37_thuanphan_spingmvc_product.service;

import com.thuanjava.pr31_jv37_thuanphan_spingmvc_product.entities.OrderEntity;
import com.thuanjava.pr31_jv37_thuanphan_spingmvc_product.models.Order;
import com.thuanjava.pr31_jv37_thuanphan_spingmvc_product.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author NO1
 */
@Service
public class OrderService {
    
    @Autowired
    private OrderRepository orderRepository;
    
    public void save(OrderEntity orderEntity){
        orderRepository.save(orderEntity);
    }
    
    public void saveOrder(Order order){
        
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setName(order.getName());
        orderEntity.setAddress(order.getAddress());
        orderEntity.setPhone(order.getPhone());
        orderEntity.setOrderDate(order.getOrderDate());
        orderRepository.save(orderEntity);
        
    }
}
