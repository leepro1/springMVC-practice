package com.springboot.jpa.dao;

import com.springboot.jpa.entity.Product;

public interface ProductDAO {

    Product insertProduct(Product product);

    Product selectProduct(Long id);

    Product updateProductName(Long number, String name) throws Exception;

    void deleteProduct(Long number) throws Exception;
}
