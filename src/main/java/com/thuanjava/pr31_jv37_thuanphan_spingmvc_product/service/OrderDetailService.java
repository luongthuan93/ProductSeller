/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thuanjava.pr31_jv37_thuanphan_spingmvc_product.service;

import com.thuanjava.pr31_jv37_thuanphan_spingmvc_product.entities.OrderDetailEntity;
import com.thuanjava.pr31_jv37_thuanphan_spingmvc_product.entities.OrderEntity;
import com.thuanjava.pr31_jv37_thuanphan_spingmvc_product.entities.ProductEntity;
import com.thuanjava.pr31_jv37_thuanphan_spingmvc_product.models.CartItem;
import com.thuanjava.pr31_jv37_thuanphan_spingmvc_product.repository.OrderDetailRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author NO1
 */
@Service
public class OrderDetailService {
    
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    
    public void save(OrderDetailEntity orderDetail){
        orderDetailRepository.save(orderDetail);
    }
    
    public void saveList(List<CartItem> cartItems,int orderId){
        for(CartItem item:cartItems){
             OrderDetailEntity detailEntity = new OrderDetailEntity();
             ProductEntity productEntity = new ProductEntity();
             productEntity.setId(item.getProductId());
             detailEntity.setProduct(productEntity);
             OrderEntity orderEntity = new OrderEntity();
             orderEntity.setId(orderId);
             detailEntity.setOrder(orderEntity);
             detailEntity.setQuantity(item.getQuantity());
             detailEntity.setTotalPrice(item.getPrice());
             orderDetailRepository.save(detailEntity);
        }
       
    }
}
