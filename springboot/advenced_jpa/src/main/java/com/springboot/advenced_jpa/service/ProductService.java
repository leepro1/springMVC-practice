package com.springboot.advenced_jpa.service;

import com.springboot.advenced_jpa.dto.ProductDTO;
import com.springboot.advenced_jpa.dto.ProductResponseDTO;

public interface ProductService {

    ProductResponseDTO getProduct(Long number);

    ProductResponseDTO saveProduct(ProductDTO productDTO);

    ProductResponseDTO changeProductName(Long number, String name) throws Exception;

    void deleteProduct(Long number) throws Exception;
}
