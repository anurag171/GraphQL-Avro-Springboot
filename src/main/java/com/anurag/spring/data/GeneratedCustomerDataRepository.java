package com.anurag.spring.data;

import com.anurag.spring.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeneratedCustomerDataRepository extends JpaRepository<Customer,String> {


}
