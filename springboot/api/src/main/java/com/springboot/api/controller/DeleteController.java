package com.springboot.api.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/delete-api")
public class DeleteController {

    @DeleteMapping("/member/{id}")
    public String deleteMember(@PathVariable Long id) {
        return "id: " + id;
    }

    @DeleteMapping("/memberName")
    public String deleteMemberName(@RequestParam String name) {
        return "name: " + name;
    }
}
