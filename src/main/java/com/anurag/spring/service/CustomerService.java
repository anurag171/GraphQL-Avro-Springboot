package com.anurag.spring.service;


import com.anurag.spring.data.GeneratedCustomerDataRepository;
import com.anurag.spring.entity.Customer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CustomerService {

    private final GeneratedCustomerDataRepository dataRepository;


    public CustomerService(GeneratedCustomerDataRepository dataRepository){
        this.dataRepository = dataRepository;
    }
    public List<Customer> getCustomers() {
      return   StreamSupport
              .stream(dataRepository.findAll().spliterator(), false)
              .collect(Collectors.toList());

      // return Arrays.asList().);
    }
}