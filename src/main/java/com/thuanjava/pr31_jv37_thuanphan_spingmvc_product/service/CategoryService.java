/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thuanjava.pr31_jv37_thuanphan_spingmvc_product.service;


import com.thuanjava.pr31_jv37_thuanphan_spingmvc_product.entities.CategoryEntity;
import com.thuanjava.pr31_jv37_thuanphan_spingmvc_product.repository.CategoryRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author NO1
 */
@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    
    public List<CategoryEntity> getCategories(){
        return (List<CategoryEntity>)categoryRepository.findAll();
    }

    
}
