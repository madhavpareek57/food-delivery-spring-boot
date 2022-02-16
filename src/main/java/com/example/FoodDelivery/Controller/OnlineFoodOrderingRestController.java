package com.example.FoodDelivery.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class OnlineFoodOrderingRestController {
    @GetMapping("/")
    public String hello() {
        return "Hello World!";
    }
}
