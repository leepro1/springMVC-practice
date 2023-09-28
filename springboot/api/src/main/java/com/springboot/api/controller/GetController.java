package com.springboot.api.controller;

import com.springboot.api.dto.MemberDTO;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/get-api")
public class GetController {

    @GetMapping("/name")
    public String getName() {
        return "Lee";
    }

    @GetMapping("/id/{id}")
    public String getId(@PathVariable Long id) {
        return "id: " + id;
    }

    @GetMapping("/request1")
    public String getRequestParam1(@RequestParam String name, @RequestParam Long id) {
        return "name: " + name + " id: " + id;
    }

    @GetMapping("/request2")
    public String getRequestParam2(@RequestParam Map<String, String> paramMap) {
        StringBuilder sb = new StringBuilder();
        paramMap.entrySet().forEach(map -> {
            sb.append(map.getKey() + ": " + map.getValue() + "\n");
        });

        return sb.toString();
    }

    @GetMapping("/request3")
    public String getRequestParam3(MemberDTO memberDTO) {
        return memberDTO.toString();
    }
}
