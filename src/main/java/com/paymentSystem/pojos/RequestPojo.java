package com.paymentSystem.pojos;

import lombok.*;
import org.hibernate.validator.constraints.Range;
import org.springframework.stereotype.Repository;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestPojo {
    @NotEmpty
    private String userId;
    @NotEmpty
    private String orderId;
    @NotNull
    private Double orderAmount;
    @NotEmpty
    private String currency;
}
