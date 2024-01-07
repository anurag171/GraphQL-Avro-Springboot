package com.anurag.spring.repository;

import com.anurag.spring.entity.CustomerAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GeneratedCustomerAccountDataRepository extends JpaRepository<CustomerAccount,String> {


    CustomerAccount findByCustomerId(Integer customerId);

}
