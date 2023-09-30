package com.springboot.jpa.service;

import com.springboot.jpa.dao.ProductDAO;
import com.springboot.jpa.dto.ProductDTO;
import com.springboot.jpa.dto.ProductResponseDTO;
import com.springboot.jpa.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductDAO productDAO;

    @Autowired
    public ProductServiceImpl(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @Override
    public ProductResponseDTO getProduct(Long number) {
        Product product = productDAO.selectProduct(number);

        ProductResponseDTO productResponseDTO = new ProductResponseDTO();
        productResponseDTO.setNumber(product.getNumber());
        productResponseDTO.setName(product.getName());
        productResponseDTO.setPrice(product.getPrice());
        productResponseDTO.setStock(product.getStock());

        return productResponseDTO;
    }

    @Override
    public ProductResponseDTO saveProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setStock(productDTO.getStock());
        product.setCreateTime(LocalDateTime.now());
        product.setUpdateTime(LocalDateTime.now());

        Product savedProduct = productDAO.insertProduct(product);

        ProductResponseDTO productResponseDTO = new ProductResponseDTO();
        productResponseDTO.setNumber(savedProduct.getNumber());
        productResponseDTO.setName(savedProduct.getName());
        productResponseDTO.setPrice(savedProduct.getPrice());
        productResponseDTO.setStock(savedProduct.getStock());

        return productResponseDTO;
    }

    @Override
    public ProductResponseDTO changeProductName(Long number, String name) throws Exception {
        Product changedProduct = productDAO.updateProductName(number, name);

        ProductResponseDTO productResponseDTO = new ProductResponseDTO();
        productResponseDTO.setNumber(changedProduct.getNumber());
        productResponseDTO.setName(changedProduct.getName());
        productResponseDTO.setPrice(changedProduct.getPrice());
        productResponseDTO.setStock(changedProduct.getStock());

        return productResponseDTO;
    }

    @Override
    public void deleteProduct(Long number) throws Exception {
        productDAO.deleteProduct(number);
    }
}
