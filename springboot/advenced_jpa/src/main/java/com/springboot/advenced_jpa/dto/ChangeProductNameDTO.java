package com.springboot.advenced_jpa.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChangeProductNameDTO {

    private Long number;
    private String name;

    public ChangeProductNameDTO(Long number, String name) {
        this.number = number;
        this.name = name;
    }
}
