package com.shop.controller;

import com.shop.dto.ItemDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/thymeleaf")
public class ThymeleafController {

    @GetMapping("/ex01")
    public String thymeleafEx01(Model model) {
        model.addAttribute("data", "타임리프 테스트입니다.");
        return "thymeleafEx/ex01";
    }

    @GetMapping("/ex02")
    public String thymeleafEx02(Model model) {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setName("테스트 상품01");
        itemDTO.setPrice(1000);
        itemDTO.setItemDetail("테스트 상품입니다.");
        itemDTO.setCreateTime(LocalDateTime.now());

        model.addAttribute("itemDTO", itemDTO);
        return "thymeleafEx/ex02";
    }

    @GetMapping("/ex03")
    public String thymeleafEx03(Model model) {
        List<ItemDTO> itemDTOList = new ArrayList<ItemDTO>();

        for (int i = 1; i <= 10; i++) {
            ItemDTO itemDTO = new ItemDTO();
            itemDTO.setName("테스트 상품" + i);
            itemDTO.setPrice(1000 + i);
            itemDTO.setItemDetail("테스트" + i + "상품입니다.");
            itemDTO.setCreateTime(LocalDateTime.now());

            itemDTOList.add(itemDTO);
        }

        model.addAttribute("itemDTOList", itemDTOList);
        return "thymeleafEx/ex03";
    }

    @GetMapping("/ex04")
    public String thymeleafEx04(Model model) {
        List<ItemDTO> itemDTOList = new ArrayList<ItemDTO>();

        for (int i = 1; i <= 10; i++) {
            ItemDTO itemDTO = new ItemDTO();
            itemDTO.setName("테스트 상품" + i);
            itemDTO.setPrice(1000 + i);
            itemDTO.setItemDetail("테스트" + i + "상품입니다.");
            itemDTO.setCreateTime(LocalDateTime.now());

            itemDTOList.add(itemDTO);
        }

        model.addAttribute("itemDTOList", itemDTOList);
        return "thymeleafEx/ex04";
    }

    @GetMapping("/ex05")
    public String thymeleafEx05() {
        return "thymeleafEx/ex05";
    }
}
