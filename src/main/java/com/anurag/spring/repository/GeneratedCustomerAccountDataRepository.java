package com.anurag.spring.repository;

import com.anurag.spring.entity.CustomerAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface GeneratedCustomerAccountDataRepository extends JpaRepository<CustomerAccount,String> {


    CustomerAccount findByCustomerId(Integer customerId);

    @Transactional
    @Modifying(clearAutomatically = true,flushAutomatically = true)
    @Query(nativeQuery = true,
            value = "update customer_account " +
                    " set balance = (balance - :amount) " +
                    " where account_number = :account_number and customer_id = :customerId"

    )
    public int subtractBalance(String account_number,Integer customerId, Double amount);

    @Transactional
    @Modifying(clearAutomatically = true,flushAutomatically = true)
    @Query(nativeQuery = true,
            value = "update customer_account " +
                    " set balance = (balance + :amount) " +
                    " where account_number = :account_number and customer_id = :customerId"

    )
    public int addBalance(String account_number,Integer customerId, Double amount);


}
