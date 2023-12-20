package com.anurag.spring.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;


@Builder
@Entity
@Table(name = "customer_record")
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    //@GenericGenerator(name = "sequence_customer_id", strategy = "com.anurag.spring.generator.CustomerIdGenerator")
    //@GeneratedValue(strategy = GenerationType.UUID)
    @UuidGenerator
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
