package com.soringboot.relationship.controller;

import com.soringboot.relationship.dto.ProductDTO;
import com.soringboot.relationship.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    @GetMapping()
    public ResponseEntity<ProductDTO> getProduct(Long number) {
        ProductDTO productResponseDTO = productService.getProduct(number);

        return ResponseEntity.status(HttpStatus.OK).body(productResponseDTO);
    }

    @PostMapping()
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {
        ProductDTO productResponseDTO = productService.saveProduct(productDTO);

        return ResponseEntity.status(HttpStatus.OK).body(productResponseDTO);
    }

    @PutMapping()
    public ResponseEntity<ProductDTO> changeProductName(@RequestParam Long number, String name) throws Exception {
        ProductDTO productResponseDTO = productService.changeProductName(number, name);

        return ResponseEntity.status(HttpStatus.OK).body(productResponseDTO);
    }

    @DeleteMapping()
    public ResponseEntity<String> deleteProduct(Long number) throws Exception {
        productService.deleteProduct(number);

        return ResponseEntity.status(HttpStatus.OK).body("정상적으로 삭제되었습니다.");
    }
}
