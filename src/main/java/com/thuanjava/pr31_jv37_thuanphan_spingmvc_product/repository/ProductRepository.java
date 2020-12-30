/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thuanjava.pr31_jv37_thuanphan_spingmvc_product.repository;


import com.thuanjava.pr31_jv37_thuanphan_spingmvc_product.entities.ProductEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author NO1
 */
@Repository
public interface ProductRepository extends CrudRepository<ProductEntity, Integer>{
    
    @Query("SELECT DISTINCT p FROM ProductEntity p LEFT JOIN FETCH p.images")
    List<ProductEntity> findAllProductsQuery();
    
    @Query("SELECT DISTINCT p FROM ProductEntity p LEFT JOIN FETCH p.images WHERE p.id = ?1")
    Optional<ProductEntity> findProductByIdQuery(int id);
    
}
