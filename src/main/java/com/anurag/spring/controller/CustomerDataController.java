package  com.anurag.spring.controller;


import com.anurag.spring.dto.CustomerDto;
import com.anurag.spring.mapper.CustomerMapper;
import com.anurag.spring.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/")
public class CustomerDataController {

    private final CustomerService customerService;

    @Autowired
    private CustomerMapper customerMapper;


    public CustomerDataController(CustomerService customerService){
        this.customerService = customerService;
    }

    @GetMapping("customers")
    public List<CustomerDto> getCustomers(){
          return  customerMapper.modelsToDtos(customerService.getCustomers());
    }


}