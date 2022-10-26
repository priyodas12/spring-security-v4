package io.springboot.security.springsecurityv4.service;

import io.springboot.security.springsecurityv4.model.Customer;
import io.springboot.security.springsecurityv4.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public Customer saveCustomer(Customer c){
        return customerRepository.save(c);
    }
}
