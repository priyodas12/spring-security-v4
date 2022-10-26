package io.springboot.security.springsecurityv4.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoanController {

    @GetMapping("/my-loan")
    public String getLoanDetails(){
        return "get account details from DB";
    }

}
