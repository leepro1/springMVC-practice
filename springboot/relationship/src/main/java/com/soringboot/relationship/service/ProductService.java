package com.soringboot.relationship.service;

import com.soringboot.relationship.dto.ProductDTO;

public interface ProductService {

    ProductDTO getProduct(Long number);
    ProductDTO saveProduct(ProductDTO productDTO);
    ProductDTO changeProductName(Long number, String newName) throws Exception;
    void deleteProduct(Long number) throws Exception;

}
