package com.soringboot.relationship.service;

import com.soringboot.relationship.Entity.ProductEntity;
import com.soringboot.relationship.dto.ProductDTO;
import com.soringboot.relationship.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public ProductDTO getProduct(Long number) {
        log.info("[getProduct] input number : {}", number);
        ProductEntity product = productRepository.findById(number).get();

        log.info("[getProduct] product number : {}, name : {}", product.getNumber(), product.getName());
        ProductDTO productResponseDTO = ProductDTO.toProductDTO(product);

        return productResponseDTO;
    }

    @Override
    public ProductDTO saveProduct(ProductDTO productDTO) {
        log.info("[savedProduct] productDTO : {}", productDTO.toString());
        ProductEntity product = new ProductEntity();
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setStock(productDTO.getStock());

        ProductEntity savedProduct = productRepository.save(product);
        log.info("[savedProduct] savedProduct : {}", savedProduct);

        ProductDTO productResponseDTO = ProductDTO.toProductDTO(savedProduct);

        return productResponseDTO;
    }

    @Override
    public ProductDTO changeProductName(Long number, String newName) throws Exception {
        Optional<ProductEntity> selectedProduct = productRepository.findById(number);

        ProductEntity updatedProduct;
        if (selectedProduct.isPresent()) {
            ProductEntity product = selectedProduct.get();

            product.setName(newName);

            updatedProduct = productRepository.save(product);
        } else {
            throw new Exception();
        }

        ProductDTO productResponseDTO = ProductDTO.toProductDTO(updatedProduct);
        return productResponseDTO;
    }

    @Override
    public void deleteProduct(Long number) throws Exception {
        Optional<ProductEntity> selectedProduct = productRepository.findById(number);

        if (selectedProduct.isPresent()) {
            ProductEntity product = selectedProduct.get();
            productRepository.delete(product);
        } else {
            throw new Exception();
        }
    }
}
