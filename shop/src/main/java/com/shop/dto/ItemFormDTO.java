package com.shop.dto;

import com.shop.constant.ItemSellStatus;
import com.shop.entity.Item;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ItemFormDTO {

    private Long id;

    @NotBlank(message = "상품명은 필수 입력 값입니다.")
    private String name;

    @NotBlank(message = "상품 설명은 필수 입력 값입니다.")
    private String itemDetail;

    @NotNull(message = "재고는 필수 입력 값입니다.")
    private Integer stock;

    @NotNull(message = "가격은 필수 입력 값입니다.")
    private Integer price;

    private ItemSellStatus itemSellStatus;

    //상품 수정 시 이미지 정보들을 저장하는 리스트
    private List<ItemImgDTO> itemImgDTOList = new ArrayList<>();

    //상품 수정 시 이미지 아이디들을 저장하는 리스트
    private List<Long> itemImgIds = new ArrayList<>();

    private static ModelMapper modelMapper = new ModelMapper();

    //생성 시
    public Item createItem() {
        return modelMapper.map(this, Item.class);
    }

    //조회 시
    public static ItemFormDTO of(Item item) {
        return modelMapper.map(item, ItemFormDTO.class);
    }
}
