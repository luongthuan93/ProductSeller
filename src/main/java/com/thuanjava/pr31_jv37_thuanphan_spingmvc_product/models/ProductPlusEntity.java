/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thuanjava.pr31_jv37_thuanphan_spingmvc_product.models;

import com.thuanjava.pr31_jv37_thuanphan_spingmvc_product.entities.ProductEntity;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author NO1
 */

public class ProductPlusEntity {
    
    @Autowired
    private ProductEntity product;
    private MultipartFile file;

    
    
    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

 
    
    
}
