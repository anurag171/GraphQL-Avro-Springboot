package com.anurag.spring.service.datafetecher;

import com.anurag.spring.repository.GeneratedCustomerDataRepository;
import com.anurag.spring.dto.CustomerDto;
import com.anurag.spring.mapper.CustomerMapper;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class AllCustomerDataFetcher implements DataFetcher<List<CustomerDto>> {

    private final GeneratedCustomerDataRepository dataRepository;

    CustomerMapper customerMapper;

    public AllCustomerDataFetcher(GeneratedCustomerDataRepository dataRepository,CustomerMapper customerMapper){
        this.dataRepository = dataRepository;
        this.customerMapper =  customerMapper;
    }
    @Override
    public List<CustomerDto> get(DataFetchingEnvironment dataFetchingEnvironment) throws Exception {

        return customerMapper.modelsToDtos(dataRepository.findAll());
    }
}
