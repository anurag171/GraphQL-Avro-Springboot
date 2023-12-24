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

   public DataGenerator(GeneratedCustomerDataRepository dataRepository){
       this.dataRepository = dataRepository;
   }


    @Scheduled(fixedDelay = 120000)
    public void generateData() {

       if(!enabled)
           return;

      List<Customer> customerList =  IntStream.rangeClosed(1,limit).parallel().mapToObj(value -> generateClientRecord()).toList();
      dataRepository.saveAll(customerList);

    }

    private Customer generateClientRecord() {

        Faker faker = new Faker();
        var name  = faker.name().name();
       Address address =  faker.address();
        log.info("Enter into generateClientRecord {} ",name);
       return Customer.builder()
                .age(faker.number().numberBetween(18,100))
                .email(faker.internet().safeEmailAddress())
                .city(address.city())
                .country(address.country())
                .zip(address.zipCode())
                .street(address.streetAddress())
                .landmark(address.streetName())
               .state(address.state())
                .phone(faker.phoneNumber().cellPhone())
                .customerName(name)
                .build();
    }
}
