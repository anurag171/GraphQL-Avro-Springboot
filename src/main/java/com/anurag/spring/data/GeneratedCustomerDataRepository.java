package com.anurag.spring.data;

import com.anurag.spring.entity.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeneratedCustomerDataRepository extends CrudRepository<Customer,String> {


}
