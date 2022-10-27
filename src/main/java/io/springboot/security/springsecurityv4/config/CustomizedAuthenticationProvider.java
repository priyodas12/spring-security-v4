package io.springboot.security.springsecurityv4.config;

import io.springboot.security.springsecurityv4.model.Customer;
import io.springboot.security.springsecurityv4.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class CustomizedAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        log.info("CustomizedAuthenticationProvider starts authentication...");
        String userName= authentication.getName();
        String pwd=authentication.getCredentials().toString();

        List<Customer> customerFromDb=customerRepository.findByUsername(userName);
        if(!CollectionUtils.isEmpty(customerFromDb)){
            if(passwordEncoder.matches(customerFromDb.get(0).getPwd(),pwd)){
                log.info("Authentication Successful!");
                List<GrantedAuthority> authorityList=new ArrayList<>();
                authorityList.add(new SimpleGrantedAuthority(customerFromDb.get(0).getRole()));
                return new UsernamePasswordAuthenticationToken(userName,pwd,authorityList);
            }else{
                throw new BadCredentialsException("wrong username/password provided!");
            }
        }else{
            throw new BadCredentialsException("No Such User Exists!");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
