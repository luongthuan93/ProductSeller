/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thuanjava.pr31_jv37_thuanphan_spingmvc_product.repository;

import com.thuanjava.pr31_jv37_thuanphan_spingmvc_product.entities.OrderDetailEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author NO1
 */
@Repository
public interface OrderDetailRepository extends CrudRepository<OrderDetailEntity, Integer> {
    
}
