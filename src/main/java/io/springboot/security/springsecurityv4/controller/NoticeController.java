package io.springboot.security.springsecurityv4.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoticeController {
    @GetMapping("/public-notice")
    public String getNoticeDetails(){
        return "get notices details from DB";
    }
}
