package com.unofficialnikke.camerastorejava.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello guys";
    }
}
