package com.springboot.api.controller;

import com.springboot.api.dto.MemberDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/put-api")
public class PutController {

    @PutMapping("/member")
    public String putMember(@RequestParam Map<String, String> paramMap) {
        StringBuilder sb = new StringBuilder();
        paramMap.entrySet().forEach(map -> {
            sb.append(map.getKey() + ": " + map.getValue() + "\n");
        });

        return sb.toString();
    }

    @PutMapping("/member2")
    public String putMember2(@RequestBody MemberDTO memberDTO) {
        return memberDTO.toString();
    }

    @PutMapping("/member3")
    public MemberDTO putMember3(@RequestBody MemberDTO memberDTO) {
        return memberDTO;
    }

    @PutMapping("/member4")
    public ResponseEntity<MemberDTO> putMember4(@RequestBody MemberDTO memberDTO) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(memberDTO);
    }
}
