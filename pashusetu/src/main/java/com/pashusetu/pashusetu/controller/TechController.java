package com.pashusetu.pashusetu.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TechController {

    @GetMapping("/hello")
    public String hello(){
        return "Welcome to PashuSetu!";
    }
}
