package com.codeing.dao;

import com.codeing.model.Customer;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class CustomerDao {

    private static void sleepExcetion(int i) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public List< Customer > getCustomerList() {

        List< Customer > customerList = IntStream.rangeClosed(1, 10)
                .peek(CustomerDao::sleepExcetion)
                .peek(e -> System.out.println("Processing count " + e))
                .mapToObj(i -> new Customer(i, "Customer" + i))
                .collect(Collectors.toList());

        return customerList;
    }

    public Flux< Customer > getCustomerListStream() {

        Flux< Customer > customerList = Flux.range(1, 50)
                .doOnNext(e -> System.out.println("Processing steam flow " + e))
                .delayElements(Duration.ofSeconds(2))
                .map(i -> new Customer(i, "Customer" + i));
        return customerList;
    }

    public Flux< Customer > getCustomer() {

        Flux< Customer > customerList = Flux.range(1, 50)
                .doOnNext(e -> System.out.println("Processing steam flow " + e))
                .map(i -> new Customer(i, "Customer" + i));
        return customerList;
    }
}
