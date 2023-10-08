package com.springboot.advenced_jpa.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
public class ProductDTO {

    private String name;
    private int price;
    private int stock;

    public ProductDTO(String name, int price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }
}
