package com.anurag.spring.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;


@Entity
@Table(name = "customer_record", indexes = {
        @Index(name = "idx_customer_customer_name", columnList = "customer_name, country")
})
@NamedQueries({
        @NamedQuery(name = "Customer.countDistinctByCustomerNameLikeIgnoreCase", query = "select count(distinct c) from Customer c where c.customerName like ?1", lockMode = LockModeType.OPTIMISTIC),
        @NamedQuery(name = "Customer.existsByCustomerNameLikeIgnoreCase", query = "select (count(c) > 0) from Customer c where upper(c.customerName) like upper(?1)", lockMode = LockModeType.OPTIMISTIC),
        @NamedQuery(name = "Customer.countDistinctBy", query = "select count(distinct c) from Customer c", lockMode = LockModeType.OPTIMISTIC),
        @NamedQuery(name = "Customer.findByRecord_number_id", query = "select c from Customer c where c.record_number_id = ?1", lockMode = LockModeType.OPTIMISTIC)
})
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EntityListeners(AuditingEntityListener.class)
public class Customer {

    @Id
    //@GenericGenerator(name = "sequence_customer_id", strategy = "com.anurag.spring.generator.CustomerIdGenerator")
    //@GeneratedValue(strategy = GenerationType.UUID)
    @UuidGenerator
    private String id;

    private int record_number_id;

    private String customerName;

    private int age;

    private String email;

    private String city;

    private String country;

    private String street;

    private String zip;

    private String state;

    private String landmark;

    private String phone;

    private String countryCode;

    @Version
    @Column(name = "version")
    @JdbcTypeCode(SqlTypes.INTEGER)
    private Integer version;

    @Column(name = "created_date", nullable = false, updatable = false)
    @CreatedDate
    private long createdDate;

    @Column(name = "modified_date")
    @LastModifiedDate
    private long modifiedDate;

    @Column(name = "operation")
    private String operation;

    @Column(name = "timestamp")
    private long timestamp;

    private void audit(String operation) {
        setOperation(operation);
        setTimestamp((new Date()).getTime());
    }

    @PreUpdate
    public void preUpdate() {
        audit("UPDATE");
    }

    @PreRemove
    public void preRemove() {
        audit("DELETE");
    }

    @PrePersist
    public void prePersist() {
        audit("INSERT");
    }
}
