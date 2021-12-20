package com.task.commissions.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class TransactionEntity {

    private Long transactionId;
    private Double transactionAmount;
    private String customerFirstName;
    private Long customerId;
    private String customerLastName;
    private LocalDateTime transactionDate;

}
