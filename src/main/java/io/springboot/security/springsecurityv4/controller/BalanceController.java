package io.springboot.security.springsecurityv4.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BalanceController {

    @GetMapping("/my-balance")
    public String getBalanceDetails(){
        return "get balances details from DB";
    }
}
