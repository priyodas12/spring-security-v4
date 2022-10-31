package io.springboot.security.springsecurityv4.config;

import io.springboot.security.springsecurityv4.model.Authority;
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
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@Slf4j
public class CustomizedAuthenticationProvider implements AuthenticationProvider {

    private Pattern BCRYPT_PATTERN = Pattern.compile("\\A\\$2(a|y|b)?\\$(\\d\\d)\\$[./0-9A-Za-z]{53}");
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
            Matcher matcher = this.BCRYPT_PATTERN.matcher(customerFromDb.get(0).getPwd());
            if (!matcher.matches()) {
                log.info("Encoded password does not look like BCrypt");
            }
            if(passwordEncoder.matches(pwd,customerFromDb.get(0).getPwd())){
                log.info("Authentication Successful for :"+customerFromDb.get(0).getEmail());
                List<GrantedAuthority> authorityList=extractAuthorities(customerFromDb.get(0).getAuthorities());
                authorityList.forEach(System.out::println);
                return new UsernamePasswordAuthenticationToken(userName,pwd,authorityList);
            }else{
                throw new BadCredentialsException("wrong username/password provided!");
            }
        }else{
            throw new BadCredentialsException("No Such User Exists!");
        }
    }

    private List<GrantedAuthority> extractAuthorities(Set<Authority> authoritySet){
        log.info("Extracting Authorities...");
        List<GrantedAuthority> authorityList=new ArrayList<>();
        authoritySet.forEach(authority -> {
            authorityList.add(new SimpleGrantedAuthority(authority.getName()));
        });
        return authorityList;
    }
    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
