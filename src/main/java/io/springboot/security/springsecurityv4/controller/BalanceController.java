package io.springboot.security.springsecurityv4.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class BalanceController {

    @GetMapping("/my-balance")
    public String getBalanceDetails(){
        log.info("BalanceController:getBalanceDetails");
        SecurityContextHolder.getContext()
                .getAuthentication()
                .getAuthorities()
                .forEach(grantedAuthority ->
                        System.out.println(grantedAuthority.getAuthority()));
        return "get balances details from DB";
    }
}
