package com.shop.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ItemDTO {

    private Long id;
    private String name;
    private Integer price;
    private String itemDetail;
    private String itemSellStatus;

    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
