package  com.anurag.spring.controller;


import com.anurag.spring.repository.GeneratedCustomerDataRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/v1/")
@Slf4j
public class CustomerDataController {

    private final GeneratedCustomerDataRepository dataRepository;


    @Autowired
    public CustomerDataController(GeneratedCustomerDataRepository dataRepository){
        this.dataRepository = dataRepository;
    }

    @QueryMapping
    public Object allCustomers(){
        return dataRepository.findAll();
    }

    @QueryMapping
    public Object customer(@Argument String id){
        return dataRepository.findById(id);
    }
}