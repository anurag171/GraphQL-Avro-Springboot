package com.anurag.spring.service.datafetecher;

import com.anurag.spring.repository.GeneratedCustomerDataRepository;
import com.anurag.spring.entity.Customer;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.stereotype.Component;

@Component
public class CustomerDataFetcher implements DataFetcher<Customer> {

    private final GeneratedCustomerDataRepository dataRepository;

    public CustomerDataFetcher(GeneratedCustomerDataRepository dataRepository){
        this.dataRepository = dataRepository;
    }
    @Override
    public Customer get(DataFetchingEnvironment dataFetchingEnvironment) throws Exception {
        String id = dataFetchingEnvironment.getArgument("id");
        return dataRepository.findById(id).orElse(null);
    }
}
