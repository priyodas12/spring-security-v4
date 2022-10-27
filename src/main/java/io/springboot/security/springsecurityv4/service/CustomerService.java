package io.springboot.security.springsecurityv4.service;

import io.springboot.security.springsecurityv4.model.Customer;
import io.springboot.security.springsecurityv4.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public Customer saveCustomer(Customer c){
        String hashPwd=passwordEncoder.encode(c.getPwd());

        log.info("Encoded PWD :{} , length: {}",hashPwd,hashPwd.length());
        c.setPwd(hashPwd);
        return customerRepository.save(c);
    }
}
