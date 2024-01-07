package com.anurag.spring.service;

import com.anurag.spring.dto.BalanceRequest;
import com.anurag.spring.dto.BalanceResponseDto;
import com.anurag.spring.entity.Customer;
import com.anurag.spring.entity.CustomerAccount;
import com.anurag.spring.repository.GeneratedCustomerAccountDataRepository;
import com.anurag.spring.repository.GeneratedCustomerDataRepository;
import jakarta.persistence.LockModeType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@Slf4j
public class CustomerAccountBalanceService {

    private final GeneratedCustomerAccountDataRepository generatedCustomerAccountDataRepository;

    private final GeneratedCustomerDataRepository generatedCustomerDataRepository;

    @Autowired
    public CustomerAccountBalanceService(GeneratedCustomerAccountDataRepository generatedCustomerAccountDataRepository,
                                         GeneratedCustomerDataRepository generatedCustomerDataRepository) {
        this.generatedCustomerAccountDataRepository = generatedCustomerAccountDataRepository;
        this.generatedCustomerDataRepository = generatedCustomerDataRepository;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Lock(LockModeType.OPTIMISTIC)
    public BalanceResponseDto addAmount(BalanceRequest balanceRequest) {

        CustomerAccount customerAccount = checkAndReturnCustomerAccount(balanceRequest);
        if(customerAccount == null)
            return BalanceResponseDto.builder().build();

        var balanceResponseDto = BalanceResponseDto.builder()
                                .oldBalance(customerAccount.getBalance().doubleValue())
                                .newBalance(customerAccount.getBalance()
                                            .add(BigDecimal.valueOf(balanceRequest.amount())).doubleValue())
                                .accountNumber(balanceRequest.accountNumber())
                                .amount(balanceRequest.amount())
                                .operator(balanceRequest.operator())
                                .customerName(customerAccount.getCustomerName())
                                .customerRecordId(balanceRequest.customerRecordId()).build();

        customerAccount.setBalance(BigDecimal.valueOf(balanceResponseDto.getNewBalance()));
        generatedCustomerAccountDataRepository.save(customerAccount);
        return balanceResponseDto;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Lock(LockModeType.OPTIMISTIC)
    public BalanceResponseDto subtractAmount(BalanceRequest balanceRequest) {
        CustomerAccount customerAccount = checkAndReturnCustomerAccount(balanceRequest);
        if(customerAccount == null)
            return BalanceResponseDto.builder().build();

        var balanceResponseDto = BalanceResponseDto.builder()
                .oldBalance(customerAccount.getBalance().doubleValue())
                .newBalance(customerAccount.getBalance()
                        .subtract(BigDecimal.valueOf(balanceRequest.amount())).doubleValue())
                .accountNumber(balanceRequest.accountNumber())
                .amount(balanceRequest.amount())
                .operator(balanceRequest.operator())
                .customerName(customerAccount.getCustomerName())
                .customerRecordId(balanceRequest.customerRecordId()).build();

        customerAccount.setBalance(BigDecimal.valueOf(balanceResponseDto.getNewBalance()));
        generatedCustomerAccountDataRepository.save(customerAccount);
        return balanceResponseDto;
    }

    private CustomerAccount checkAndReturnCustomerAccount(BalanceRequest balanceRequest){

        log.info(" Received request [{}]", balanceRequest.toString());

        Customer customer = generatedCustomerDataRepository.findByRecord_number_id(balanceRequest.customerRecordId());


        if(customer == null){
            throw  new RuntimeException("Customer not found");
        }
        log.info("Check customer by record id [{}]",customer);
        CustomerAccount customerAccount = generatedCustomerAccountDataRepository.
                findByCustomerId(customer.getRecord_number_id());

        log.info(" Resolved customer record [{}]", customerAccount);
        return  customerAccount;
    }


}
