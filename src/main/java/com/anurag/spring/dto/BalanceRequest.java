package com.anurag.spring.dto;

public record BalanceRequest(Integer customerRecordId,String accountNumber,Double amount,String operator) {

}
