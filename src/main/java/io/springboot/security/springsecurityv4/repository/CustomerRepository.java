package io.springboot.security.springsecurityv4.repository;

import io.springboot.security.springsecurityv4.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends CrudRepository< Customer,Long> {

    List<Customer> findByUsername(String username);
}
