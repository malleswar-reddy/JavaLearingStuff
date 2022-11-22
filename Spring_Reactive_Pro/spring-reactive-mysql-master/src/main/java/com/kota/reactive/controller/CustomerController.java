package com.kota.reactive.controller;

import com.kota.reactive.entity.Customer;
import com.kota.reactive.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class CustomerController {

    private CustomerRepository customerRepository;
    private  CustomerService customerService;

    @PostMapping("/savecustomer")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono< Customer > createCustomer(@RequestBody Customer customer) {


        return customerService.save(customer);
    }

    @GetMapping("/")
    public Flux<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    @GetMapping("/customer")
    public Mono<Customer> getCustomer(Long id) {
        return customerRepository.findById(id);
    }

    @PutMapping("/")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Mono<Customer> updateCustomer(@RequestBody Customer customer) {
        return this.customerRepository
                .findById(customer.getId())
                .map(c->customer)
                .flatMap(this.customerRepository::save);
    }

    @DeleteMapping("/")
    public Mono<Customer> delete(Long id) {
        return this.customerRepository
                .findById(id)
                .flatMap(customer -> this.customerRepository.deleteById(customer.getId()).thenReturn(customer));
    }
}
