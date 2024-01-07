package com.anurag.spring.service.mutation;

import com.anurag.spring.constant.MutationOperator;
import com.anurag.spring.dto.BalanceRequest;
import com.anurag.spring.repository.GeneratedCustomerAccountDataRepository;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
@Slf4j
public class BalanceMutation implements GraphQLMutationResolver {

    GeneratedCustomerAccountDataRepository generatedCustomerAccountDataRepository;
    public BalanceMutation(GeneratedCustomerAccountDataRepository generatedCustomerAccountDataRepository){
        this.generatedCustomerAccountDataRepository =  generatedCustomerAccountDataRepository;
    }

    @Transactional
    public Double operateBalance(BalanceRequest balanceRequest) throws Exception {

        MutationOperator operator = MutationOperator.valueOf(balanceRequest.operator());

        switch (operator){
           case ADD -> addToCurrentBalance(balanceRequest);
            case SUBTRACT -> subtractToCurrentBalance(balanceRequest);
            default -> throw InvalidOperator(balanceRequest.operator());
        }

        balanceRequest.amount();
        return Double.MAX_VALUE;
    }

    private Exception InvalidOperator(String operator) throws Exception {
        throw new Exception("Invalid operator " + operator);
    }

    private void subtractToCurrentBalance(BalanceRequest balanceRequest) {

    }

    private void addToCurrentBalance(BalanceRequest balanceRequest) {
    }

}
