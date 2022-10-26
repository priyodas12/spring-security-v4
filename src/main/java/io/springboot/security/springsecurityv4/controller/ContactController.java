package io.springboot.security.springsecurityv4.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactController {

    @GetMapping("/contact-us")
    public String getContactDetails(){
        return "contact us page";
    }
}
