package io.springboot.security.springsecurityv4.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CardController {

    @GetMapping("/my-card")
    public String getCardDetails(){
        return "get cards details from DB";
    }
}
