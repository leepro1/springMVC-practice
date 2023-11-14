package com.soringboot.relationship.dto;

import com.soringboot.relationship.Entity.ProductEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Long number;
    private String name;
    private int price;
    private int stock;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;

    public static ProductDTO toProductDTO(ProductEntity productEntity) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setNumber(productEntity.getNumber());
        productDTO.setName(productEntity.getName());
        productDTO.setPrice(productEntity.getPrice());
        productDTO.setStock(productEntity.getStock());
        productDTO.setCreatedTime(productEntity.getCreatedTime());
        productDTO.setUpdatedTime(productEntity.getUpdatedTime());

        return productDTO;
    }
}
