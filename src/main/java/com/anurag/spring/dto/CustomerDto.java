package com.anurag.spring.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerDto {

    private String id;

    private String customerName;

    private int age;

    private String email;

    private String city;

    private String country;

    private String street;

    private String zip;

    private String state;

    private String landmark;

    private String phone;
}