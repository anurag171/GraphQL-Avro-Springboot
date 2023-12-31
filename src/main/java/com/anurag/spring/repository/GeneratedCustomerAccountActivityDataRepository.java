package com.anurag.spring.repository;

import com.anurag.spring.entity.CustomerAccountActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeneratedCustomerAccountActivityDataRepository extends JpaRepository<CustomerAccountActivity,String> {
}
