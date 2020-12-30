/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thuanjava.pr31_jv37_thuanphan_spingmvc_product.service;


import com.thuanjava.pr31_jv37_thuanphan_spingmvc_product.entities.ImageEntity;
import com.thuanjava.pr31_jv37_thuanphan_spingmvc_product.repository.ImageRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author NO1
 */
@Service
public class ImageService {
    
    @Autowired
    private ImageRepository imageRepository;
    
    public void save(ImageEntity imageEntity){
        imageRepository.save(imageEntity);
    }
    
    public ImageEntity findById(int id){
        Optional<ImageEntity> image = imageRepository.findById(id);
        if (image.isPresent()) {
            return image.get();
        } else {
            return new ImageEntity();
        }
    }
}
