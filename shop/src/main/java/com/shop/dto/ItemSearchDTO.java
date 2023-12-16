package com.shop.dto;

import com.shop.constant.ItemSellStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemSearchDTO {

    private String searchDateType; //현재 시간과 상품 등록일을 비교하여 시간 기준 조회

    private ItemSellStatus searchSellStatus; //판매 상태 기준

    private String searchBy; //조회 기준

    private String searchQuery = ""; //조회 기준에 따른 쿼리
}
