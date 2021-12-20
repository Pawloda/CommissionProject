package com.task.commissions.type;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class CommissionTO {

    private String customerFirstName;
    private String customerLastName;
    private Long customerId;
    private Long numberOfTransactions;
    private Double valueOfTransactions;
    private Double feeValue;
    private LocalDateTime lastTransactionDate;

}
