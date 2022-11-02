package io.springboot.security.springsecurityv4.controller;

import io.springboot.security.springsecurityv4.model.JwtToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Slf4j
public class LoginController {

    @GetMapping("/jwt/token")
    public String getLoginDetails(){

        log.info("LoginController:getLoginDetails");
        String loggedUser=SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

     return loggedUser;
    }
}
