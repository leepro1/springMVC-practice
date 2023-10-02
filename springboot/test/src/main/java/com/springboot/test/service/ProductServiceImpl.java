package com.springboot.test.service;

import com.springboot.test.dto.ProductDTO;
import com.springboot.test.dto.ProductResponseDTO;
import com.springboot.test.entity.Product;
import com.springboot.test.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductResponseDTO getProduct(Long number) {
        log.info("[getProduct] input number : {}", number);
        Product product = productRepository.findById(number).get();

        log.info("[getProduct] product number : {}, name : {}", product.getNumber(), product.getName());
        ProductResponseDTO productResponseDTO = new ProductResponseDTO();
        productResponseDTO.setNumber(product.getNumber());
        productResponseDTO.setName(product.getName());
        productResponseDTO.setPrice(product.getPrice());
        productResponseDTO.setStock(product.getStock());

        return productResponseDTO;
    }

    @Override
    public ProductResponseDTO saveProduct(ProductDTO productDTO) {
        log.info("[savedProduct] productDTO : {}", productDTO.toString());
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setStock(productDTO.getStock());
        product.setCreateTime(LocalDateTime.now());
        product.setUpdateTime(LocalDateTime.now());

        Product savedProduct = productRepository.save(product);
        log.info("[savedProduct] savedProduct : {}", savedProduct);

        ProductResponseDTO productResponseDTO = new ProductResponseDTO();
        productResponseDTO.setNumber(savedProduct.getNumber());
        productResponseDTO.setName(savedProduct.getName());
        productResponseDTO.setPrice(savedProduct.getPrice());
        productResponseDTO.setStock(savedProduct.getStock());

        return productResponseDTO;
    }

    @Override
    public ProductResponseDTO changeProductName(Long number, String name) throws Exception {
        Product foundProduct = productRepository.findById(number).get();
        foundProduct.setName(name);
        Product changedProduct = productRepository.save(foundProduct);

        ProductResponseDTO productResponseDTO = new ProductResponseDTO();
        productResponseDTO.setNumber(changedProduct.getNumber());
        productResponseDTO.setName(changedProduct.getName());
        productResponseDTO.setPrice(changedProduct.getPrice());
        productResponseDTO.setStock(changedProduct.getStock());

        return productResponseDTO;
    }

    @Override
    public void deleteProduct(Long number) throws Exception {
        productRepository.deleteById(number);
    }
}
