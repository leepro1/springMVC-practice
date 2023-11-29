package com.shop.controller;

import com.shop.dto.MemberFormDTO;
import com.shop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/member")
@Controller
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/new")
    public String memberForm(Model model){
        model.addAttribute("memberFormDTO",new MemberFormDTO());
        return "member/memberForm";
    }

    @PostMapping("/new")
    public String memberForm(MemberFormDTO memberFormDTO){
        memberService.saveMember(memberFormDTO);
        return "redirect:/";
    }

}
