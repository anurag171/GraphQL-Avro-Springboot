package com.anurag.spring.service.datafetecher;

import com.anurag.spring.data.GeneratedCustomerDataRepository;
import com.anurag.spring.entity.Customer;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class AllCustomerDataFetcher implements DataFetcher<List<Customer>> {

    private final GeneratedCustomerDataRepository dataRepository;

    public AllCustomerDataFetcher(GeneratedCustomerDataRepository dataRepository){
        this.dataRepository = dataRepository;
    }
    @Override
    public List<Customer> get(DataFetchingEnvironment dataFetchingEnvironment) throws Exception {

        return dataRepository.findAll();
    }
}
