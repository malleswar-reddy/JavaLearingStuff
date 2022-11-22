package com.codeing.handler;

import com.codeing.dao.CustomerDao;
import com.codeing.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerHandler {


    @Autowired
    private CustomerDao customerDao;

    public Mono< ServerResponse > loadCustomer(ServerRequest serverRequest) {
        final Flux< Customer > customers = customerDao.getCustomer();

        return ServerResponse.ok().body(customers, Customer.class);
    }

    public Mono< ServerResponse > loadCustomerStream(ServerRequest serverRequest) {
        final Flux< Customer > customerListStream = customerDao.getCustomerListStream();
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(customerListStream, Customer.class);
    }

    public Mono< ServerResponse > loadCustomerFindById(ServerRequest serverRequest) {
        final Integer customerId = Integer.valueOf(serverRequest.pathVariable("id"));
        customerDao.getCustomer().filter(e -> e.getId() == customerId).next();
        final Mono< Customer > singleCustomer = customerDao.getCustomer()
                .filter(e -> e.getId() == customerId).take(1).single();

        return ServerResponse.ok().body(singleCustomer, Customer.class);
    }

    public Mono< ServerResponse > saveCustomer(ServerRequest serverRequest) {
        Mono< Customer > customerMono = serverRequest.bodyToMono(Customer.class);
        Mono< String > customerStr = customerMono.map(customer -> customer.getId() + " : " + customer.getCustomerName());
        return ServerResponse.ok().body(customerStr, String.class);
    }
}
