package com.anurag.spring.data;

import com.anurag.spring.entity.Customer;
import com.anurag.spring.entity.CustomerAccount;
import com.anurag.spring.repository.GeneratedCustomerAccountDataRepository;
import com.anurag.spring.repository.GeneratedCustomerDataRepository;
import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.iban4j.CountryCode;
import org.iban4j.Iban;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicBoolean;

@Component
@Slf4j
public class CustomerAccountGenerator {
    @Value("${data.generator.limit:5}")
    Integer limit;

    @Value("${data.account.generator.enabled:false}")
    Boolean enabled;

    private final GeneratedCustomerAccountDataRepository dataRepository;

    private final GeneratedCustomerDataRepository customerDataRepository;

    public CustomerAccountGenerator(GeneratedCustomerAccountDataRepository dataRepository, GeneratedCustomerDataRepository customerDataRepository) {
        this.dataRepository = dataRepository;
        this.customerDataRepository = customerDataRepository;
    }


    @Scheduled(fixedDelay = 30000)
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void generateData() {

        if (!enabled)
            return;


        AtomicBoolean tryAgain = new AtomicBoolean(true);

        while (tryAgain.get()) {
            try {
                Integer limit = customerDataRepository.countDistinctBy();
                runnableRoutine(limit, tryAgain);
            } catch (Exception ex) {
                log.error(ex.getMessage());
                tryAgain.set(true);
            }
        }
    }

    private void runnableRoutine(Integer limit, AtomicBoolean tryAgain) {
        int clientid = ThreadLocalRandom.current().nextInt(1, limit);

        dataRepository.save(generateClientAccountRecord(customerDataRepository.findByRecord_number_id(clientid)));
        tryAgain.set(false);
    }

    private CustomerAccount generateClientAccountRecord(Customer customer) {

        Faker faker = new Faker();

        Iban iban = new Iban.Builder()
                .countryCode(CountryCode.getByCode(customer.getCountryCode()))

                .buildRandom();

        log.info("Enter into generateClientRecord {} ", customer.getCustomerName());
        CustomerAccount customerAccount = new CustomerAccount();
        customerAccount.setCustomerName(customer.getCustomerName());
        customerAccount.setAccountNumber(iban.getAccountNumber());
        customerAccount.setAccountType(iban.getAccountType() != null ? iban.getAccountType() : "SAVING");
        customerAccount.setBalance(BigDecimal.valueOf(faker.number().randomDouble(2, 10000, 9999999)));
        customerAccount.setCustomerId(customer.getRecord_number_id());
        customerAccount.setCustomerUniqueId(customer.getId());
        return customerAccount;
    }
}
