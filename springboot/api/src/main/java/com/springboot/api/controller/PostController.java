package com.springboot.api.controller;

import com.springboot.api.dto.MemberDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/post-api")
public class PostController {

    @PostMapping("/member")
    public String postMember(@RequestBody Map<String, String> paramMap) {
        StringBuilder sb = new StringBuilder();
        paramMap.entrySet().forEach(map -> {
            sb.append(map.getKey() + ": " + map.getValue() + "\n");
        });

        return sb.toString();
    }

    @PostMapping("/member2")
    public String postMemberDTO(@RequestBody MemberDTO memberDTO) {
        return memberDTO.toString();
    }
}
