package com.anurag.spring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "customer_account_activity")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class CustomerAccountActivity {

    @Id
    @UuidGenerator
    private String id;

    private String accountNumber;

    private String entryType;

    private String narration;

    private BigDecimal amount;

    private String currency;

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
