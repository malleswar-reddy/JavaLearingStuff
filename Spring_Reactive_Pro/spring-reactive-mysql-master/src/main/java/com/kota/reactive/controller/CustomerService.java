package com.kota.reactive.controller;

import com.kota.reactive.entity.Customer;
import com.kota.reactive.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class CustomerService {

    @Autowired
    private final CustomerRepository customerRepository;
    public Mono< Customer > save(Customer customer) {

       return customerRepository.save(customer);
    }
}
