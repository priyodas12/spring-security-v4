package io.springboot.security.springsecurityv4.controller;

import io.springboot.security.springsecurityv4.model.Customer;
import io.springboot.security.springsecurityv4.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class CustomerRegController {

    @Autowired
    CustomerService customerService;

    @PostMapping("/register/customer")
    public ResponseEntity<Customer> saveCustomerDetails(@RequestBody Customer customer){
        Customer customer1=customerService.saveCustomer(customer);
        try{
            if(customer1.getCustomer_id()>0){
            return new ResponseEntity<>(customer1,HttpStatus.CREATED);
            }
        }catch(Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return null;
    }
}
