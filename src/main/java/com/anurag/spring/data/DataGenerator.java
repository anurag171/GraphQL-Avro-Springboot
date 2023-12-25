package com.anurag.spring.data;

import com.anurag.spring.entity.Customer;
import com.github.javafaker.Address;
import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.IntStream;

@Component
@Slf4j
public class DataGenerator {
    @Value("${data.generator.limit:20}")
    Integer limit;

    @Value("${data.generator.enabled:false}")
    Boolean enabled;

    private final GeneratedCustomerDataRepository dataRepository;

    public DataGenerator(GeneratedCustomerDataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }


    @Scheduled(fixedDelay = 120000)
    public void generateData() {

        if (!enabled)
            return;

        List<Customer> customerList = IntStream.rangeClosed(1, limit).parallel().mapToObj(value -> generateClientRecord()).toList();
        dataRepository.saveAll(customerList);

    }

    private Customer generateClientRecord() {

        Faker faker = new Faker();
        var name = faker.name().name();
        Address address = faker.address();
        log.info("Enter into generateClientRecord {} ", name);
        Customer customer = new Customer();
        customer.setAge(faker.number().numberBetween(18, 100));
        customer.setEmail(faker.internet().safeEmailAddress());
        customer.setCity(address.city());
        customer.setCountry(address.country());
        customer.setZip(address.zipCode());
        customer.setStreet(address.streetAddress());
        customer.setLandmark(address.streetName());
        customer.setState(address.state());
        customer.setPhone(faker.phoneNumber().cellPhone());
        customer.setCustomerName(name);
        return customer;
    }
}
