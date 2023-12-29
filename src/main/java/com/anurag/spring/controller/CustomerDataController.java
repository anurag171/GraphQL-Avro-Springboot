package  com.anurag.spring.controller;


import com.anurag.spring.service.CustomerService;
import graphql.ExecutionResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/")
@Slf4j
public class CustomerDataController {

    private final CustomerService customerService;


    public CustomerDataController(CustomerService customerService){
        this.customerService = customerService;
    }

    @GetMapping("customers")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> getCustomers(@RequestBody String query){
       ExecutionResult executionResult = customerService.getGraphQL().execute(query);
      return new ResponseEntity<>(executionResult,HttpStatus.OK);
    }
}