package io.springboot.security.springsecurityv4.config;

import io.springboot.security.springsecurityv4.model.Customer;
import io.springboot.security.springsecurityv4.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*Through Custom UserDetailsService and custom table and JPA standard*/
@Service
@Slf4j
public class CustomerUserDetails implements UserDetailsService{

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String userName,password=null;
        List<GrantedAuthority> authorityList=null;
        List<Customer> customerList=customerRepository.findByUsername(username);

        if(CollectionUtils.isEmpty(customerList)){
            log.info(username+" Not found in Database! Try with different userName.");
            throw new UsernameNotFoundException(username+" Not found in Database! Try with different userName.");
        }else{
            log.info(username+" found in Database!");
            userName=customerList.get(0).getUsername();
            password=customerList.get(0).getPwd();
            authorityList=new ArrayList<>(Collections.singleton(new SimpleGrantedAuthority(customerList.get(0).getRole())));
        }
        return new User(userName,password,authorityList);
    }
}
