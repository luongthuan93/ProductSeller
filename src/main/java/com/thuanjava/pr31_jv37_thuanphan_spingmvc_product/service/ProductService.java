/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thuanjava.pr31_jv37_thuanphan_spingmvc_product.service;


import com.thuanjava.pr31_jv37_thuanphan_spingmvc_product.entities.ImageEntity;
import com.thuanjava.pr31_jv37_thuanphan_spingmvc_product.entities.ProductEntity;
import com.thuanjava.pr31_jv37_thuanphan_spingmvc_product.repository.ProductRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 *
 * @author NO1
 */
@Service
public class ProductService {
    
    @Autowired
    ProductRepository productRepository;
    public void saveAll(List<ProductEntity> products) {
        productRepository.saveAll(products);
    }

    public void save(ProductEntity product,String action) {
        
        if(action.equalsIgnoreCase("add")){
            List<ImageEntity> images = product.getImages();
            for(ImageEntity image:images){
                image.setProduct(product);
                product.setImages(images);
            }            
        }
        
        productRepository.save(product);
        
        
    }
    
    
    public List<ProductEntity> getProducts() {
        return (List<ProductEntity>) productRepository.findAllProductsQuery();
    }
    
    public ProductEntity getProduct(int id) {
        Optional<ProductEntity> product = productRepository.findProductByIdQuery(id);
        if (product.isPresent()) {
            return product.get();
        } else {
            return new ProductEntity();
        }
    }

    public boolean delete(int id) {
        productRepository.deleteById(id);
        return productRepository.existsById(id);
    }
}
