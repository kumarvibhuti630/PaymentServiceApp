package com.paymentSystem.models;

import lombok.Data;
import org.hibernate.validator.constraints.Range;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Entity
@Data
@Table(name="paymentmodeldto",uniqueConstraints = {@UniqueConstraint(columnNames = "orderId")})
public class PaymentModelDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long Id;
    private String userId;
    private String orderId;
    @Range(min=0)
    private Double orderAmount;
    private String transactionId;
    private com.paymentSystem.enums.transactionStatus transactionStatus;
    @Size(min=3, max=3)
    private String currency;
    private Timestamp created;
    private Timestamp modified;

}
