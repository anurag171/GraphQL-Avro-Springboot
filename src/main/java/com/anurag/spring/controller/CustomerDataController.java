package  com.anurag.spring.controller;


import com.anurag.spring.repository.GeneratedCustomerDataRepository;
import com.anurag.spring.service.CustomerService;
import graphql.ExecutionResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/v1/")
@Slf4j
public class CustomerDataController {

    private final GeneratedCustomerDataRepository dataRepository;


    public CustomerDataController(GeneratedCustomerDataRepository dataRepository){
        this.dataRepository = dataRepository;
    }

    @QueryMapping
    public Object allCustomers(@Argument String query){
        return dataRepository.findAll();
    }

    @QueryMapping
    public Object customer(@Argument String id){
        return dataRepository.findById(id);
    }
}