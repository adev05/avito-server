package com.avito.notification.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin(origins = "localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class HomeController {

    @GetMapping("/")
    public String helloWorld() {
        return "Hello, World!";
    }

    

}