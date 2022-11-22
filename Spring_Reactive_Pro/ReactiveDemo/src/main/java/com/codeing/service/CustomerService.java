package com.codeing.service;

import com.codeing.dao.CustomerDao;
import com.codeing.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerDao customerDao;

    public List< Customer > getAllCustomers() {
        Long sartTime = System.currentTimeMillis();
        final List< Customer > customerList = customerDao.getCustomerList();
        Long endTime = System.currentTimeMillis();
        System.out.println("Total Execution Time : " + (endTime - sartTime));
        return customerList;
    }


    public Flux< Customer > getAllCustomersStrem() {
        Long sartTime = System.currentTimeMillis();
        final Flux< Customer > customerList = customerDao.getCustomerListStream();
        Long endTime = System.currentTimeMillis();
        System.out.println("Total Execution Time : " + (endTime - sartTime));
        return customerList;
    }
}
