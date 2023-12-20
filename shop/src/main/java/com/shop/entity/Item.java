package com.shop.entity;

import com.shop.constant.ItemSellStatus;
import com.shop.dto.ItemFormDTO;
import com.shop.exception.OutOfStockException;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "item")
public class Item extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private int stock;

    @Lob
    @Column(nullable = false)
    private String itemDetail;

    @Enumerated(EnumType.STRING)
    private ItemSellStatus itemSellStatus;

    public void updateItem(ItemFormDTO itemFormDTO) {
        this.name = itemFormDTO.getName();
        this.price = itemFormDTO.getPrice();
        this.stock = itemFormDTO.getStock();
        this.itemDetail = itemFormDTO.getItemDetail();
        this.itemSellStatus = itemFormDTO.getItemSellStatus();
    }

    public void removeStock(int stock) {
        int restStock = this.stock - stock;
        if (restStock < 0) {
            throw new OutOfStockException("상품의 재고가 부족합니다. (현재 재고 수향: " + this.stock + ")");
        }
        this.stock = restStock;
    }
}