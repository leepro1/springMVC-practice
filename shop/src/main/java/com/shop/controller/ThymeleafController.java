package com.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/thymeleaf")
public class ThymeleafController {

    @GetMapping("/ex01")
    public String thymeleafEx01(Model model){
        model.addAttribute("data","타임리프 테스트입니다.");
        return "thymeleafEx/ex01";
    }

}
