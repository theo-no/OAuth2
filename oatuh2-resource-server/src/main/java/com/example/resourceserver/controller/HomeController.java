package com.example.resourceserver.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/hello")
    public Authentication hello() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

}
