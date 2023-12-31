package com.anurag.spring.repository;

import com.anurag.spring.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeneratedCustomerDataRepository extends JpaRepository<Customer,String> {


    Integer countDistinctBy();

    Customer findByRecord_number_id(Integer record_number_id);




}
