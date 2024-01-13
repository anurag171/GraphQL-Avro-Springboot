package com.anurag.spring.controller;


import com.anurag.spring.constant.MutationOperator;
import com.anurag.spring.dto.BalanceRequest;
import com.anurag.spring.dto.BalanceResponseDto;
import com.anurag.spring.service.CustomerAccountBalanceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("v1/")
public class CustomerAccountBalanceController {

    private final CustomerAccountBalanceService customerAccountBalanceService;

    public CustomerAccountBalanceController(CustomerAccountBalanceService customerAccountBalanceService){
        this.customerAccountBalanceService = customerAccountBalanceService;
    }

   @SchemaMapping(typeName = "Mutation", field = "operateBalance")
    public BalanceResponseDto operateBalance(@Argument BalanceRequest balanceRequest){

        log.info(" Received request [{}]", balanceRequest.toString());
       MutationOperator operator = MutationOperator.getMutationOperator(balanceRequest.operator());
        switch (operator){
            case ADD -> {
                return customerAccountBalanceService.deposit(balanceRequest);
            }
            case SUBTRACT -> {
                return customerAccountBalanceService.withdraw(balanceRequest);
            }
            default -> {
                return BalanceResponseDto.builder().build();
            }
        }
    }
}
