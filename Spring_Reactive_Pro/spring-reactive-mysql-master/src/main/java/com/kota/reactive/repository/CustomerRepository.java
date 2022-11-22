package com.kota.reactive.repository;

import com.kota.reactive.entity.Customer;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface CustomerRepository extends ReactiveCrudRepository< Customer, Long> {
}
