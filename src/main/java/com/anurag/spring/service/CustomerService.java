package com.anurag.spring.service;


import com.anurag.spring.repository.GeneratedCustomerDataRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CustomerService {

    /*@Value("classpath:customer.graphqls")
    Resource resource;*/


//    @Getter
//    private GraphQL graphQL;

   /* @Autowired
    private AllCustomerDataFetcher allCustomerDataFetcher;

    @Autowired
    private CustomerDataFetcher customerDataFetcher;*/



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

    @Autowired
    public CustomerService(GeneratedCustomerDataRepository dataRepository){
        this.dataRepository = dataRepository;
    }


   /* private RuntimeWiring buildRuntimeWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring -> typeWiring
                        .dataFetcher("allCustomers", allCustomerDataFetcher)
                        .dataFetcher("customer", customerDataFetcher)).
        build();
    }*/

}