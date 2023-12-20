package com.anurag.spring.data;

import com.anurag.spring.entity.Customer;
import com.github.javafaker.Address;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.IntStream;

@Component
public class DataGenerator {
    @Value("${data.generator.limit:20}")
    Integer limit;

    @Autowired
    GeneratedCustomerDataRepository dataRepository;


    @Scheduled(fixedDelay = 60 * 1000)
    public void generateData() {

      List<Customer> customerList =  IntStream.rangeClosed(1,limit).mapToObj(value -> generateClientRecord()).toList();
      dataRepository.saveAll(customerList);

    }

    private Customer generateClientRecord() {
        Faker faker = new Faker();
       Address address =  faker.address();
       return Customer.builder()
                .age(faker.number().numberBetween(18,100))
                .email(faker.internet().safeEmailAddress())
                .city(address.city())
                .country(address.country())
                .zip(address.zipCode())
                .street(address.streetAddress())
                .landmark(address.streetName())
                .phone(faker.phoneNumber().cellPhone())
                .customerName(faker.name().name())
                .build();
    }
}
