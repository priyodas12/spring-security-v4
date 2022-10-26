package io.springboot.security.springsecurityv4.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Slf4j
public class BaseController {

    @GetMapping("/basic")
    public String webEndPoint(){
        log.info("BaseController:webEndPoint>>");
        return "Welcome to spring security!";
    }
}
