package com.anurag.spring.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;

@Builder
@Getter
public class BalanceResponseDto {

    @NonNull
    private String customerName;

    @NonNull
    private Integer customerRecordId;
    @NonNull
    private String accountNumber;

    @NonNull
    private Double amount;

    @NonNull
    private String operator;

    @NonNull
    private Double oldBalance;

    @NonNull
    private Double newBalance;
}
