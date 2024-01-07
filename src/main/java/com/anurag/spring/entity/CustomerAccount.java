package com.anurag.spring.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "customer_account")
@NamedQueries({
        @NamedQuery(name = "CustomerAccount.findByCustomerId", query = "select c from CustomerAccount c where c.customerId = ?1", lockMode = LockModeType.OPTIMISTIC)
})
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EntityListeners(AuditingEntityListener.class)
public class CustomerAccount {

    @Id
    @UuidGenerator
    private String id;

    private String accountNumber;

    private int customerId;

    private String customerName;

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

    @Version
    @Column(name = "version")
    @JdbcTypeCode(SqlTypes.INTEGER)
    private Integer version;

    private String accountType;

    private BigDecimal balance;

    private String customerUniqueId;

    @Column(name = "operation")
    private String operation;

    @Column(name = "timestamp")
    private long timestamp;

    @Column(name = "created_date", nullable = false, updatable = false)
    @CreatedDate
    private long createdDate;

    @Column(name = "modified_date")
    @LastModifiedDate
    private long modifiedDate;

    private void audit(String operation) {
        setOperation(operation);
        setTimestamp((new Date()).getTime());
    }

}
