package com.task.commissions.mapper;

import com.task.commissions.domain.TransactionEntity;
import com.task.commissions.type.TransactionTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TransactionMapper {

    public static TransactionTO toTransactionTO(TransactionEntity transaction) {
        if(Objects.equals(null, transaction)) {
            return null;
        }
        return TransactionTO.builder()
                .transactionId(transaction.getTransactionId())
                .transactionAmount(transaction.getTransactionAmount())
                .customerFirstName(transaction.getCustomerFirstName())
                .customerId(transaction.getCustomerId())
                .customerLastName(transaction.getCustomerLastName())
                .transactionDate(transaction.getTransactionDate())
                .build();
    }

    public static TransactionEntity toTransactionEntity(TransactionTO transaction) {
        if(Objects.equals(null, transaction)) {
            return null;
        }
        return TransactionEntity.builder()
                .transactionId(transaction.getTransactionId())
                .transactionAmount(transaction.getTransactionAmount())
                .customerFirstName(transaction.getCustomerFirstName())
                .customerId(transaction.getCustomerId())
                .customerLastName(transaction.getCustomerLastName())
                .transactionDate(transaction.getTransactionDate())
                .build();
    }

    public static List<TransactionTO> map2TOs(List<TransactionEntity> transactions) {
        if(Objects.equals(null, transactions)) {
            return null;
        }
        List<TransactionTO> result = new ArrayList<>();
        for(TransactionEntity transaction : transactions) {
            if(!Objects.equals(null, transaction)) {
                result.add(toTransactionTO(transaction));
            }
        }
        return result;
    }

    public static List<TransactionEntity> map2Entities(List<TransactionTO> transactions) {
        if(Objects.equals(null, transactions)) {
            return null;
        }
        List<TransactionEntity> result = new ArrayList<>();
        for(TransactionTO transaction : transactions) {
            if(!Objects.equals(null, transaction)) {
                result.add(toTransactionEntity(transaction));
            }
        }
        return result;
    }

}
