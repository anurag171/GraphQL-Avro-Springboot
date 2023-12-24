package com.anurag.spring.service;


import com.anurag.spring.data.GeneratedCustomerDataRepository;
import com.anurag.spring.entity.Customer;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final GeneratedCustomerDataRepository dataRepository;


    public CustomerService(GeneratedCustomerDataRepository dataRepository){
        this.dataRepository = dataRepository;
    }
    public Iterable<Customer> getCustomers() {

      return   dataRepository.findAll();

      // return Arrays.asList().);
    }
}