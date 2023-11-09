package com.springboot.advenced_jpa.entity;

import com.springboot.advenced_jpa.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Autowired
    ProductRepository productRepository;

    @Test
    public void auditingTest(){
        Product product = new Product();
        product.setName("pen");
        product.setPrice(1000);
        product.setStock(100);

        Product savedProduct = productRepository.save(product);

        System.out.println("name : "+savedProduct.getName());
        System.out.println("createdAt: "+savedProduct.getCreatedAt());
    }
}