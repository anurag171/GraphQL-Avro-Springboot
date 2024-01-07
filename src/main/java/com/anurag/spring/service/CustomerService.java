package com.anurag.spring.service;


import com.anurag.spring.service.mutation.BalanceMutation;
import com.anurag.spring.repository.GeneratedCustomerDataRepository;
import com.anurag.spring.service.datafetecher.AllCustomerDataFetcher;
import com.anurag.spring.service.datafetecher.CustomerDataFetcher;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
@Slf4j
public class CustomerService {

    @Value("classpath:customer.graphqls")
    Resource resource;


//    @Getter
//    private GraphQL graphQL;

    @Autowired
    private AllCustomerDataFetcher allCustomerDataFetcher;

    @Autowired
    private CustomerDataFetcher customerDataFetcher;

    @Autowired
    private BalanceMutation balanceMutation;

    private final GeneratedCustomerDataRepository dataRepository;

  /*  @PostConstruct
    private void loadSchema() throws IOException {
        log.info("Entering loadSchema@GraphQLService");
        //Get the graphql file
        File file = resource.getFile();
        //Parse SchemaF
        TypeDefinitionRegistry typeDefinitionRegistry = new SchemaParser().parse(file);
        RuntimeWiring runtimeWiring = buildRuntimeWiring();
        GraphQLSchema graphQLSchema = new SchemaGenerator().makeExecutableSchema(typeDefinitionRegistry, runtimeWiring);
        graphQL = GraphQL.newGraphQL(graphQLSchema).build();
    }*/


    public CustomerService(GeneratedCustomerDataRepository dataRepository){
        this.dataRepository = dataRepository;
    }


    private RuntimeWiring buildRuntimeWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring -> typeWiring
                        .dataFetcher("allCustomers", allCustomerDataFetcher)
                        .dataFetcher("customer", customerDataFetcher)).
        build();
    }

}