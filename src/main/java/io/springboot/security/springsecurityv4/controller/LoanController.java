package io.springboot.security.springsecurityv4.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class LoanController {

    @GetMapping("/my-loan")
    public String getLoanDetails(){

        log.info("LoanController:getLoanDetails");
        SecurityContextHolder.getContext()
                .getAuthentication()
                .getAuthorities()
                .forEach(grantedAuthority ->
                        System.out.println(grantedAuthority.getAuthority()));
        return "get account details from DB";
    }

}
